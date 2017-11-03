package cn.ennwifi.guava.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhangbo
 *
 */
public class Style {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("enn");
    list.add("baidu");
    list.add("alibaba");
    list.add("tengxun");
    list.add("jingdong");
    Style test = new Style();
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
