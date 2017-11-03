package cn.ennwifi.guava.thread.create;

/**
 * @author zhangbo
 *
 */
public class Thread1 extends Thread {

  public void run() {
    System.out.println(Thread.currentThread().getName() + ";thread");
  }

}
