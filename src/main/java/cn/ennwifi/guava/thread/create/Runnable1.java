package cn.ennwifi.guava.thread.create;

/**
 * @author zhangbo
 *
 */
public class Runnable1 implements Runnable {

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + ";runnable");

  }

}
