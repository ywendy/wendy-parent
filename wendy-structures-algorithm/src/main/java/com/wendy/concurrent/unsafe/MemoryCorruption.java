package com.wendy.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 破坏内存
 * 修改已经存在的对象的内存中的数据
 * Guard对象已经存在内存中，且变量是私有的，可以通过Unsafe类进行修改其私有变量的值
 *
 * doc:http://mishadoff.com/blog/java-magic-part-4-sun-dot-misc-dot-unsafe/
 *
 * <p>
 * 2017/12/19.
 */
public class MemoryCorruption {

    public static void main(String[] args) throws NoSuchFieldException {
        Guard guard = new Guard();
        Guard guard1 = new Guard();
        System.out.println("before unsafe access:" + guard.giveAccess());//false ,no access.


        Unsafe unsafe = UnsafeUtil.getUnsafe();
        Field accessFeild = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(accessFeild), 35);
        System.out.println("unsafe access:" + guard.giveAccess());
//内存中guard1 在guard旁边，可以直接加16 进行修改

        unsafe.putInt(guard, 16 + unsafe.objectFieldOffset(accessFeild), 35);
        System.out.println(guard1.giveAccess());


    }

    //


}


class Guard {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
        return 35 == ACCESS_ALLOWED;
    }
}
