package edu.project2;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MazeBFSSearch {

    private MazeBFSSearch() {}

    public static Pair[][] findWay(Cell[][] maze, Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2) {

        boolean[][] wasChecked = new boolean[maze.length][maze[0].length];
        Pair[][] previousCell = new Pair[maze.length][maze[0].length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                previousCell[i][j] = null;
            }
        }

        previousCell[point1.getRight()][point1.getLeft()] = new ImmutablePair(point1.getLeft(), point1.getRight());

        Queue<Cell> order = new ArrayDeque<>();
        order.add(maze[point1.getRight()][point1.getLeft()]);

        while ((Objects.requireNonNull(order.peek()).getY() != point2.getRight()
            || Objects.requireNonNull(order.peek()).getX() != point2.getLeft()) && !order.isEmpty()) {

            Pair<Integer, Integer> previous = new ImmutablePair<>(Objects.requireNonNull(order.peek()).getX(),
                Objects.requireNonNull(order.peek()).getY());

            wasChecked[previous.getRight()][previous.getLeft()] = true;
            order.poll();

            if (!maze[previous.getRight()][previous.getLeft()].hasWall(Cell.DIRECTION.TOP)
                && !wasChecked[previous.getRight() - 1][previous.getLeft()]) {

                previousCell[previous.getRight() - 1][previous.getLeft()] = previous;
                order.add(maze[previous.getRight() - 1][previous.getLeft()]);
            }
            if (!maze[previous.getRight()][previous.getLeft()].hasWall(Cell.DIRECTION.BOTTOM)
                && !wasChecked[previous.getRight() + 1][previous.getLeft()]) {

                previousCell[previous.getRight() + 1][previous.getLeft()] = previous;
                order.add(maze[previous.getRight() + 1][previous.getLeft()]);
            }
            if (!maze[previous.getRight()][previous.getLeft()].hasWall(Cell.DIRECTION.LEFT)
                && !wasChecked[previous.getRight()][previous.getLeft() - 1]) {

                previousCell[previous.getRight()][previous.getLeft() - 1] = previous;
                order.add(maze[previous.getRight()][previous.getLeft() - 1]);
            }
            if (!maze[previous.getRight()][previous.getLeft()].hasWall(Cell.DIRECTION.RIGHT)
                && !wasChecked[previous.getRight()][previous.getLeft() + 1]) {

                previousCell[previous.getRight()][previous.getLeft() + 1] = previous;
                order.add(maze[previous.getRight()][previous.getLeft() + 1]);
            }
        }

        return previousCell;
    }
}
