package cn.ennwifi.guava.thread.create;

/**
 * @author zhangbo
 *
 */
public class YieldTest extends Thread {

  public YieldTest(String name) {
    super(name);
  }

  @Override
  public void run() {
    for (int i = 0; i < 50; i++) {
      System.out.println("" + this.getName() + "-----" + i);
      if (i == 30) {
        this.yield();
      }
    }
  }

  public static void main(String[] args) {
    YieldTest t1 = new YieldTest("zhangsan");
    YieldTest t2 = new YieldTest("lisi");
    t1.setPriority(1);
    t2.setPriority(10);
    t1.start();
    t2.start();
  }

}
