package com.exmaple.os;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer extends Thread {
    private final List<Integer> list;
    private final int capacity;

    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public Producer(List<Integer> list, int capacity, String name, Lock lock, Condition notFull, Condition notEmpty) {
        this.list = list;
        this.capacity = capacity;
        setName(name);
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            lock.lock();
            try {
                while (list.size() == capacity) {
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(getName() + " produced: " + value);

                list.add(value++);

                notEmpty.signalAll();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
