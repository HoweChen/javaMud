package util;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class FileUtilTest {

  @Test
  public void givenSmapleFile_whenEncrypt_thenShouldSuccess() {
    RSA rsa = new RSA();
    byte[] encrypt =
        rsa.encrypt(
            ResourceUtil.getStream(
                "C:\\Users\\yuhaochen\\IdeaProjects\\javaMud\\mud-java-basic\\src\\test\\resources\\haha.csv"),
            KeyType.PrivateKey);
    byte[] decrypt = rsa.decrypt(encrypt, KeyType.PublicKey);

    System.out.println(StrUtil.str(decrypt, StandardCharsets.UTF_8));
  }

  @Test
  public void givenSampleFile_whenEncryptThenDecyrptUsingAES256_thenShouldSucceed() {
    AES aes =
        new AES(Mode.CBC, Padding.PKCS5Padding);

    byte[] encrypt =
        aes.encrypt(
            ResourceUtil.getStream(
                "C:\\Users\\yuhaochen\\IdeaProjects\\javaMud\\mud-java-basic\\src\\test\\resources\\haha.csv"));
    byte[] decrypt = aes.decrypt(encrypt);

    System.out.println(StrUtil.str(decrypt, StandardCharsets.UTF_8));
  }
}
