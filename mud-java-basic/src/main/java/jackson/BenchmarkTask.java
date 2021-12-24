package jackson;

import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchmarkTask {

  public static ObjectMapper JACKSON_MAPPER = new ObjectMapper();
  public static TestClass testClass = new TestClass("Haha", 10);
  private final static Logger log = LoggerFactory.getLogger("benchmark");


  @Benchmark
  @Warmup(iterations = 1, time = 3)
  @Fork(value = 5)
  @BenchmarkMode(value = Mode.Throughput)
  @Measurement(iterations = 1, time = 10)
  public void runJackson() {
    TestClass testClass = new TestClass(RandomUtil.randomString(32), RandomUtil.randomInt(10000));
    log.info(testClass.toJacksonStr());
  }

  @Benchmark
  @Warmup(iterations = 1, time = 3)
  @Fork(value = 5)
  @BenchmarkMode(value = Mode.Throughput)
  @Measurement(iterations = 1, time = 10)
  public void runToString() {
    TestClass testClass = new TestClass(RandomUtil.randomString(32), RandomUtil.randomInt(10000));
    log.info(testClass.toString());
  }

  @Benchmark
  @Warmup(iterations = 1, time = 3)
  @Fork(value = 5)
  @BenchmarkMode(value = Mode.Throughput)
  @Measurement(iterations = 1, time = 10)
  public void runReflectionToString() {
    TestClass testClass = new TestClass(RandomUtil.randomString(32), RandomUtil.randomInt(10000));
    log.info(testClass.toReflectionStr());
  }

}
