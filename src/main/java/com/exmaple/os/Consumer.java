package com.exmaple.os;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer extends Thread {
    private final List<Integer> list;

    public Consumer(List<Integer> list, String name) {
        this.list = list;
        setName(name);
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(50 ,50+1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

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
                System.out.println(Thread.currentThread().getName() + " consumed: " + val);

                if(list.isEmpty()) {
                    list.notifyAll();

                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
