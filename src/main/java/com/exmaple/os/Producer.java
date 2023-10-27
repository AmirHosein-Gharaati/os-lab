package com.exmaple.os;

import java.util.List;

public class Producer extends Thread {

    private final List<Integer> list;
    private final int capacity;

    public Producer(List<Integer> list, int capacity, String name) {
        this.list = list;
        this.capacity = capacity;
        setName(name);
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            synchronized (list) {
                while (list.size() == capacity) {
                    System.out.println("list is full " + Thread.currentThread().getName() + " is waiting");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                while(list.size() < capacity) {
                    System.out.println("Producer produced: " + value);
                    list.add(value++);
                }

                list.notifyAll();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
