package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;

public class BenchMark {

  public static ObjectMapper JACKSON_MAPPER = new ObjectMapper();
  public static TestClass testClass = new TestClass("Haha", 10);

  @Benchmark
  @Warmup(iterations = 1, time = 3)
  @Fork(value = 5)
  @BenchmarkMode(Mode.Throughput)
  @Measurement(iterations = 1, time = 10)
  public void runJackson() {
    testClass.toJacksonStr();
  }

  @Benchmark
  @Warmup(iterations = 1, time = 3)
  @Fork(value = 5)
  @BenchmarkMode(Mode.Throughput)
  @Measurement(iterations = 1, time = 10)
  public void runToString() {
    testClass.toString();
  }

  //  public static void main(String[] args) throws RunnerException {
  //    Options options =
  //        new OptionsBuilder()
  //            .include(BenchMark.class.getSimpleName())
  ////            .output("./Benchmark.log")
  //            .build();
  //    new Runner(options).run();
  //  }
}
