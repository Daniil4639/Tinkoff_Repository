package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class MazeDFSSearch {

    private MazeDFSSearch() {}

    public static List<List<Pair<Integer, Integer>>> findWay(
        List<List<Cell>> maze,
        Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2) {

        List<List<Pair<Integer, Integer>>> previousCell = new ArrayList<>();

        List<List<Boolean>> wasChecked = new ArrayList<>();

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

        boolean[] ok = new boolean[1];
        ok[0] = true;

        reqSearch(maze, previousCell, wasChecked, point1, point2, ok);

        return previousCell;
    }

    private static void reqSearch(
        List<List<Cell>> maze,
        List<List<Pair<Integer, Integer>>> previousCell,
        List<List<Boolean>> wasChecked,
        Pair<Integer, Integer> currentPos,
        Pair<Integer, Integer> point2,
        boolean[] ok) {

        if (!ok[0]) {
            return;
        }

        wasChecked.get(currentPos.getRight()).set(currentPos.getLeft(), true);

        if (!(Objects.equals(currentPos.getLeft(), point2.getLeft())
            && Objects.equals(currentPos.getRight(), point2.getRight()))) {

            if (!maze.get(currentPos.getRight()).get(currentPos.getLeft()).hasWall(Direction.TOP)
                && !wasChecked.get(currentPos.getRight() - 1).get(currentPos.getLeft())) {
                previousCell.get(currentPos.getRight() - 1).set(currentPos.getLeft(), new ImmutablePair<>(
                    currentPos.getLeft(), currentPos.getRight()));

                reqSearch(maze, previousCell, wasChecked, new ImmutablePair<>(currentPos.getLeft(),
                    currentPos.getRight() - 1), point2, ok);
            }
            if (!maze.get(currentPos.getRight()).get(currentPos.getLeft()).hasWall(Direction.BOTTOM)
                && !wasChecked.get(currentPos.getRight() + 1).get(currentPos.getLeft())) {
                previousCell.get(currentPos.getRight() + 1).set(currentPos.getLeft(), new ImmutablePair<>(
                    currentPos.getLeft(), currentPos.getRight()));

                reqSearch(maze, previousCell, wasChecked, new ImmutablePair<>(currentPos.getLeft(),
                    currentPos.getRight() + 1), point2, ok);
            }
            if (!maze.get(currentPos.getRight()).get(currentPos.getLeft()).hasWall(Direction.LEFT)
                && !wasChecked.get(currentPos.getRight()).get(currentPos.getLeft() - 1)) {
                previousCell.get(currentPos.getRight()).set(currentPos.getLeft() - 1, new ImmutablePair<>(
                    currentPos.getLeft(), currentPos.getRight()));

                reqSearch(maze, previousCell, wasChecked, new ImmutablePair<>(currentPos.getLeft() - 1,
                    currentPos.getRight()), point2, ok);
            }
            if (!maze.get(currentPos.getRight()).get(currentPos.getLeft()).hasWall(Direction.RIGHT)
                && !wasChecked.get(currentPos.getRight()).get(currentPos.getLeft() + 1)) {
                previousCell.get(currentPos.getRight()).set(currentPos.getLeft() + 1, new ImmutablePair<>(
                    currentPos.getLeft(), currentPos.getRight()));

                reqSearch(maze, previousCell, wasChecked, new ImmutablePair<>(currentPos.getLeft() + 1,
                    currentPos.getRight()), point2, ok);
            }
        } else {
            ok[0] = false;
        }

    }
}
