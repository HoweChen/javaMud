package core;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import pojo.BizError;
import pojo.BizWrapper;
import pojo.YABizError;

public class ExceptionTest {

  @Test
  public void givenFunctionWithBizWrapperAsReturnValue_whenReturnBizWrapper_thenShouldSucceed() {
    BizWrapper<String> bw = getString();
    if (bw.hasError()) {
      System.out.println(bw.getError());
    } else {
      System.out.println(bw.getResponse());
    }
  }

  @Test
  public void
      givenFunctionWithYABizWrapperAsInputParameter_whenReturnBizWrapper_thenShouldSucceed() {
    YABizError bizError = new YABizError();
    String result = getString(bizError);
    if (StringUtils.isEmpty(result) || bizError.hasError()) {
      System.out.println(bizError.getError());
    } else {
      System.out.println(result);
    }
  }

  private BizWrapper<String> getString() {
    BizWrapper<Integer> intBW = getRandomInteger();
    if (intBW.hasError()) {
      return BizWrapper.ERROR(intBW.getError());
    } else {
      return BizWrapper.SUCCESS(String.valueOf(intBW.getResponse()));
    }
  }

  private BizWrapper<Integer> getRandomInteger() {
    int response = ThreadLocalRandom.current().nextInt();
    System.out.println(response);
    return BizWrapper.SUCCESS(response);
  }

  private String getString(YABizError error) {
    int intResult = getRandomInteger(error);
    if (error.hasError()) {
      return null;
    } else {
      return String.valueOf(intResult);
    }
  }

  private int getRandomInteger(YABizError error) {
    int response = ThreadLocalRandom.current().nextInt();
    System.out.println(response);
    return response;
  }
}
