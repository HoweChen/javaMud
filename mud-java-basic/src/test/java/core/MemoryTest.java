package core;

import org.junit.jupiter.api.Test;

public class MemoryTest {

  @Test
  public void givenString_whenSetAsParameterAndChange_thenShouldNotChange() {
    // given
    String str = "haha";

    // when
    changeString(str);

    // then
    System.out.println(str);
  }

  @Test
  public void givenInnerClassAndSetAsParameter_whenChange_thenShouldChange() {
    // given
    InnerClass innerClass = new InnerClass();
    innerClass.setName("haha");

    // when
    changeInnerClass(innerClass);

    // then
    System.out.println(innerClass.getName());
  }

  private void changeInnerClass(InnerClass innerClass) {
    innerClass.setName("Fuck");
  }

  private void changeString(String str) {
    str.codePoints();
    str = "hehe";
  }

  private static class InnerClass {

    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
