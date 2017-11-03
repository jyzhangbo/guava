package cn.ennwifi.guava.thread.create;

/**
 * @author zhangbo
 *
 */
public class Thread2 extends Thread {


  public Thread2(Runnable runnable) {
    super(runnable);
  }

  public void run() {
    System.out.println(Thread.currentThread().getName() + ";thread2");
  }

}
