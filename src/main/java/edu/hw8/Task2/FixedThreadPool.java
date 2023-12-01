package edu.hw8.Task2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedThreadPool implements ThreadPool {

    private static int coreCount = 0;
    private static final Queue<Runnable> TASKS = new ArrayDeque<>();
    private static final AtomicBoolean CLOSED = new AtomicBoolean(true);
    private static final int COMFORT_TIME_FOR_SLEEP = 200;

    public void create(int coreCount) {
        FixedThreadPool.coreCount = coreCount;
        CLOSED.set(false);
    }

    public boolean tasksAreEmpty() {
        //lock.readLock().lock();
        boolean empty;

        try {
            empty = TASKS.isEmpty();
        } finally {
            //lock.readLock().unlock();
        }

        return empty;
    }

    public boolean isClosed() {
        return CLOSED.get();
    }

    public Runnable getTask() {
        //lock.readLock().lock();
        Runnable task;

        try {
            task = TASKS.poll();
        } finally {
            //lock.readLock().unlock();
        }

        return task;
    }

    public Queue<Runnable> getTasks() {
        return TASKS;
    }

    @Override
    public void start() {
        if (coreCount <= 0) {
            throw new RuntimeException();
        } else {
            for (int i = 0; i < coreCount; i++) {
                CustomThread thread = new CustomThread(this);
                thread.start();
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        //lock.writeLock().lock();

        try {
            if (!CLOSED.get()) {
                TASKS.add(runnable);
            }
        } finally {
            //lock.writeLock().unlock();
        }
    }

    @Override
    public void close() throws Exception {
        CLOSED.set(true);
        TASKS.clear();
    }

    public static class CustomThread extends Thread {
        private final FixedThreadPool threadPool;

        public CustomThread(FixedThreadPool threadPool) {
            this.threadPool = threadPool;
        }

        @Override
        public void run() {
            while (!threadPool.tasksAreEmpty() || !threadPool.isClosed()) {
                Runnable task = threadPool.getTask();

                if (task != null) {
                    task.run();
                }

                try {
                    Thread.sleep(COMFORT_TIME_FOR_SLEEP);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
