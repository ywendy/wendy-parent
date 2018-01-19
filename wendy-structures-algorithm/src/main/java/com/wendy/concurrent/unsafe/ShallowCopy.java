package com.wendy.concurrent.unsafe;

import sun.misc.Unsafe;

/**
 * 简单的浅copy
 * <p>
 * 2017/12/19.
 */
public class ShallowCopy {

    public static void main(String[] args) {

        TT t = new TT();
        t.print();
        TT t1 = (TT) shallowCopy(t);
        t1.print();

    }



    static Object shallowCopy(Object obj) {

        long size = SizeOf.sizeOf(obj);
        long start = toAddress(obj);
        long address = UnsafeUtil.getUnsafe().allocateMemory(size);
        UnsafeUtil.getUnsafe().copyMemory(start, address, size);
        return fromAddress(address);
    }

    static long toAddress(Object obj) {
        Object[] array = new Object[]{obj};
        long baseOffset = UnsafeUtil.getUnsafe().arrayBaseOffset(Object[].class);
        return normalize(UnsafeUtil.getUnsafe().getInt(array, baseOffset));

    }


    private static long normalize(int value) {
        if (value > 0) {
            return value;
        }
        return (0L >>> 32) & value;
    }

    static Object fromAddress(long address) {
        Object[] array = new Object[]{null};
        long baseOffset = UnsafeUtil.getUnsafe().arrayBaseOffset(Object[].class);
        UnsafeUtil.getUnsafe().putLong(array, baseOffset, address);
        return array[0];
    }


}

class TT{
    private int a = 10;

    public void print(){
        System.out.println("a="+a);
    }
}
