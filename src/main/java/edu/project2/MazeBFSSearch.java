package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MazeBFSSearch {

    private MazeBFSSearch() {}

    public static List<List<Pair<Integer, Integer>>> findWay(
        List<List<Cell>> maze,
        Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2
    ) {

        List<List<Boolean>> wasChecked = new ArrayList<>();

        for (int i = 0; i < maze.size(); i++) {
            List<Boolean> raw = new ArrayList<>();

            for (int j = 0; j < maze.get(0).size(); j++) {
                raw.add(false);
            }

            wasChecked.add(raw);
        }

        List<List<Pair<Integer, Integer>>> previousCell = new ArrayList<>();

        for (int i = 0; i < maze.size(); i++) {
            List<Pair<Integer, Integer>> raw = new ArrayList<>();

            for (int j = 0; j < maze.get(0).size(); j++) {
                raw.add(null);
            }

            previousCell.add(raw);
        }

        previousCell.get(point1.getRight())
            .set(point1.getLeft(), new ImmutablePair<>(point1.getLeft(), point1.getRight()));

        Queue<Cell> order = new ArrayDeque<>();
        order.add(maze.get(point1.getRight()).get(point1.getLeft()));

        while ((Objects.requireNonNull(order.peek()).getY() != point2.getRight()
            || Objects.requireNonNull(order.peek()).getX() != point2.getLeft()) && !order.isEmpty()) {

            Pair<Integer, Integer> previous = new ImmutablePair<>(Objects.requireNonNull(order.peek()).getX(),
                Objects.requireNonNull(order.peek()).getY());

            wasChecked.get(previous.getRight()).set(previous.getLeft(), true);
            order.poll();

            if (!maze.get(previous.getRight()).get(previous.getLeft()).hasWall(Direction.TOP)
                && !wasChecked.get(previous.getRight() - 1).get(previous.getLeft())) {

                previousCell.get(previous.getRight() - 1).set(previous.getLeft(), previous);
                order.add(maze.get(previous.getRight() - 1).get(previous.getLeft()));
            }
            if (!maze.get(previous.getRight()).get(previous.getLeft()).hasWall(Direction.BOTTOM)
                && !wasChecked.get(previous.getRight() + 1).get(previous.getLeft())) {

                previousCell.get(previous.getRight() + 1).set(previous.getLeft(), previous);
                order.add(maze.get(previous.getRight() + 1).get(previous.getLeft()));
            }
            if (!maze.get(previous.getRight()).get(previous.getLeft()).hasWall(Direction.LEFT)
                && !wasChecked.get(previous.getRight()).get(previous.getLeft() - 1)) {

                previousCell.get(previous.getRight()).set(previous.getLeft() - 1, previous);
                order.add(maze.get(previous.getRight()).get(previous.getLeft() - 1));
            }
            if (!maze.get(previous.getRight()).get(previous.getLeft()).hasWall(Direction.RIGHT)
                && !wasChecked.get(previous.getRight()).get(previous.getLeft() + 1)) {

                previousCell.get(previous.getRight()).set(previous.getLeft() + 1, previous);
                order.add(maze.get(previous.getRight()).get(previous.getLeft() + 1));
            }
        }

        return previousCell;
    }
}
