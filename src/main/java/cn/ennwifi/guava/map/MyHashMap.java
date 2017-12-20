package cn.ennwifi.guava.map;

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

    return value;
  }


  private Node[] resize() {
    if (nodes == null) {
      nodes = new Node[8];
    } else {
      int size = nodes.length;
      size = size * 2;
      nodes = new Node[size];
    }
    return nodes;
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
