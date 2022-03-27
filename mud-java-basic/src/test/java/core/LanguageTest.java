package core;

import org.junit.jupiter.api.Test;

public class LanguageTest {

  @Test
  public void givenTest_whenChangeInFinally_thenShouldReturnCorrectOne() {
    System.out.println(test());
  }

  private String test() {

    String result = "haha";
    try {
      result = "bitch";
      return result;
    } finally {
      if (result.equals("bitch")) {
        result = "fuck";
        System.out.println(result);
        System.out.println("haha, again");
      }
    }
  }
}
