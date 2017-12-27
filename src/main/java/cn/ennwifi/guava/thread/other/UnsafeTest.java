package cn.ennwifi.guava.thread.other;

import java.lang.reflect.Field;
import java.util.Arrays;

import sun.misc.Unsafe;


/**
 * @author zhangbo
 *
 */
public class UnsafeTest {

  public static void main(String[] args) throws Exception {
    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
    theUnsafe.setAccessible(true);
    Unsafe unsafe = (Unsafe) theUnsafe.get(null);
    System.out.println(unsafe);

    byte[] b = new byte[10];
    System.out.println(Arrays.toString(b));

    int arrayBaseOffset = unsafe.arrayBaseOffset(b.getClass());
    System.out.println(arrayBaseOffset);

    int arrayIndexScale = unsafe.arrayIndexScale(b.getClass());
    System.out.println(arrayIndexScale);

    int i = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
    System.out.println(i);
    Long l = 1l;
    Long s = (l << i) + arrayBaseOffset;
    System.out.println(s);

    unsafe.putByte(b, s, (byte) 1);
    System.out.println(Arrays.toString(b));

    unsafe.compareAndSwapObject(b, s, (byte) 1, (byte) 2);
    System.out.println(Arrays.toString(b));
  }

}
