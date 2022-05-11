package workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author yuhaochen
 */
public final class ActionExecutor extends AbstractEngine implements Engine<Set<Action>> {

  @Override
  public void run(Set<Action> unit, Context context) {
    List<CompletableFuture<WorkflowEvent>> actionResults = new ArrayList<>();

    for (Action action : unit) {
      actionResults.add(
          CompletableFuture
              .supplyAsync(() -> action.run(context), getExecutors())
              .exceptionally(
              throwable -> {
                throwable.printStackTrace();
                return WorkflowEvent.ON_SYS_ERROR;
              }));

    }
    CompletableFuture<Void> allCFs = CompletableFuture.allOf(
        actionResults.toArray(new CompletableFuture[0]));

    try {
      allCFs.get(10, TimeUnit.SECONDS);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      e.printStackTrace();
    }
  }
}
