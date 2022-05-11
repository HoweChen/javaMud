package workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nonnull;

/**
 * @author yuhaochen
 */
public class Workflow {

  List<Stage> stages = new ArrayList<>();
  Map<WorkflowEvent, Function<Context, Context>> eventHandlerMap = new HashMap<>();

  private Workflow() {}

  public Context run() {
    return null;
  }

  public static final class WorkflowBuilder {

    private final Workflow workflow;

    public WorkflowBuilder() {
      this.workflow = new Workflow();
    }

    public WorkflowBuilder next(@Nonnull Stage stage) {
      workflow.stages.add(stage);
      return this;
    }

    public WorkflowBuilder onEvent(
        @Nonnull WorkflowEvent event, @Nonnull Function<Context, Context> onEventHandler) {
      workflow.eventHandlerMap.put(event, onEventHandler);
      return this;
    }

    public Workflow build() {
      return this.workflow;
    }
  }
}
