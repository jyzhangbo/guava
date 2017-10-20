package cn.ennwifi.guava.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * 汇聚.
 * 
 * @author zhangbo
 *
 */
public class StreamTest3 {

  public static void main(String[] args) {
    ArrayList<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 5, null, 6, 7, 8);

    // 可变汇聚
    ArrayList<Integer> newList = nums.stream().filter(i -> i != null).collect(() -> new ArrayList<Integer>(),
        (list, item) -> list.add(item), (list1, list2) -> list1.addAll(list2));
    System.out.println(newList);

    // 重写的可变汇聚
    List<Integer> collect = nums.stream().filter(i -> i != null).collect(Collectors.toList());
    System.out.println(collect);

    // groupingBy
    Map<Integer, Integer> r =
        nums.stream().filter(i -> i != null).collect(Collectors.groupingBy(p -> p, Collectors.summingInt(p -> 1)));
    System.out.println(r);

    // 其他汇聚 reduce方法
    // 求和
    Integer integer1 = nums.stream().filter(i -> i != null).reduce(0, (a, b) -> a + b);
    Integer integer2 = nums.stream().filter(i -> i != null).reduce(0, Integer::sum);
    System.out.println(integer1);
    System.out.println(integer2);
    // 求最小值
    Integer integer3 = nums.stream().filter(i -> i != null).reduce(Integer::min).get();
    System.out.println(integer3);

    // findFirst
    Optional<Integer> findFirst = nums.stream().filter(i -> i != null).findFirst();
    System.out.println(findFirst.orElse(0));

    // min/max
    List<String> list2 = Arrays.asList("aa", "bbb", "cdes", "adsfasdf");
    OptionalInt max = list2.stream().mapToInt(String::length).max();
    OptionalInt min = list2.stream().mapToInt(String::length).min();
    System.out.println(max.getAsInt());
    System.out.println(min.getAsInt());

    // match
    boolean allMatch = list2.stream().allMatch(name -> name.length() > 2);
    System.out.println(allMatch);
    boolean anyMatch = list2.stream().anyMatch(name -> name.length() > 2);
    System.out.println(anyMatch);
    boolean noneMatch = list2.stream().noneMatch(name -> name.length() > 10);
    System.out.println(noneMatch);

    // groupingBy/partitioningBy
    Map<Integer, List<String>> collect2 = list2.stream().collect(Collectors.groupingBy(String::length));
    System.out.println(collect2.toString());
    Map<Boolean, List<String>> collect3 = list2.stream().collect(Collectors.partitioningBy(s -> s.length() > 2));
    System.out.println(collect3);

  }

}
