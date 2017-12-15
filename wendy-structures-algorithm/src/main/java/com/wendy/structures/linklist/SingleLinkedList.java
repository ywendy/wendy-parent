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
public class SingleLinkedList {

    public Node head;//链表头

    public SingleLinkedList() {
        this.head = null;
    }

    /***
     * 头插法 ,每次添加数据都会添加到头结点上，头结点向后移动
     * @param data
     */
    public void addByHead(int data) {
        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
        } else {
            Node temp = head;
            node.next = temp;
            head = node;
        }
    }

    /**
     * 尾插法,在链表尾部插入元素
     *
     * @param data
     */
    public void addByTail(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    /**
     * 链表长度.
     * 算法时间复杂度:O(n)
     *
     * @return
     */
    public int size() {
        Node current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    /**
     * 根据关键字搜索链表.
     *
     * @param key
     * @return
     */
    public Node searchNode(int key) {
        if (head == null) {
            return null;
        }

        Node current = head;
        while (current.data != key && current.next != null) {
            current = current.next;
        }
        if (current.data == key) {
            return current;
        }

        return null;
    }

    /**
     * 反转链表
     *
     */

    public void reverseList() {

        Node temp,newList= null;

        while (head!=null){
            temp = head;
            head = head.next;
            temp.next = newList;
            newList = temp;
        }
        head = newList;
    }


    /**
     * 打印链表信息
     */
    public void displayAllNodes() {
        Node current = head;
        while (current != null) {
            current.display();
            current = current.next;
            if (current != null) {
                System.out.print("->");
            }

        }

    }


    public static void main(String[] args) {
        System.out.println("---------头插法---------------");
        SingleLinkedList list = new SingleLinkedList();
        list.addByHead(2);
        list.addByHead(3);
        list.addByHead(4);
        list.addByHead(5);
        list.addByHead(1);
        list.addByHead(-9);
        list.addByHead(-20);
        list.displayAllNodes();

        System.out.println();
        System.out.println("------尾插法------");
        SingleLinkedList list1 = new SingleLinkedList();
        list1.addByTail(1);
        list1.addByTail(2);
        list1.addByTail(3);
        list1.displayAllNodes();

        System.out.println();
        System.out.println("------先头插,在尾插------");
        SingleLinkedList list2 = new SingleLinkedList();
        list2.addByHead(1);
        list2.addByHead(2);
        list2.addByHead(3);
        list2.addByTail(8);
        list2.addByTail(10);
        list2.addByHead(9);
        list2.displayAllNodes();

        System.out.println();
        System.out.println("------链表长度----");
        SingleLinkedList list3 = new SingleLinkedList();

        list3.addByHead(1);
        list3.addByHead(3);
        list3.addByHead(9);

        list3.displayAllNodes();
        System.out.println("  链表长度为:" + list3.size());


        System.out.println("------结点查找--------");
        SingleLinkedList list4 = new SingleLinkedList();

        list4.addByHead(1);
        list4.addByHead(3);
        list4.addByHead(7);
        list4.addByHead(20);
        list4.displayAllNodes();
        System.out.println();
        Node node = list4.searchNode(3);
        if (node != null) {
            System.out.println("找到的结点为:" + node.data);
        } else {
            System.out.println("未找到结点");
        }


        System.out.println();
        System.out.println("------链表反转------");
        SingleLinkedList list5 = new SingleLinkedList();

        list5.addByHead(1);
        list5.addByHead(2);
        list5.addByHead(3);
        list5.addByHead(4);
        list5.addByHead(5);
        System.out.println("反转之前:");
        list5.displayAllNodes();
        list5.reverseList();
        System.out.println("\n反转后:");
        list5.displayAllNodes();

    }


}
