package com.wendy.structures.lru;

/**
 * Created by Administrator on 2017/9/5.
 */
public interface LRUCache<K,V> {

    public  V get(K key);


    public void put(K key,V value);
}
