package com.wendy.base.loader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;

/**
 * @author Administrator
 * @date 2018/4/10
 */
public class BreakParentClassLoader extends ClassLoader {
    private String name;

    public BreakParentClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return "BreakParentClassLoader{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        Class<?> clazz = null ;
        ClassLoader systemClassLoader = getSystemClassLoader();
        try {
            clazz = systemClassLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            //skip it
        }

        if (clazz != null) {
            return clazz;
        }

        clazz = findClass(name);


        return clazz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = null;
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("pkg/User.class");
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            int line;
            while ((line = inputStream.read()) != -1) {
                baos.write(line);
            }
            data = baos.toByteArray();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return this.defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) {
        BreakParentClassLoader breakParentClassLoader = new BreakParentClassLoader(BreakParentClassLoader.class.getClassLoader(),"wendy-001");
        try {
            Class<?> clazz = breakParentClassLoader.loadClass("com.wendy.base.loader.User");

            Constructor constructor = clazz.getConstructor(String.class, Long.class, int.class);
            Object user = constructor.newInstance("tony", 1L, 20);

            //User{name='tony', id=1, age=20}
            System.out.println(user.toString());
            //加载自定义的String 类




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
