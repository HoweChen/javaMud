package juc;

public enum Test {
  PUBLIC(String.class),
  PRIVATE(Integer.class);

  Class<?> clazz;

  Test(Class<?> stringClass) {
    this.clazz = stringClass;
  }

  public static void main(String[] args) {
    System.out.println(Test.PUBLIC.getClazz());
    System.out.println(Test.PRIVATE.getClazz());
  }

  public Class<?> getClazz() {
    return clazz;
  }
}
