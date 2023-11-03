package com.exmaple.os;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Semaphore;

public class DownloadManager extends Thread {
    private static final Logger log = LoggerFactory.getLogger(DownloadManager.class);
    private static final int MAXIMUM_BYTES = 1024;
    private final Semaphore semaphore;
    private final String url;
    private final String destinationPath;

    public DownloadManager(Semaphore semaphore, String url, String destinationPath) {
        this.semaphore = semaphore;
        this.url = url;
        this.destinationPath = destinationPath;
    }

    public void download() {

        log.info("{} started with url: {}", getName(), url);

        try {
            semaphore.acquire();
            log.info("{} acquired lock", getName());

            URL fileUrl = new URL(url);
            try (InputStream inputStream = new BufferedInputStream(fileUrl.openStream())) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[MAXIMUM_BYTES];
                int bytesRead;

                while((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();

                saveImage(outputStream);
            } catch (IOException e) {
                log.error("Error occurred while downloading file: {}", e.getMessage());
                throw new RuntimeException(e);
            }

        } catch (InterruptedException | MalformedURLException e) {
            log.error("Error occurred while executing the thread: {}", e.getMessage());
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            log.info("{} released the semaphore", getName());
            semaphore.release();
        }
    }

    private void saveImage(ByteArrayOutputStream out) {
        try (FileOutputStream fos = new FileOutputStream(destinationPath)) {
            byte[] response = out.toByteArray();
            fos.write(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        download();
    }
}
