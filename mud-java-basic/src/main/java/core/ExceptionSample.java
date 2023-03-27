package core;

public class ExceptionSample {

  public static String test() throws Exception {
    System.out.println("Here");
    try {
      return "Haha";
    } catch (Exception ignored) {
    }
    throw new Exception("Fuck");
  }

  public static void main(String[] args) {
    try {
      System.out.println(ExceptionSample.test());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
