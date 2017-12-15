package com.wendy.structures.linklist;

/**
 * 2017/10/27.
 *
 * @author admin
 */
public class Node {

    public int data; //数据域
    public Node next;//指针域

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    /**
     * 打印数据域
     */
    public void display() {
        System.out.print(" " + data + " ");
    }


}
