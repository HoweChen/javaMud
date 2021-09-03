package future;

public class TaskNumber extends Task<Integer> {
  @Override
  public Integer call() throws Exception {
    return 10;
  }
}
