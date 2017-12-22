package cn.ennwifi.guava.set;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangbo
 *
 */
public class MyArrayList {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("zhangbo");
    list.add("16");
    list.add("beijing");

    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
  }

}
