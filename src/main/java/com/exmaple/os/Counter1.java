package com.exmaple.os;

public class Counter1 implements Runnable {

    private final String name;

    public Counter1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 20; i++) {
            System.out.println(name + ": " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
