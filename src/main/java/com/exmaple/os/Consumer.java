package com.exmaple.os;

import java.util.List;

public class Consumer extends Thread {
    private final List<Integer> list;

    public Consumer(List<Integer> list, String name) {
        this.list = list;
        setName(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                while (list.isEmpty()) {
                    System.out.println("list is empty " + Thread.currentThread().getName() + " is waiting");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                int val = list.remove(0);

                System.out.println("Consumer consumed: " + val);

                list.notify();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
