package workflow;

/**
 * @author yuhaochen
 */
public final class Action {

  WorkflowEvent run(Context context) {return null;}

  private Action() {}

  public static final class ActionBuilder {
    private final Action action;

    public ActionBuilder() {
      this.action = new Action();
    }

    public Action build() {
      return this.action;
    }
  }
}
