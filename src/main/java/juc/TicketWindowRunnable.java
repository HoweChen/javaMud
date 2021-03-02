package juc;


public class TicketWindowRunnable implements Runnable {

  private int index = 1; //不做 static修饰
  private final static int MAX = 50;

  @Override
  public void run() {
    while (index < MAX) {
      System.out.println(Thread.currentThread() + "的号码是:" + (index++));
//      try {
//        Thread.sleep(100);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }

    }
  }
}
