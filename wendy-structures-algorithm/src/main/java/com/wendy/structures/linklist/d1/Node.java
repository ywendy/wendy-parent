package com.wendy.structures.linklist.d1;

/**
 * singly linked list data .
 * <p>
 * 2017/12/15.
 */
public class Node<T> implements Comparable<T> {

    private T value;
    private Node<T> nextRef;


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNextRef() {
        return nextRef;
    }

    public void setNextRef(Node<T> nextRef) {
        this.nextRef = nextRef;
    }


    @Override
    public int compareTo(T arg) {
        if (arg == this.value) {
            return 0;
        }
        return 1;
    }
}
