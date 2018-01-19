package com.wendy.structures.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by me on 2017/9/5.
 */
public class LinkedHashMapLRUCache<K, V> implements LRUCache<K, V> {

    private int cacheSize;
    private LinkedHashMap<K, V> map;


    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    public LinkedHashMapLRUCache(int capacity) {
        this.cacheSize = capacity;
        this.map = new LinkedHashMap<K, V>(capacity) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > cacheSize;
            }
        };
    }

    public void clear() {
        writeLock.lock();
        try{

            map.clear();
        }finally {
            writeLock.unlock();
        }
    }


    @Override
    public V get(K key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void put(K key, V value) {
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }

    }

    public static void main(String[] args) {
        LinkedHashMapLRUCache<String, Integer> cache = new LinkedHashMapLRUCache<>(2);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);

        System.out.println("a:" + cache.get("a"));
        System.out.println("b:" + cache.get("b"));
        System.out.println("c:" + cache.get("c"));
        System.out.println("d:" + cache.get("d"));

        Random random = new Random();

        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 30) + random.nextInt(10));

    }

}
