package cn.ennwifi.guava.set;

import com.google.common.base.Objects;

/**
 * 模拟hashmap,暂时没有使用红黑树结构，只是用了链表和数据.
 * 
 * @author zhangbo
 *
 */
public class MyHashMap {

  // 存储元素的数据
  Node[] nodes;

  // 获取数据
  public String get(String key) {
    Node[] tab;
    Node first, e;
    int n, hash;
    String k;
    hash = hash(key);
    if (((tab = nodes) != null) && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
      if (first.hashcode == hash && ((k = first.key) == key || (key != null && key.equals(k)))) {
        // 如果第一个元素的key就一样，则直接返回
        return first.value;
      }

      if ((e = first.next) != null) {
        // 在链表中查找
        do {
          if (e.hashcode == hash && ((k = e.key) == key || (key != null && key.equals(k))))
            return e.value;
        } while ((e = e.next) != null);
      }
    }

    return null;
  }

  // 存放数据
  public String put(String key, String value) {
    Node[] tab;
    Node p;
    int n, i, hash;
    hash = hash(key);

    if ((tab = nodes) == null || (n = tab.length) == 1) {
      // 第一次进来需要初始化nodes
      n = (tab = resize()).length;
    }

    if ((p = nodes[i = (n - 1) & hash]) == null) {
      // 通过hash计算元素存放位置，如果位置为空，则直接放入，此时还是数组
      nodes[i] = new Node(key, value, hash, null);
    } else {
      Node e;
      String k;
      // 已经存在数据，发送数据冲撞
      if (p.hashcode == hash && (((k = p.key) == key) || (key != null && key.equals(k)))) {
        // 如果存放的第一个元素和要存的元素 hash和key相等
        e = p;
      } else {
        while (true) {
          if ((e = p.next) == null) {
            p.next = new Node(key, value, hash, null);
            break;
          }
          if (p.hashcode == hash && (((k = p.key) == key) || (key != null && key.equals(k)))) {
            break;
          }
          p = e;
        }
      }

      if (e != null) {
        // 如果找到key值、hash值一样的,将原值返回，赋新值
        String oldv = e.value;
        e.value = value;
        return oldv;
      }
    }

    if (++size > threshold) {
      resize();
    }
    return value;
  }

  int size;
  static final int MAXIMUM_CAPACITY = 1 << 30;
  static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
  static final float DEFAULT_LOAD_FACTOR = 0.75f;
  final float loadFactor = 0.75f;
  int threshold;

  /**
   * 1.第一次初始化，oldTab，nodes为null
   * 
   */
  private Node[] resize() {
    Node[] oldTab = nodes;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;// 16
    int oldThr = threshold;// 12
    int newCap, newThr = 0;
    if (oldCap > 0) {
      if (oldCap >= MAXIMUM_CAPACITY) {
        threshold = Integer.MAX_VALUE;
        return oldTab;
      } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
        newThr = oldThr << 1; // double threshold
    } else if (oldThr > 0) { // initial capacity was placed in threshold
      newCap = oldThr;
    } else {
      // 第一次初始化，容量初始值为16，扩容临界值为16*0.75=12
      newCap = DEFAULT_INITIAL_CAPACITY;
      newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
      float ft = (float) newCap * loadFactor;
      newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes", "unchecked"})
    Node[] newTab = (Node[]) new Node[newCap];
    nodes = newTab;
    if (oldTab != null) {
      for (int j = 0; j < oldCap; ++j) {
        Node e;
        if ((e = oldTab[j]) != null) {
          oldTab[j] = null;
          if (e.next == null) {
            // 该node只有一个数，直接重新确定位置
            newTab[e.hashcode & (newCap - 1)] = e;
          } else { // preserve order
            Node loHead = null, loTail = null;
            Node hiHead = null, hiTail = null;
            Node next;
            do {
              next = e.next;
              if ((e.hashcode & oldCap) == 0) {
                // 该节点还在原来的位置
                if (loTail == null) {
                  loHead = e;
                } else {
                  loTail.next = e;
                }
                loTail = e;
              } else {
                // 该节点位置发生变化
                if (hiTail == null)
                  hiHead = e;
                else
                  hiTail.next = e;
                hiTail = e;
              }
            } while ((e = next) != null);
            if (loTail != null) {
              loTail.next = null;
              newTab[j] = loHead;
            }
            if (hiTail != null) {
              hiTail.next = null;
              newTab[j + oldCap] = hiHead;
            }
          }
        }
      }
    }
    return newTab;
  }


  public int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }

  private class Node {
    final String key;
    String value;
    Node next;
    final int hashcode;

    Node(String key, String value, int hashcode, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
      this.hashcode = hashcode;
    }

    public String getKey() {
      return key;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return key + "=" + value;
    }

    public final int hashCode() {
      return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Node) {
        Node node = (Node) obj;
        if (Objects.equal(key, node.getKey()) && Objects.equal(value, node.getValue())) {
          return true;
        }
      }

      return false;
    }

  }

}
