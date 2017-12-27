package cn.ennwifi.guava.set;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Objects;

import sun.misc.Unsafe;

/**
 * @author zhangbo
 *
 */
public class MyConcurrentHashMap {

  static final int HASH_BITS = 0x7fffffff;

  static final int spread(int h) {
    return (h ^ (h >>> 16)) & HASH_BITS;
  }

  static final Node tabAt(Node[] tab, int n) {
    return (Node) U.getObjectVolatile(tab, ((long) n << ASHIFT) + ABASE);
  }

  static final boolean casTabAt(Node[] tab, int n, Node c, Node v) {
    return U.compareAndSwapObject(tab, ((long) n << ASHIFT) + ABASE, c, v);
  }

  // 赋值
  public String put(String key, String value) {
    if (key == null || value == null) {
      throw new NullPointerException("间和值不能为空");
    }
    // 计算key的hash
    int hash = spread(key.hashCode());
    int binCount = 0;
    for (Node[] tab = table;;) {
      Node f;
      int n, i, fh;
      if (tab == null || (n = tab.length) == 0) {
        // 如果table为空，则初始化
        initTab();
      } else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) { // 查询数组第i个地址的值，如果为空，则新建一个node赋值进去
        if (casTabAt(tab, i, null, new Node(hash, key, value, null))) {
          break;
        }
      } else if ((fh = f.hash) == -1) {
        tab = helpTransfer(tab, f);
      } else {
        String oldVal = null;
        synchronized (f) {
          if (tabAt(tab, i) == f) {
            if (fh > 0) {
              binCount = 1;
              for (Node e = f;; ++binCount) {
                String ek;
                if (e.hash == hash && ((ek = e.key) == key || (ek != null) && ek.equals(key))) {
                  oldVal = e.value;
                  e.value = value;
                  break;
                }

                Node pred = e;
                if ((e = e.nextNode) == null) {
                  pred.nextNode = new Node(hash, key, value, null);
                  break;
                }
              }
            }
          }
        }
      }
    }

    return value;
  }

  private Node[] helpTransfer(Node[] tab, Node f) {
    return null;
  }

  volatile int sizeCtl;

  // 初始化table
  private Node[] initTab() {
    Node[] tab;
    int sc;
    while ((tab = table) == null || tab.length == 0) {
      if ((sc = sizeCtl) < 0) {// 判断sizeCtl是否小于0，sizeCtl是-1标示其他线程正在初始化
        Thread.yield();
      } else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {// 将sizeCtl赋值为-1，然后进入table的初始化流程
        if ((tab = table) == null || tab.length == 0) {
          try {
            int n = (sc > 0) ? sc : 16;// 初始容量为16
            Node[] nt = new Node[n];
            table = tab = nt;
            sc = n - (n >>> 2);
          } finally {
            sizeCtl = sc;
          }
          break;
        }
      }
    }

    return tab;
  }

  private static final long SIZECTL;
  private static final long ABASE;
  private static final int ASHIFT;
  private static final Unsafe U;
  static {
    try {
      Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      U = (Unsafe) theUnsafe.get(null);

      Class<?> k = MyConcurrentHashMap.class;
      SIZECTL = U.objectFieldOffset(k.getDeclaredField("sizeCtl"));

      Class<?> ak = Node[].class;
      ABASE = U.arrayBaseOffset(ak);
      int scale = U.arrayIndexScale(ak);
      ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  volatile Node[] table;

  private class Node {
    final int hash;
    final String key;
    volatile String value;
    volatile Node nextNode;

    Node(int hash, String key, String value, Node nextNode) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.nextNode = nextNode;
    }

    public String getKey() {
      return key;
    }

    public String getValue() {
      return value;
    }

    public int hashCode() {
      return key.hashCode() ^ value.hashCode();
    }

    public String toString() {
      return key + "=" + value;
    }

    public boolean equals(Object o) {
      String k, v;
      Node e;
      return ((o instanceof Node) && (k = (e = ((Node) o)).getKey()) != null && (v = e.getValue()) != null
          && Objects.equal(k, key) && Objects.equal(v, value));
    }

  }

  public static void main(String[] args) {
    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    map.put("name", "zhangbo1");
    System.out.println(spread("name".hashCode()));
    map.put("age", "342");
    System.out.println(spread("age".hashCode()));
    map.put("address1", "beijing3");
    System.out.println(spread("address1".hashCode()));
    map.put("address2", "beijing4");
    System.out.println(spread("address2".hashCode()));
    map.put("address3", "beijing5");
    System.out.println(spread("address3".hashCode()));
    map.put("address4", "beijing6");
    System.out.println(spread("address4".hashCode()));
    map.put("address5", "beijing7");
    System.out.println(spread("address5".hashCode()));
    map.put("address6", "beijing8");
    System.out.println(spread("address6".hashCode()));
    map.put("address7", "beijing9");
    System.out.println(spread("address7".hashCode()));
    map.put("address8", "beijing10");
    System.out.println(spread("address8".hashCode()));
    map.put("address9", "beijing11");
    System.out.println(spread("address9".hashCode()));
    map.put("address10", "beijing12");
    System.out.println(spread("address10".hashCode()));
    map.put("address11", "beijing13");
    System.out.println(spread("address11".hashCode()));
    map.put("address12", "beijing14");
    System.out.println(spread("address12".hashCode()));
    map.put("address13", "beijing15");
    System.out.println(spread("address13".hashCode()));

    System.out.println(map.toString());
  }

}
