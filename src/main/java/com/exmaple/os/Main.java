package com.exmaple.os;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        final int MAXIMUM_PERMITS = 3;
        Semaphore semaphore = new Semaphore(MAXIMUM_PERMITS);

        List<String> urls = List.of(
                "https://picsum.photos/200/400",
                "https://picsum.photos/100/300",
                "https://picsum.photos/500/300",
                "https://picsum.photos/800/300",
                "https://picsum.photos/234/300",
                "https://picsum.photos/200/10"
        );

        for (int i = 0; i < urls.size(); i++) {
            String fileName = "%s.jpg".formatted(i);
            Thread thread = new DownloadManager(semaphore, urls.get(i), fileName);
            thread.start();
        }
    }
}
