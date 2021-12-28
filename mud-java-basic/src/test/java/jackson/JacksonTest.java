package jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JacksonTest {

  @Test
  public void givenString_whenWriteToString_ThenShouldSucceed() {
    String test = "12345";
    ObjectMapper mapper = new ObjectMapper();
    try {
      System.out.println(mapper.writeValueAsString(test));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
