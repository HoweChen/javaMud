package juc;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import org.junit.Test;

public class CompletionServiceTest {

  @Test
  public void givenTwoThreads_whenOneTimeoutThenAnotherGotSuccess_thenShouldGetCorrectResponse() {

    // given
    ExecutorService executorService = Executors.newCachedThreadPool();
    CompletableFuture<Integer> normalCF = CompletableFuture.supplyAsync(() -> 1, executorService);
    CompletableFuture<String> timeoutCF =
        CompletableFuture.supplyAsync(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(10);
              } catch (InterruptedException _ignored) {
                System.out.println(_ignored);
              }
              return "test";
            },
            executorService);

    // when
    List<?> completedTasks = null;
    try {
      List<CompletableFuture<?>> cfList = Lists.newArrayList(normalCF, timeoutCF);

      CompletableFuture[] cfs = cfList.toArray(new CompletableFuture[0]);
      CompletableFuture.allOf(cfs)
          .whenComplete(
              (unused, throwable) ->
                  Arrays.stream(cfs)
                      .forEach(
                          cf -> System.out.println(Thread.currentThread().getName() + cf.isDone())))
          .get(3, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.out.println(e);
      //      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      System.out.println(e);
      //      throw new RuntimeException(e);
    } catch (TimeoutException e) {
      System.out.println(e);
    } finally {
      if (normalCF.isDone()) {
        try {
          System.out.println(normalCF.get());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } catch (ExecutionException e) {
          throw new RuntimeException(e);
        }
      }

      if (timeoutCF.isDone()) {
        try {
          System.out.println(timeoutCF.get());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } catch (ExecutionException e) {
          throw new RuntimeException(e);
        }
      }
    }

    // then
    System.out.println(normalCF.isDone());
    System.out.println(timeoutCF.isDone());
  }

  @Test
  public void givenOneCompletableFuture_whenGetNow_thenShouldReturnSucceed() {
    // given
    ExecutorService executorService = Executors.newCachedThreadPool();
    CompletableFuture<Integer> normalCF =
        CompletableFuture.supplyAsync(
            () -> {
              //              try {
              //                TimeUnit.SECONDS.sleep(3);
              //              } catch (InterruptedException e) {
              //                throw new RuntimeException(e);
              //              }

              return 1;
            },
            executorService);

    // when
    Integer now = null;
    try {
      now = normalCF.getNow(normalCF.get());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    }

    // then
    System.out.println(now);
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println(now);
  }

  @Test
  public void givenOneCompletableFutureWithSleep_whenGet_thenShouldReturnSucceed() {
    // given
    ExecutorService executorService = Executors.newCachedThreadPool();
    CompletableFuture<Integer> normalCF =
        CompletableFuture.supplyAsync(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(3);
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }

              return 1;
            },
            executorService);

    // when
    try {
      // note that the cf get timeout time is larger than the sleep duration in cf
      normalCF.get(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    } catch (TimeoutException e) {
      throw new RuntimeException(e);
    }

    Integer result = null;
    try {
      if (normalCF.isDone()) {
        result = normalCF.get();
      } else {
        result = null;
      }
    } catch (Exception ignored) {
      System.out.println(ignored);
    }

    // then
    assert result != null;
    System.out.println(result);
  }

  @Test
  public void givenListenableFuture_whenRun_thenShouldReturnSucceed() {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    ListeningExecutorService guavaExecutor = MoreExecutors.listeningDecorator(executor);
    ListenableFuture<String> future1 =
        guavaExecutor.submit(
            () -> {
              // step 1
              System.out.println("执行step 1");
              return "step1 result";
            });
    ListenableFuture<String> future2 =
        guavaExecutor.submit(
            () -> {
              // step 2
              System.out.println("执行step 2");
              return "step2 result";
            });
    ListenableFuture<List<String>> future1And2 = Futures.allAsList(future1, future2);
    Futures.addCallback(
        future1And2,
        new FutureCallback<List<String>>() {
          @Override
          public void onSuccess(List<String> result) {
            System.out.println(result);
            ListenableFuture<String> future3 =
                guavaExecutor.submit(
                    () -> {
                      System.out.println("执行step 3");
                      return "step3 result";
                    });
            Futures.addCallback(
                future3,
                new FutureCallback<String>() {
                  @Override
                  public void onSuccess(String result) {
                    System.out.println(result);
                  }

                  @Override
                  public void onFailure(Throwable t) {}
                },
                guavaExecutor);
          }

          @Override
          public void onFailure(Throwable t) {}
        },
        guavaExecutor);
  }

  @Test
  public void givenCompletableFuture_whenRun_thenShouldReturnSucceed() {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    CompletableFuture<String> cf1 =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println("执行step 1");
              return "step1 result";
            },
            executor);
    CompletableFuture<String> cf2 =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println("执行step 2");
              return "step2 result";
            });
    cf1.thenCombine(
            cf2,
            (result1, result2) -> {
              System.out.println(result1 + " , " + result2);
              System.out.println("执行step 3");
              return "step3 result";
            })
        .thenAccept(result3 -> System.out.println(result3));
  }

  @Test
  public void givenThreeCFs_whenAllTrigger_thenShouldSucceed() {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "1");
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "2");
    CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> "3");

    CompletableFuture<String> allResult =
        CompletableFuture.allOf(cf1, cf2, cf3)
            .thenApply(
                unused -> {
                  String oneResult = cf1.join();
                  String twoResult = cf2.join();
                  String threeResult = cf3.join();
                  return oneResult + twoResult + threeResult;
                });

    System.out.println(allResult.join());
  }
}
