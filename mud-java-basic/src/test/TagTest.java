import org.junit.jupiter.api.Test;

public class TagTest {

  @Test
  public void givenTag_whenBreakTag_thenShouldPrintFinally(){

    LOOP:
    try{
      System.out.println("In the try block");
      break LOOP;
    } finally{
      System.out.println("haha");
    }
  }

}
