package com.wendy.base.loader;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

/**
 * @author Administrator
 * @date 2018/4/10
 */
public class CustomClassLoader extends ClassLoader {
    private String name;

    public CustomClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomClassLoader{" +
                "name='" + name + '\'' +
                '}';
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        byte[] data = null;

        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("pkg/User.class");
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            int line;
            while ((line = inputStream.read()) != -1) {
                byteArrayOutputStream.write(line);
            }
            data = byteArrayOutputStream.toByteArray();

        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return this.defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) {
        CustomClassLoader loader = new CustomClassLoader(CustomClassLoader.class.getClassLoader(), "wendy");

        try {
            Class<?> clazz = loader.loadClass("com.wendy.base.loader.User");

            Constructor constructor = clazz.getConstructor(String.class, Long.class, int.class);
            Object user = constructor.newInstance("tony", 1L, 20);

            //User{name='tony', id=1, age=20}
            System.out.println(user.toString());

                /**
                 * 1.CustomClassLoader{name='wendy'}
                 * 2.sun.misc.Launcher$AppClassLoader@75b84c92
                 * 3.sun.misc.Launcher$ExtClassLoader@74a14482
                 * 4.null
                 */
            System.out.println(clazz.getClassLoader().toString());
            System.out.println(clazz.getClassLoader().getParent().toString());
            System.out.println(clazz.getClassLoader().getParent().getParent().toString());
            System.out.println(clazz.getClassLoader().getParent().getParent().getParent());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
