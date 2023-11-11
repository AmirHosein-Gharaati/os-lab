
# Operating System Lab

## Section 3

## What Is a Semaphore?

A semaphore is an integer variable, shared among multiple processes.
The main aim of using a semaphore is process synchronization and access
control for a common resource in a concurrent environment.

There are two types of semaphores:

+ Binary semaphore (something like mutex)
+ Counting Semaphore

Read more about semaphores: https://www.baeldung.com/cs/semaphore

## Downloader Application

We are going to implement a simple downloader.

In this applcation, we can download multiple resources at the same time. But with a limitation.

The limitation is that we can download at most N files simultaneously. For example, if N is equal to 3, we can download 3 files at the same time. Then, when one of the threads has completed downloading a file, another thread will start its process.

## Phase 1

You don't have to implement "download" part. Use `Thread.sleep(500)` instead to simulate the download opeartion.

### Inputs

- `MAXIMUM_PERMITS`: The maximum number of downloaders that can download at the same time.

### Implementation
The DownloadManager should take a semaphore, URL, and the output file name.

Use logging to record each operation performed by your threads. Add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.11</version>
    </dependency>
</dependencies>
```

Use logger in your class:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassName {
    private static final Logger log = LoggerFactory.getLogger(ClassName.class);

    public void foo() {
        log.info("Hello World");
    }
}
```

Parametrize your log:
```java

public class ClassName {
    ...

    public void foo() {
        String threadName = "Thread-1";
        String url = "https://picsum.photos/200/400";
        log.info("{} started with url: {}", threadName, url);
    }
    
    ...
}
```

## Phase 2
Implement the actual code for the "download" part to download the resources.