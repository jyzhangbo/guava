package cn.ennwifi.guava.java8style;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhangbo
 *
 */
public class Test1 {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("enn");
    list.add("baidu");
    list.add("alibaba");
    list.add("tengxun");
    list.add("jingdong");
    Test1 test = new Test1();
    test.sortJava8(list);
    System.out.println(list);

  }

  public void sortJava8(List<String> list) {
    Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
  }

  public void sortJava7(List<String> list) {
    Collections.sort(list, new Comparator<String>() {
      @Override
      public int compare(String s1, String s2) {
        return s1.compareTo(s2);
      }
    });
  }

}
