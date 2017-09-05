package com.wendy.structures.linklist;

/**
 * Created by Administrator on 2017/8/16.
 * <p>
 * <p>
 * +---------------+------------------+
 * |      Data     |       Next       |
 * +---------------+------------------+
 * <p>
 * <p>
 * +------+------+           +-------+------+       +-----+------+-
 * | data1| next |---------->| data2 | next |------>|item | null |
 * +------+------+           +-------+------+       +-----+------+
 */
public class SingleLinkList<E> {


    private static class Node<E> {
        private Node<E> next;//指针域
        private E value;//数据域

        Node(E value, Node<E> next) {
            this.next = next;
            this.value = value;
        }

        Node(E value) {
            this(value, null);
        }

    }


}
