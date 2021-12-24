package jackson;

import java.io.IOException;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

  public static void main(String[] args) throws IOException, RunnerException {
    Options options = new OptionsBuilder().include(BenchmarkTask.class.getSimpleName())
        //            .output("./Benchmark.log")
        .build();
    new Runner(options).run();
//    org.openjdk.jmh.Main.main(args);
  }
}
