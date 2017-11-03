package cn.ennwifi.guava.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * 转换的练习.
 * 
 * @author zhangbo
 *
 */
public class StreamTest2 {

  public static void main(String[] args) {
    ArrayList<String> list1 = Lists.newArrayList("aaa", "bbb", "ccc", "ddd", "aaa");
    System.out.println(list1);

    // souted排序
    list1.stream().sorted((a, b) -> b.compareTo(a)).forEach(System.out::print);
    System.out.println();

    // distinct去重
    list1.stream().distinct().forEach(System.out::print);
    System.out.println();

    // filter过滤
    list1.stream().filter(name -> name.startsWith("a")).forEach(System.out::print);
    System.out.println();

    // map转换
    list1.stream().map(String::toUpperCase).forEach(System.out::print);
    System.out.println();

    // flatMap一对多的转换
    Stream<List<Integer>> of = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
    of.flatMap(list -> list.stream()).forEach(System.out::print);
    System.out.println();

    // peek提供消费函数
    list1.stream().peek(System.out::println).count();

    // limit取前n个
    list1.stream().limit(2).forEach(System.out::println);

    // skip丢弃前n个
    list1.stream().skip(2).forEach(System.out::println);

    List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
    System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2)
        .peek(System.out::println).skip(2).limit(4).sum());
  }

}
