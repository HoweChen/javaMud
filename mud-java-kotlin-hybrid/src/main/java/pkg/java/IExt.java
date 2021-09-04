package pkg.java;

public interface IExt {

  default String getClassName(ExtEnum e) {
    return e.getType().getType().getTypeName();
  }
}
