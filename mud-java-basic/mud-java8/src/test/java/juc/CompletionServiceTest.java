package juc;

import com.google.common.collect.Lists;
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
import org.junit.jupiter.api.Test;

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
}
