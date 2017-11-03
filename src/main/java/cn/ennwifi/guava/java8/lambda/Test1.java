package cn.ennwifi.guava.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * lambda表达式基本语法.
 * 
 * @author zhangbo
 *
 */
public class Test1 {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("enn");
    list.add("baidu");
    list.add("alibaba");
    list.add("jingdong");
    list.add("tengxun");

    // 声明类型
    List<String> list1 = list.stream().map((String name) -> {
      return name.toUpperCase();
    }).collect(Collectors.toList());

    // 不声明类型
    List<String> list2 = list.stream().map((name) -> {
      return name.toLowerCase();
    }).collect(Collectors.toList());

    // 没有大括号和返回
    List<String> list3 = list.stream().map(name -> name.toUpperCase()).collect(Collectors.toList());

    // 使用方法引用
    List<String> list4 = list.stream().map(String::toLowerCase).collect(Collectors.toList());

    System.out.println(list1);
    System.out.println(list2);
    System.out.println(list3);
    System.out.println(list4);
  }
}
