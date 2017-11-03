package cn.ennwifi.guava.thread.create;

/**
 * 线程的创建.
 * 
 * @author zhangbo
 *
 */
public class Test1 {

  public static void main(String[] args) throws InterruptedException {
    Thread1 test1 = new Thread1();
    Thread1 test2 = new Thread1();
    Thread1 test3 = new Thread1();
    test1.start();
    test2.start();
    test3.start();
    Thread.sleep(100);

    Thread thread1 = new Thread(new Runnable1());
    Thread thread2 = new Thread(new Runnable1());
    Thread thread3 = new Thread(new Runnable1());
    thread1.start();
    thread2.start();
    thread3.start();

    Thread2 thread21 = new Thread2(new Runnable1());
    Thread2 thread22 = new Thread2(new Runnable1());
    Thread2 thread23 = new Thread2(new Runnable1());
    thread21.start();
    thread22.start();
    thread23.start();
  }
}
