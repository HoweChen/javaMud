package guava;

import com.google.common.collect.ImmutableClassToInstanceMap;
import org.junit.jupiter.api.Test;

public class ClassToInstanceMapTest {

  private static final Class<ClassOne> OneClass = ClassOne.class;
  private static final Class<ClassTwo> TwoClass = ClassTwo.class;

  private abstract static class AbsClass {}

  private static class ClassOne extends AbsClass {
    private String test = "0";

    public String getTest() {
      return test;
    }

    public void setTest(String test) {
      this.test = test;
    }
  }

  private static class ClassTwo extends AbsClass {}

  private static ImmutableClassToInstanceMap<AbsClass> im =
      ImmutableClassToInstanceMap.<AbsClass>builder()
          .put(ClassOne.class, new ClassOne())
          .put(ClassTwo.class, new ClassTwo())
          .build();

  @Test
  public void test() {
    ClassOne instance = im.getInstance(OneClass);
    instance.setTest("1");

    ClassOne classTwoInstance = im.getInstance(OneClass);
    System.out.println(classTwoInstance.getTest());
  }
}
