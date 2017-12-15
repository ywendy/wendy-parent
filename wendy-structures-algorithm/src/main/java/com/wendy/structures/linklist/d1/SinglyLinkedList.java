package com.wendy.structures.linklist.d1;

/**
 * Created by Administrator on 2017/12/15.
 */
public class SinglyLinkedList <T> {

    private Node<T> head;
    private Node<T> tail;

    public void add(T element){
        Node<T> nd = new Node<>();
        nd.setValue(element);
        System.out.println("Adding:"+element);

        if (head == null){
            head = nd;
            tail = nd;
        }else {
            tail.setNextRef(nd);
            tail = nd;
        }

    }


    public  void addAfter(T element, T after){
        Node<T> tmp = head;
        Node<T> refNode = null;
        System.out.println("Traversing to all nodes..");

        while (true){
            if (tmp == null){
                break;
            }
            if (tmp.compareTo(after) == 0){
                refNode = tmp;
                break;
            }
            tmp = tmp.getNextRef();
        }
        if (refNode !=null){
            Node<T> nd = new Node<>();
            nd.setValue(element);
            nd.setNextRef(tmp.getNextRef());
            if (tmp == tail){
                tail = nd;
            }
            tmp.setNextRef(nd);

            return;
        }
        System.out.println("Unable to find the given element ...");
    }

    public void deleteFront(){
        if (head == null){
            System.out.println("Underflow ...");
        }
        Node<T> tmp = head;
        head = tmp.getNextRef();
        if (head == null){
            tail = null;
        }
        System.out.println("Deleted:"+tmp.getValue());
    }



    public void deleteAfter( T after){
        Node<T> tmp = head;
        Node<T> refNode = null;
        System.out.println("Traversing to all nodes ...");
        while (true){

            if (tmp == null){
                break;
            }
            if (tmp.compareTo(after) == 0){
                refNode = tmp;
                break;
            }
            tmp = tmp.getNextRef();
        }
        if (refNode !=null){
            tmp = refNode.getNextRef();
            refNode.setNextRef(tmp.getNextRef());
            if (refNode.getNextRef() == null){
                tail = refNode;
            }
            System.out.println("Deleted:"+tmp.getValue());
        }else{
            System.out.println("unable to find the given element...");
        }


    }

    public void traverse(){
        Node<T> tmp = head;
        while (true){
            if (tmp == null){
                break;
            }
            System.out.println(tmp.getValue());
            tmp = tmp.getNextRef();
        }
    }


    public static void main(String[] args) {
        SinglyLinkedList<Integer> sList = new SinglyLinkedList<>();
        sList.add(3);
        sList.add(32);
        sList.add(54);
        sList.add(89);

        sList.traverse();
    }





}
