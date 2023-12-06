package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {

    private volatile double sumValue;
    private volatile double count;
    private volatile double minValue;
    private volatile double maxValue;
    private final ReadWriteLock lock;
    private final ExecutorService pool;
    private static final int THREAD_COUNT = 6;

    public StatsCollector() {
        sumValue = 0;
        count = 0;
        minValue = Double.MAX_VALUE;
        maxValue = Double.MIN_VALUE;
        lock = new ReentrantReadWriteLock();
        pool = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    public void push(String metricName, double[] stats) {
        //LOGGER.info("Получены данные: " + metricName);
        for (double data: stats) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (StatsCollector.class) {
                        lock.writeLock().lock();
                        sumValue += data;
                        count++;
                        minValue = Math.min(minValue, data);
                        maxValue = Math.max(maxValue, data);
                        lock.writeLock().unlock();
                    }
                }
            });
        }
    }

    public List<Map.Entry<String, Double>> stats() {
        lock.readLock().lock();
        List<Map.Entry<String, Double>> resultList = new ArrayList<>();
        try {
            Thread.sleep(1);
            resultList.add(Map.entry("Сумма: ", sumValue));
            resultList.add(Map.entry("Среднее арифметическое: ", sumValue / count));
            resultList.add(Map.entry("Минимум: ", minValue));
            resultList.add(Map.entry("Максимум: ", maxValue));
        } catch (InterruptedException error) {
            throw new RuntimeException();
        } finally {
            lock.readLock().unlock();
        }

        return resultList;
    }
}
