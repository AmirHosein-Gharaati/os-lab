package com.exmaple.os;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Integer> list = new LinkedList<>();
        final int capacity = 8;

        Thread thread1 = new Producer(list, capacity, "Producer");
        Thread thread2 = new Consumer(list, "Consumer1");

        thread1.start();
        thread2.start();
    }
}
