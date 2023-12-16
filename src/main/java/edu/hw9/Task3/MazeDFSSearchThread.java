package edu.hw9.Task3;

import edu.project2.Cell;
import edu.project2.Direction;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MazeDFSSearchThread {

    private static final int THREAD_COUNT = 6;

    private static List<List<Pair<Integer, Integer>>> previousCell;
    private static List<List<Boolean>> wasChecked;
    private static List<List<Cell>> mazeThread;
    private static Pair<Integer, Integer> point1Thread;
    private static Pair<Integer, Integer> point2Thread;
    private static Stack<Cell> orderStack;
    private static AtomicBoolean ok;

    private MazeDFSSearchThread() {
    }

    public static List<List<Pair<Integer, Integer>>> findWay(
        List<List<Cell>> maze,
        Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2
    ) {
        mazeThread = maze;
        point1Thread = point1;
        point2Thread = point2;

        previousCell = new ArrayList<>();

        wasChecked = new ArrayList<>();

        for (int i = 0; i < maze.size(); i++) {
            List<Boolean> raw = new ArrayList<>();

            for (int j = 0; j < maze.get(0).size(); j++) {
                raw.add(false);
            }

            wasChecked.add(raw);
        }

        for (int i = 0; i < maze.size(); i++) {
            List<Pair<Integer, Integer>> raw = new ArrayList<>();

            for (int j = 0; j < maze.get(0).size(); j++) {
                raw.add(null);
            }

            previousCell.add(raw);
        }
        previousCell.get(point1.getRight())
            .set(point1.getLeft(), new ImmutablePair<>(point1.getLeft(), point1.getRight()));

        search();

        return previousCell;
    }

    private static void search() {
        orderStack = new Stack<>();
        orderStack.add(mazeThread.get(point1Thread.getRight()).get(point1Thread.getLeft()));
        ok = new AtomicBoolean(true);
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

        var futures = Stream.generate(() -> CompletableFuture.runAsync(new DFSThread(), pool)).limit(THREAD_COUNT)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        pool.close();
    }

    private static class DFSThread implements Runnable {

        @Override
        public void run() {
            while (ok.get()) {
                Cell currentCell;
                synchronized (MazeDFSSearchThread.class) {
                    if (orderStack.empty()) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        continue;
                    }
                    currentCell = orderStack.pop();
                }

                if (currentCell == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    continue;
                }

                if (currentCell.getX() == point2Thread.getLeft() && currentCell.getY() == point2Thread.getRight()) {
                    ok.set(false);
                }
                wasChecked.get(currentCell.getY()).set(currentCell.getX(), true);

                if (!currentCell.hasWall(Direction.TOP)
                    && !wasChecked.get(currentCell.getY() - 1).get(currentCell.getX())) {
                    previousCell.get(currentCell.getY() - 1).set(currentCell.getX(), new ImmutablePair<>(
                        currentCell.getX(), currentCell.getY()));
                    orderStack.add(mazeThread.get(currentCell.getY() - 1).get(currentCell.getX()));
                }
                if (!currentCell.hasWall(Direction.BOTTOM)
                    && !wasChecked.get(currentCell.getY() + 1).get(currentCell.getX())) {
                    previousCell.get(currentCell.getY() + 1).set(currentCell.getX(), new ImmutablePair<>(
                        currentCell.getX(), currentCell.getY()));
                    orderStack.add(mazeThread.get(currentCell.getY() + 1).get(currentCell.getX()));
                }
                if (!currentCell.hasWall(Direction.LEFT)
                    && !wasChecked.get(currentCell.getY()).get(currentCell.getX() - 1)) {
                    previousCell.get(currentCell.getY()).set(currentCell.getX() - 1, new ImmutablePair<>(
                        currentCell.getX(), currentCell.getY()));
                    orderStack.add(mazeThread.get(currentCell.getY()).get(currentCell.getX() - 1));
                }
                if (!currentCell.hasWall(Direction.RIGHT)
                    && !wasChecked.get(currentCell.getY()).get(currentCell.getX() + 1)) {
                    previousCell.get(currentCell.getY()).set(currentCell.getX() + 1, new ImmutablePair<>(
                        currentCell.getX(), currentCell.getY()));
                    orderStack.add(mazeThread.get(currentCell.getY()).get(currentCell.getX() + 1));
                }
            }
        }
    }
}
