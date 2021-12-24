package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author yuhaochen
 */
public class TestClass {

  private String name;
  private int age;

  public TestClass() {
  }

  public TestClass(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("name", name)
        .append("age", age).toString();
  }

  public String toReflectionStr() {
    return ReflectionToStringBuilder.toString(this);
  }

  public String toJacksonStr() {
    try {
      return BenchmarkTask.JACKSON_MAPPER.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      return this.toString();
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
