import static cn.hutool.http.Header.USER_AGENT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author yuhaochen
 */
@Slf4j
public class WebRequestFactory {

  private static final OkHttpClient HTTP_CLIENT = initHttpClient();
  private static final ObjectMapper MAPPER =
      new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

  public static SimpleWebRequest simpleWebRequest =
      new Retrofit.Builder()
          .client(HTTP_CLIENT)
          .baseUrl("https://jsonplaceholder.typicode.com")
          .addConverterFactory(JacksonConverterFactory.create(MAPPER))
          .build()
          .create(SimpleWebRequest.class);

  private static OkHttpClient initHttpClient() {
    Dispatcher dispatcher = new Dispatcher();
    dispatcher.setMaxRequests(100);
    dispatcher.setMaxRequestsPerHost(10);

    Interceptor interceptor =
        chain -> {
          Request request = chain.request();
          request =
              request
                  .newBuilder()
                  .addHeader(
                      USER_AGENT.getValue(),
                      "Mozilla/5.0 (Linux; Android 7.1.1; MI 6 Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/043807 Mobile Safari/537.36 MicroMessenger/6.6.1.1220(0x26060135) NetType/4G Language/zh_CN MicroMessenger/6.6.1.1220(0x26060135) NetType/4G Language/zh_CN miniProgram")
                  .addHeader("X-Forwarded-For", "127.0.0.1")
                  .addHeader("X-Real-IP", "127.0.0.1")
                  .method(request.method(), request.body())
                  .build();
          log.info("Request URL: {}", request.url());
          Response proceed = chain.proceed(request);
          if (!proceed.isSuccessful()) {
            log.error("无法请求远端");
          } else {
            log.info("请求远端成功");
            if (proceed.body() != null) {
              log.info("Remote response: {}", proceed.peekBody(1024).string());
            }
          }

          return proceed;
        };

    return new OkHttpClient.Builder()
        .connectionPool(new ConnectionPool(20, 10000, TimeUnit.MILLISECONDS))
        .dispatcher(dispatcher)
        .addInterceptor(interceptor)
        .build();
  }
}
