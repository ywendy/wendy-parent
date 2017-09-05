package com.wendy.structures.lru;

import java.util.Hashtable;

/**
 * 将Cache的所有位置都用双连表连接起来，当一个位置被命中之后，就将通过调整链表的指向，将该位置调整到链表头的位置，新加入的Cache直接加到链表头中。
 * 这样，在多次进行Cache操作后，最近被命中的，就会被向链表头方向移动，而没有命中的，而想链表后面移动，链表尾则表示最近最少使用的Cache。
 * 当需要替换内容时候，链表的最后位置就是最少被命中的位置，我们只需要淘汰链表最后的部分即可
 * <p>
 * Created by me on 2017/9/5.
 */
public class DoublyLinkedListLRUCache<K, V> implements LRUCache<K, V> {


    private int cacheSize;
    private Hashtable<K, CacheNode> nodes; //缓存容器
    private int currentSize;
    private CacheNode first;
    private CacheNode last;

    class CacheNode {
        CacheNode prev;//前一个节点
        CacheNode next;//下一个节点
        K key; //键
        V value;//值

        CacheNode() {

        }

        @Override
        public String toString() {
            return "CacheNode{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }


    public DoublyLinkedListLRUCache(int capacity) {
        this.cacheSize = capacity;
        this.currentSize = 0;
        this.nodes = new Hashtable<>();
    }


    @Override
    public V get(K key) {
        CacheNode node = nodes.get(key);
        if (node != null) {
            moveToHead(node);
            return node.value;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {

        CacheNode node = nodes.get(key);
        if (node == null) {
            if (currentSize >= cacheSize) {
                if (last != null) {
                    nodes.remove(last.key);
                }
                removeLast();
            } else {
                currentSize++;
            }
            node = new CacheNode();
        }
        node.value = value;
        node.key = key;
        moveToHead(node);
        nodes.put(key, node);

    }


    public V remove(K key) {
        CacheNode node = nodes.get(key);
        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (last == node) {
                last = node.prev;
            }
            if (first == node) {
                first = node.next;
            }
        }
        nodes.remove(key);
        return node.value;
    }


    public void clear() {
        first = null;
        last = null;
        nodes.clear();
    }


    private void removeLast() {
        System.out.println("remove cache last:" + last);
        if (last != null) {
            if (last.prev != null) {
                last.prev.next = null;
            } else {
                first = null;
            }
            last = last.prev;
        }
    }


    private void moveToHead(CacheNode node) {
        if (node == first) {
            return;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }

        if (node == last) {
            last = node.prev;
        }

        if (first != null) {
            node.next = first;
            first.prev = node;
        }

        first = node;
        node.prev = null;
        if (last == null) {
            last = first;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedListLRUCache<String, Integer> cache = new DoublyLinkedListLRUCache<>(3);
        cache.put("a", 1);
        cache.put("b", 1);
        cache.put("c", 1);
        cache.put("d", 1);
        cache.put("e", 1);
        cache.put("f", 1);
    }


}
