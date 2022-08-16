package core;

public class TryCatchTest {

  public static void main(String[] args) {
    String a = "haha";
    try {
      a = "world";
    } finally {
      System.out.println(a);
      a = "fuck";
      System.out.println(a);
    }
  }
}
