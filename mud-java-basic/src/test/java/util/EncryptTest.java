package util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class EncryptTest {

  protected String padTo16WithWhitespace(String str) {
    int len = str.length();
    int mod = len % 16;
    if (mod != 0) {
      int pad = 16 - mod;
      return StringUtils.leftPad(str, len + pad, ' ');
    } else {
      return str;
    }
  }


  @Test
  public void testAes() {
    AES aes = new AES(Mode.ECB, Padding.NoPadding, padTo16WithWhitespace("1234567812345678").getBytes());
    String result = aes.encryptHex(padTo16WithWhitespace("Hello World!"));
    System.out.println(result);
    System.out.println(result.equalsIgnoreCase("F97F96BE0BF00E6E3223A595EC70F4BD"));
  }

  @Test
  public void leftPaddingTest() {
    String str = "haha";
    System.out.println(padTo16WithWhitespace(str));

  }

}
