package cn.ennwifi.guava.set;

/**
 * @author zhangbo
 *
 */
public class MyMapTest {

  public static void main(String[] args) {
    MyHashMap map = new MyHashMap();

    map.put("sex1", "man");
    map.put("sex15", "man");
    map.put("sex26", "man");
    map.put("sex37", "man");
    map.put("sex48", "man");
    map.put("sex59", "man");
    map.put("zhangbo69", "man");
    map.put("zhang80", "man");
    map.put("zhang81", "man");
    map.put("zhang82", "man");
    map.put("zhang83", "man");
    map.put("zhang84", "man");
    map.put("zhang85", "man");
    System.out.println(map.get("name"));
    System.out.println(map.get("age"));
    System.out.println(map.get("address"));
    System.out.println(map.get("sex"));
  }

}
