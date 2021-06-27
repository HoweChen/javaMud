package juc;

/** @author howechen */
public class ThreadTemplate {

  public final void print() {
    System.out.println("#########");
    printInfo();
    System.out.println("#########");
  }

  protected void printInfo() {}
}
