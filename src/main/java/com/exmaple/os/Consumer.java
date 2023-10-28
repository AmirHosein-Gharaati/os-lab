package com.exmaple.os;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer extends Thread {
    private final List<Integer> list;

    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public Consumer(List<Integer> list, String name, Lock lock, Condition notFull, Condition notEmpty) {
        this.list = list;
        setName(name);
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            lock.lock();
            try {
                while (list.isEmpty()) {
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                int val = list.remove(0);

                System.out.println(getName() + " consumed: " + val);

                notFull.signal();

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
