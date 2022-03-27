package pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author yuhaochen
 */
public class BizWrapper<T> {

  T response;
  List<BizError> errors = new ArrayList<>();

  public BizWrapper() {}

  public T getResponse() {
    return response;
  }

  public void setResponse(T response) {
    this.response = response;
  }

  public List<BizError> getAllErrors() {
    return errors;
  }

  public void addError(@Nonnull BizError error) {
    this.errors.add(error);
  }

  public boolean hasError() {
    return !this.errors.isEmpty();
  }

  public BizError getError() {
    if (errors.isEmpty()) {
      return new BizError();
    } else {
      return this.errors.get(this.errors.size() - 1);
    }
  }

  @Nonnull
  public static <T> BizWrapper<T> ERROR(@Nonnull BizError error) {
    BizWrapper<T> bw = new BizWrapper<>();
    bw.addError(error);
    return bw;
  }

  @Nonnull
  public static <T> BizWrapper<T> SUCCESS(@Nonnull T response) {
    BizWrapper<T> bw = new BizWrapper<>();
    bw.setResponse(response);
    return bw;
  }

  @Nonnull
  public static BizWrapper<Void> VOID() {
    return new BizWrapper<>();
  }
}
