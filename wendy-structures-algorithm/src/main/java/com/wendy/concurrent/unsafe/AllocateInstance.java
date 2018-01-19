package com.wendy.concurrent.unsafe;

/**
 * 创建对象三种方式：
 * 1、构造方法直接new对象
 * 2、通过反射获取对象
 * 3、通过Unsafe类的allocateInstance方法创建对象，此时对象创建出来，但是并没有调用构造方法
 *
 * 对于单利模式，是否还是单例，值得思考。
 *
 * <p>
 * 2017/12/19.
 */
public class AllocateInstance {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        //1通过构造方法new对象
        A o1 = new A();
        o1.a("构造方法new出对象"); // print 1
        //通过反射创建对象
        A o2 = A.class.newInstance();
        o2.a("反射创建对象");// print 1

        //通过Unsafe类创建对象
        A o3 = (A) UnsafeUtil.getUnsafe().allocateInstance(A.class);
        o3.a("通过Unsafe类创建对象");
        //this.a=0


    }


}

class A {
    private long a;

    public A() {
        this.a = 1;
    }

    public long a(String way) {
        System.out.println(way + ":" + this.a);
        return this.a;
    }
}

