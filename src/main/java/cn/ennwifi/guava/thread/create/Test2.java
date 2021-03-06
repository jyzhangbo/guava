package cn.ennwifi.guava.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhangbo
 *
 */
public class Test2 {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Callable<Integer> callable = new Callable1();
    FutureTask<Integer> futureTask = new FutureTask<>(callable);
    Thread thread = new Thread(futureTask);

    System.out.println(thread.getState().name());

    thread.start();

    System.out.println(thread.getState().name());

    System.out.println(futureTask.get());

    Thread.sleep(1000);
    System.out.println(thread.getState().name());
  }

}
