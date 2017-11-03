package cn.ennwifi.guava.thread.create;

import java.util.concurrent.Callable;

/**
 * @author zhangbo
 *
 */
public class Callable1 implements Callable<Integer> {

  /*
   * (non-Javadoc)
   * 
   * @see java.util.concurrent.Callable#call()
   */
  @Override
  public Integer call() throws Exception {
    int sum = 0;
    for (int i = 0; i < 100; i++) {
      System.out.println(Thread.currentThread().getName() + "-" + i);
      sum += i;
    }
    Thread.sleep(5000);
    return sum;
  }

}
