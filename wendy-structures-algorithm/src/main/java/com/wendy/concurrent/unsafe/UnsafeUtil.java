package com.wendy.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/12/19.
 */
public class UnsafeUtil {

    private static Unsafe UNSAFE;


    static {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            UNSAFE = (Unsafe) unsafeField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            UNSAFE = null;
        }
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }


}
