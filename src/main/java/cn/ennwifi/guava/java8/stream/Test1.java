package cn.ennwifi.guava.java8.stream;

import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * Stream创建的方法.
 * 
 * @author zhangbo
 *
 */
public class Test1 {
  public static void main(String[] args) {
    List<Integer> nums = Lists.newArrayList(1, null, 2, 3, null, 4, 5);
    // 串行
    long count = nums.stream().filter(num -> num != null).count();
    System.out.println(count);
    // 并行
    long count2 = nums.parallelStream().filter(num -> num != null).count();
    System.out.println(count2);


    // 通过of方法创建stream
    Stream.of(2, 3, 4, 5).forEach(System.out::println);
    Stream.of("aaa").forEach(System.out::println);

    // 使用generateor方法
    Stream.generate(Math::random).limit(10).forEach(System.out::println);

    // 使用iterate
    Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
  }

}
