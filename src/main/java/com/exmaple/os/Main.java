package com.exmaple.os;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Counter1("Counter1"));
        Thread thread2 = new Counter2("Counter2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread finished");
    }
}
