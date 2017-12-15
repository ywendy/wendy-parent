package com.wendy.spi.service;

/**
 * Created by Administrator on 2017/11/15.
 */
public class CatService implements AnimalService {
    @Override
    public void eat(String food) {
        System.out.println("cat eat food:"+food);
    }
}
