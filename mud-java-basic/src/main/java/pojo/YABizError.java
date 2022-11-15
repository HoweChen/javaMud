package pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

/**
 * @author yuhaochen
 */
public class YABizError {

  private List<IError> IErrors = new ArrayList<>();

  public List<IError> getAllErrors() {
    return IErrors;
  }

  public void addError(@Nonnull IError IError) {
    this.IErrors.add(IError);
  }

  public boolean hasError() {
    return !this.IErrors.isEmpty();
  }

  public IError getError() {
    if (IErrors.isEmpty()) {
      return new IError();
    } else {
      return this.IErrors.get(this.IErrors.size() - 1);
    }
  }
}
