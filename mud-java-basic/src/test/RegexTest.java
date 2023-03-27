import org.junit.jupiter.api.Test;

public class RegexTest {

  @Test
  public void givenNameRegex_whenTest_thenShouldPass(){
    String regex = "^(?=.{2,100}$)(?!\\s)[\\w\\s]+(?<!\\s)$";
  }

}
