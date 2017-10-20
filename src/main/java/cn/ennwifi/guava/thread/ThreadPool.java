package cn.ennwifi.guava.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangbo
 *
 */
public class ThreadPool {

  public static void main(String[] args) {

    ExecutorService executorService = Executors.newCachedThreadPool();

    // 可缓存线程池,如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
    for (int i = 0; i < 10; i++) {
      executorService.execute(() -> System.out.println("Cached" + Thread.currentThread().getName()));
    }

    // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待,因为线程池大小为2，所以没两秒打印2个
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
    for (int i = 0; i < 10; i++) {
      newFixedThreadPool.execute(() -> {
        System.out.println("Fixed" + Thread.currentThread().getName());
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    // 创建一个定长线程池，支持定时及周期性任务执行。
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
    // 延迟执行，推迟2s执行
    scheduledThreadPool.schedule(() -> System.out.println("scheduled" + Thread.currentThread().getName()), 2,
        TimeUnit.SECONDS);

    // 定期执行，推迟2s后，每2s执行一次
    scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("scheduled" + Thread.currentThread().getName()), 2,
        2, TimeUnit.SECONDS);

    // 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
    ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 10; i++) {
      newSingleThreadExecutor.execute(() -> System.out.println("single" + Thread.currentThread().getName()));
    }

  }

}
