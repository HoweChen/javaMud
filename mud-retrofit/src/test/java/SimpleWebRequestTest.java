import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SimpleWebRequestTest {

  @Test
  public void getTest() throws IOException {
    Map<String, Object> body = WebRequestFactory.simpleWebRequest
        .get("1")
        .execute()
        .body();
    log.info(String.valueOf(body));
  }
}
