package cn.ennwifi.guava.lambda;

import java.util.stream.Stream;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * lambda表达式访问外部资源.
 * 
 * @author zhangbo
 *
 */
public class Test2 {

  public static void main(String[] args) {
    String[] array = {"a", "b", "c"};
    for (Integer i : Lists.newArrayList(1, 2, 3)) {
      Stream.of(array).map(name -> Strings.padEnd(name, i, '@')).forEach(System.out::println);
    }
  }

}
