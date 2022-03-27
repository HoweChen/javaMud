package pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

/**
 * @author yuhaochen
 */
public class YABizError {

  private List<BizError> errors = new ArrayList<>();

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
}
