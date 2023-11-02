# Operating System Lab

## Section 1


## Overview
In Java, synchronization is a mechanism used to control access to shared
resources, particularly in multi-threaded programs. When multiple threads
are concurrently accessing and modifying shared data, synchronization
ensures that the threads operate in a coordinated and orderly manner to
prevent issues such as data corruption and race conditions.


### Synchronized examples

Example of synchronized method:

```java
public synchronized void synchronizedMethod() {
    // Only one thread can execute this method at a time.
}
```

Example of synchronized block:
```java
public void someMethod() {
    synchronized (lockObject) {
        // Only one thread can execute this block at a time.
    }
}
```

### wait(), notify() and notifyAll()
Java concurrency is pretty complex topic and requires a lot of attention
while writing application code dealing with multiple threads accessing
one/more shared resources at any given time.

Programmers using concurrency classes will feel a lot more confident than
programmers directly handling synchronization stuff using wait(), notify()
and notifyAll() method calls.

This is a good article about these three methods:

[How to work with wait(), notify() and notifyAll() in Java?](https://howtodoinjava.com/java/multi-threading/wait-notify-and-notifyall-methods/)


### Producer-Consumer problem
The Producer-Consumer Problem (sometimes called the Bounded-Buffer Problem)
is a classic example of a multi-threaded synchronization problem. The problem
describes two or many threads, the Producer and the Consumer, and they are
sharing a common, fixed-size buffer that is used as a queue.

**Note 1**: You can see the example implementations in the previous commits in this branch.

**Note 2**: You have to put `Thread.sleep(...)` before synchronized block and after
notify to see the prints better.

## Producer-Consumer: Phase 1

We are going to implement a simple producer-consumer problem in java.

In this example we will have a **shared** list of items: `List<Integer>`.

The whole idea is the `Producer` is going to add integers in the list, and the consumer is going to take and remove integers from that list.

Parameters:

- `List<Integer>`: a list that will contain some integers.
- `capacity`: which is the maximum capacity we want to have in our list.

Both producer and consumer should have an infinite loop to do their jobs.

Producer:
- Producer should wait for consumer when the list is reached its maximum capacity.
- Otherwise, it adds an integer to the list and notifies consumer.

Consumer:
- Consumer should wait when the list is empty and let the producer, producer integer.
- Otherwise, it removes the first item from the list and notifies producer.


## Producer-Consumer: Phase 2

Now use multiple consumers to consume the list.

At this phase you should notify all threads not only one!

__Optional__: You can change the behaviour of producer to produce the maximum capacity it can, then it notifies all threads.


## Producer-Consumer: Phase 3

There is a problem until here. When we say `notifyAll()` all of threads regardless of the condition will be notified. But we don't want this.

Refactor the second phase of the problem with `ReentrantLock`.

Use 2 conditions:
- notFull
- notEmpty

You should `lock()` and `unlock()` when you want to enter your critical section.

Also you can use `await()` and `signalAll()` on the conditions.