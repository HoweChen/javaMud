package util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

public class HutoolDateUtilTest {

  @Test
  public void givenWrongTimestamp_whenGenerateDateTimeObject_thenShouldFailed() {
    try {

      DateTime parse = DateUtil.parse("haha", DatePattern.NORM_DATETIME_PATTERN);
    } catch (Exception ex) {
      System.out.println();
    }
  }

  // get HHmmss string of date
  @Test
  public void givenDate_whenGetHHmmss_thenShouldSuccess() {
    DateTime now = DateUtil.date();
    String format = DateUtil.format(now, "HHmmss");
    System.out.println(format);
  }

}
