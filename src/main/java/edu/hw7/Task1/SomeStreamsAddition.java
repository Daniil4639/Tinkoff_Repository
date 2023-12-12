package edu.hw7.Task1;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SomeStreamsAddition {

    private final int rangeCheckNumber = 100_000;

    private final AtomicInteger generalCount;

    public SomeStreamsAddition() {
        generalCount = new AtomicInteger(0);
    }

    public AtomicInteger getGeneralCount() {
        return generalCount;
    }

    public void incrementCount(int threadCount) {
        List<Thread> threadList = Stream.generate(() -> new Thread(() -> {
            for (int i = 0; i < rangeCheckNumber; i++) {
                generalCount.incrementAndGet();
            }
        })).limit(threadCount).toList();

        for (var thread: threadList) {
            thread.start();
        }

        try {
            for (var thread: threadList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            generalCount.updateAndGet(val -> -1);
        }
    }
}
