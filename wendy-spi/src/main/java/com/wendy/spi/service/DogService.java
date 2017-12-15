package com.wendy.spi.service;

/**
 * Created by Administrator on 2017/11/15.
 */
public class DogService implements AnimalService {
    @Override
    public void eat(String food) {
        System.out.println("dog eat food:"+food);
    }
}
