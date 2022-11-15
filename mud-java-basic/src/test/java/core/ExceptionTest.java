package core;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import pojo.IResult;
import pojo.YABizError;

public class ExceptionTest {

  @Test
  public void givenFunctionWithBizWrapperAsReturnValue_whenReturnBizWrapper_thenShouldSucceed() {
    IResult bw = getString();
    System.out.println(bw);
  }

  @Test
  public void givenFunctionWithYABizWrapperAsInputParameter_whenReturnBizWrapper_thenShouldSucceed() {
    YABizError bizError = new YABizError();
    String result = getString(bizError);
    if (StringUtils.isEmpty(result) || bizError.hasError()) {
      System.out.println(bizError.getError());
    } else {
      System.out.println(result);
    }
  }

  private IResult getString() {
    IResult randomIntegerResult = getRandomInteger();
    return switch (randomIntegerResult.getResultType()) {
      case OK -> IResult.OK(randomIntegerResult, String::valueOf);
      case ERROR -> IResult.ERROR(randomIntegerResult.getError());
    };
  }

  private IResult getRandomInteger() {
    int response = ThreadLocalRandom.current().nextInt();
    System.out.println(response);
    return IResult.OK(response);
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
