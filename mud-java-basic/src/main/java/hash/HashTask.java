package hash;

import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import jackson.TestClass;
import java.nio.charset.StandardCharsets;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashTask {

  public static ObjectMapper JACKSON_MAPPER = new ObjectMapper();
  public static TestClass testClass = new TestClass("Haha", 10);
  private static final Logger log = LoggerFactory.getLogger("benchmark");

  private static final String str =
      "00020101021240590018ID.CO.ALLOBANK.WWW01189360056718277817800211781778878825204482953033605408100000005802ID5911ADI PRATAMA6015JAKARTA SELATAN61051279062840703A010804CDPT99650002000155VEE12Q8A79CASHIN4110QRIS15072022054041636ALLOBANK00481563043AB9";

  @Benchmark
  @Warmup(iterations = 1, time = 3)
  @Fork(value = 5)
  @BenchmarkMode(value = Mode.AverageTime)
  @Measurement(iterations = 1, time = 10)
  @Threads(100)
  public String runHash() {
    //    return DigestUtil.sha256Hex(str);
    //noinspection UnstableApiUsage
    return Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString();
  }

  public static void main(String[] args) {
    HashTask task = new HashTask();
    System.out.println(task.runHash().length());
  }
}
