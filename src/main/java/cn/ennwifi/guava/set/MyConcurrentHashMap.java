package cn.ennwifi.guava.set;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangbo
 *
 */
public class MyConcurrentHashMap {

  public static void main(String[] args) {
    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    map.put("name", "zhangbo");
    map.put("age", "34");
    map.put("address", "beijing");
  }

}
