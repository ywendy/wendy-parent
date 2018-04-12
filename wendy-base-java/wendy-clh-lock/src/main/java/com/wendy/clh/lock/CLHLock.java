package com.wendy.clh.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 1、init
 *
 * +------+
 * | head | <---- tail
 * +------+
 *
 * 2、一个thread加入lock队列
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * @author Administrator
 * @date 2018/4/12
 */
public class CLHLock implements Lock {

    private ThreadLocal<QNode> myNode;
    private ThreadLocal<QNode> myPred;
    private AtomicReference<QNode> tail;

    public CLHLock() {
        this.myNode = ThreadLocal.withInitial(() -> new QNode());
        this.myPred = ThreadLocal.withInitial(() -> null);
        this.tail = new AtomicReference<>(new QNode());
    }

    @Override
    public void lock() {

        QNode qNode = myNode.get();
        qNode.locked = true;
        QNode pred = tail.getAndSet(qNode);
        myPred.set(pred);
        while (pred.locked) ;


    }

    @Override
    public void unlock() {
        QNode qNode = myNode.get();
        qNode.locked = false;
        myNode.set(myPred.get());

    }
}
