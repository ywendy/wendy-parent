package com.wendy.spring.schema;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/11/15.
 */
public class SchemaTest {


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-people.xml");
        People people = (People) ctx.getBean("tonyID");

        System.out.println("peopele:"+people);
    }

}
