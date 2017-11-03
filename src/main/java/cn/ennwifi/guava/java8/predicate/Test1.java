package cn.ennwifi.guava.java8.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author zhangbo
 *
 */
public class Test1 {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");

    eval(list, name -> true);
    eval(list, name -> name.startsWith("a"));

  }

  public static void eval(List<String> list, Predicate<String> predicate) {

    for (String name : list) {
      if (predicate.test(name)) {
        System.out.println(name);
      }
    }

  }

}
