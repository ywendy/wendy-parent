package com.wendy;

import com.wendy.spi.service.AnimalService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Administrator on 2017/11/15.
 */
public class SpiTest {


    public static void main(String[] args) {
        ServiceLoader<AnimalService> spiLoader = ServiceLoader.load(AnimalService.class);
        Iterator<AnimalService> iterator = spiLoader.iterator();
        while (iterator.hasNext()) {
            iterator.next().eat("苹果");
        }
    }
}
