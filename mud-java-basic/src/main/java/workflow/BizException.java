package workflow;

/**
 * @author yuhaochen
 */
public class BizException extends RuntimeException {

  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }
}
