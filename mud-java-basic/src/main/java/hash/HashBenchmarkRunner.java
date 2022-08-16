package hash;

import java.io.IOException;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class HashBenchmarkRunner {

  public static void main(String[] args) throws IOException, RunnerException {
    Options options = new OptionsBuilder().include(HashTask.class.getSimpleName()).build();
    new Runner(options).run();
  }
}
