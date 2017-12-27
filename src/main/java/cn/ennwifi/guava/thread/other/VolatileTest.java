package cn.ennwifi.guava.thread.other;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangbo
 *
 */
public class VolatileTest {

  public volatile int i = 0;
  Lock lock = new ReentrantLock();

  public void inc() {
    lock.lock();
    try {
      i++;
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {

    VolatileTest test = new VolatileTest();
    for (int j = 0; j < 10; j++) {
      new Thread(() -> {
        for (int m = 0; m < 1000; m++) {
          test.inc();
        }

      }).start();
    }

    while (Thread.activeCount() > 1) {
      Thread.yield();
    }
    System.out.println(test.i);
  }
}
