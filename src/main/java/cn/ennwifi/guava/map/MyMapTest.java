package cn.ennwifi.guava.map;

/**
 * @author zhangbo
 *
 */
public class MyMapTest {

  public static void main(String[] args) {
    MyHashMap map = new MyHashMap();
    map.put("name", "zhangbo");
    map.put("age", "27");
    map.put("address", "beijing");
    map.put("sex", "man");
    System.out.println(map.get("name"));
    System.out.println(map.get("age"));
    System.out.println(map.get("address"));
    System.out.println(map.get("sex"));
  }

}
