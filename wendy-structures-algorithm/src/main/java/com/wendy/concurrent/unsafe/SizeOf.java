package com.wendy.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * 使用ObjectFieldOffset 方法，我们可以实现一个类似于c函数sizeof的函数
 * <p>
 * 2017/12/19.
 */
public class SizeOf {

    public static void main(String[] args) {
        B b = new B();
        System.out.println(sizeOf(b));


        // System.out.println(sizeOf(b,""));
    }

    /**
     * In fact, for good, safe and accurate sizeof function better to use java.lang.instrument package,
     * but it requires specifyng agent option in your JVM.
     * -javaagent:jarpath[=options]
     *
     * @param o
     * @param empty
     * @return
     */
    public static long sizeOf(Object o, String empty) {

        Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getAddress(normalize(unsafe.getInt(o, 4L)) + 12L);
    }


    private static long normalize(int value) {
        if (value > 0) {
            return value;
        }
        return (0L >>> 32) & value;
    }

    //非static Field 大小
    public static long sizeOf(Object o) {
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        HashSet<Field> fields = new HashSet<>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        long maxSize = 0L;
        for (Field field : fields) {
            long offset = unsafe.objectFieldOffset(field);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }
        return ((maxSize / 8) + 1) * 8;
    }


}


class B {
    private long a;
    private static int b;
}

