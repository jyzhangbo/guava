package cn.ennwifi.guava.optional;

import java.util.Optional;

/**
 * @author zhangbo
 *
 */
public class Test1 {

  public static void main(String[] args) {

    Integer value = null;
    Integer value1 = 2;

    // of创建一个Optional但是参数不能为null，否则抛出NullPointerException
    // Optional<Integer> opt = Optional.of(value);
    Optional<Integer> opt = Optional.of(value1);
    // get如果optional存在则返回值，不存在抛出NoSuchElementException
    System.out.println(opt.get()); // 返回2

    // ofNullable方法可以接受null,是null则返回一个空的Optional
    Optional<Integer> optional = Optional.ofNullable(value);
    // 抛出异常
    // System.out.println(optional.get());

    // isPresent如果存在则返回true，否则返回false
    System.out.println(opt.isPresent()); // true
    System.out.println(optional.isPresent()); // false

    // orElse存在则返回，不存在则返回默认值
    System.out.println(opt.orElse(1)); // 返回2
    System.out.println(optional.orElse(1)); // 返回1

    // orElseGet存在则返回，不存在则调用函数，与orElse类似，就是默认值不一样，一个是传入的字符串，一个是Supplier接口的实现
    System.out.println(optional.orElseGet(() -> 1)); // 1

    // orElseThrow存在则返回，不存在抛出创建的异常
    try {
      System.out.println(optional.orElseThrow(NullPointerException::new));
    } catch (Exception e) {
      System.out.println("空"); // 空
    }
    try {
      System.out.println(opt.orElseThrow(NullPointerException::new)); // 2
    } catch (Exception e) {
      System.out.println("空");
    }

    // 存在则执行方法
    opt.ifPresent(System.out::println); // 2

    // map如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，则创建包含mapping返回值的Optional作为map方法返回值，
    // 否则返回空Optional。
    Optional<Integer> map = opt.map(i -> i + 2);
    System.out.println(map.get()); // 4
    Optional<Integer> map2 = optional.map(i -> i + 1);
    System.out.println(map2.orElse(2)); // 2

    // flatMap
    // flatMap方法与map方法类似，区别在于mapping函数的返回值不同。map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional
    Optional<Integer> flatMap = opt.flatMap(i -> Optional.of(i + 2));
    System.out.println(flatMap.get()); // 4

    // filter通过传入限定条件对Optional实例的值进行过滤
    Optional<Integer> filter = opt.filter(i -> i > 1);
    System.out.println(filter.orElse(1));

  }

}
