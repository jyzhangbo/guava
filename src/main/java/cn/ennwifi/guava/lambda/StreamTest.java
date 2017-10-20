package cn.ennwifi.guava.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangbo
 *
 */
public class StreamTest {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    list.add("bbc");
    list.add("ccc");
    list.add("ddd");
    List<String> list2 = list.stream().map(name -> name.toUpperCase()).filter(name -> name.startsWith("B")).distinct()
        .collect(Collectors.toList());

    System.out.println(list);
    System.out.println(list2);
  }

}
