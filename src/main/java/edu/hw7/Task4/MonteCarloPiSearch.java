package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class MonteCarloPiSearch {

    private static volatile AtomicLong operationCount;
    private static volatile AtomicLong pointsInCircle;
    private static final int PROCESSORS_COUNT = 16;

    private MonteCarloPiSearch() {}

    public static double countPi(long accuracy) {
        long pointInTheCircleCount = 0;

        for (int i = 0; i < accuracy; i++) {
            double x = Math.random();
            double y = Math.random();

            if (x * x + y * y <= 1) {
                pointInTheCircleCount++;
            }
        }

        return piCalculation(pointInTheCircleCount, accuracy);
    }

    public static double countPiThread(long accuracy) {
        operationCount = new AtomicLong(0);
        pointsInCircle = new AtomicLong(0);

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < PROCESSORS_COUNT; i++) {
            threadList.add(new Thread(() -> {
                long localCircleCount = 0;
                long localGeneralCount = 0;

                while (localGeneralCount <= accuracy / PROCESSORS_COUNT) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();

                    if (x * x + y * y <= 1) {
                        localCircleCount++;
                    }
                    localGeneralCount++;
                }

                operationCount.addAndGet(localGeneralCount);
                pointsInCircle.addAndGet(localCircleCount);
            }));
        }

        for (Thread thread: threadList) {
            thread.start();
        }

        try {
            for (Thread thread: threadList) {
                thread.join();
            }

            return piCalculation(pointsInCircle.get(), operationCount.get());
        } catch (InterruptedException e) {
            return -1;
        }
    }

    @SuppressWarnings("MagicNumber")
    private static double piCalculation(long circle, long total) {
        return 4.0 * circle / total;
    }
}
