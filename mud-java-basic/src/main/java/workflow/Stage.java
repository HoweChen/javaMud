package workflow;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 * @author yuhaochen
 */
public class Stage {

  boolean isAsync = false;
  Set<Action> actions = new HashSet<>();

  private Stage() {}

  public WorkflowEvent run() {
    return null;
  }

  public static final class StageBuilder {

    private final Stage stage;

    public StageBuilder() {
      this.stage = new Stage();
    }

    public Stage build() {
      return this.stage;
    }
  }
}
