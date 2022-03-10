import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author yuhaochen
 */
public interface SimpleWebRequest {

  /**
   * Get
   *
   * @return get result map
   */
  @POST("/todo/{num}")
  Call<Map<String, Object>> get(@Path("num") String num);
}
