package pkg.java;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

  public static void main(String[] args) {
    ObjectMapper mapper = new ObjectMapper();
    String oneJson = "{\n" + "    \"name\":\"yuhaochen\",\n" + "    \"value\":10\n" + "}";
    String twoJson = "{\n" + "    \"name\":\"lilith\",\n" + "    \"value\":20\n" + "}";

//    System.out.println(DefaultJsonUtil.convert(oneJson,ExtEnum.ONE));
  }
}
