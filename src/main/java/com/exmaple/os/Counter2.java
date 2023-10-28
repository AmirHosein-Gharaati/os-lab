package com.exmaple.os;

public class Counter2 extends Thread {

    public Counter2(String name) {
        setName(name);
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 20; i++) {
            System.out.println(getName() + ": " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
