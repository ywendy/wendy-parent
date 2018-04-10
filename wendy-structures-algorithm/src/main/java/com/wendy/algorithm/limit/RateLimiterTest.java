package com.wendy.algorithm.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/2.
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(2);
        while (true){
            rateLimiter.acquire();
            System.out.println(new Date());

        }
    }

}
