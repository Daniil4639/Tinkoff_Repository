package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class SomeStreamsAddition {

    private final int rangeCheckNumber = 100_000;

    private final AtomicInteger generalCount;

    public SomeStreamsAddition() {
        generalCount = new AtomicInteger(0);
    }

    public AtomicInteger getGeneralCount() {
        return generalCount;
    }

    public void incrementCount() {
        Thread firstIncrement = new Thread(() -> {
            for (int i = 0; i < rangeCheckNumber; i++) {
                generalCount.incrementAndGet();
            }
        });
        Thread secondIncrement = new Thread(() -> {
            for (int i = 0; i < rangeCheckNumber; i++) {
                generalCount.incrementAndGet();
            }
        });
        Thread thirdIncrement = new Thread(() -> {
            for (int i = 0; i < rangeCheckNumber; i++) {
                generalCount.incrementAndGet();
            }
        });

        firstIncrement.start();
        secondIncrement.start();
        thirdIncrement.start();

        try {
            firstIncrement.join();
            secondIncrement.join();
            thirdIncrement.join();

        } catch (InterruptedException e) {
            generalCount.updateAndGet(val -> -1);
        }
    }
}
