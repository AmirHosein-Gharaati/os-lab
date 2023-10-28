package com.exmaple.os;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        final int capacity = 8;

        Lock lock = new ReentrantLock();
        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();

        Thread producer = new Producer(list, capacity, "Producer", lock, notFull, notEmpty);
        producer.start();

        for(int i = 0 ; i < 8; i++) {
            Thread consumer = new Consumer(list, "Consumer" + i, lock, notFull, notEmpty);
            consumer.start();
        }
    }
}
