package com.exmaple.os;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        final int capacity = 4;

        Thread producer = new Producer(list, capacity, "Producer");
        producer.start();

        for(int i = 0 ; i < 4; i++) {
            Thread consumer = new Consumer(list, "Consumer" + i);
            consumer.start();
        }
    }
}
