package com.wendy.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Administrator on 2017/11/15.
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("people",new PeopleBeanDefinitionParser());
    }
}
