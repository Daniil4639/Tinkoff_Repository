package edu.project2;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeGeneratorTest {

    @Test
    @DisplayName("Проверка работы generate()")
    void generateWallTest() {
        MazeGenerator mazeGenerator = new MazeGenerator(10, 10);
        mazeGenerator.generate();

        List<List<Cell>> maze = mazeGenerator.getMaze();
        boolean ok = true;
        Direction[] directions = new Direction[] {
            Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT
        };

        try {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (!maze.get(i).get(j).hasWall(directions[k])) {
                            int indY = i;
                            int indX = j;

                            switch (directions[k]) {
                                case TOP:
                                    indY--;
                                    if (indY < 0) {
                                        throw new Exception();
                                    }
                                    break;
                                case BOTTOM:
                                    indY++;
                                    if (indY > 9) {
                                        throw new Exception();
                                    }
                                    break;
                                case LEFT:
                                    indX--;
                                    if (indX < 0) {
                                        throw new Exception();
                                    }
                                    break;
                                case RIGHT:
                                    indX++;
                                    if (indX > 9) {
                                        throw new Exception();
                                    }
                                    break;
                            }

                            if (maze.get(indY).get(indX).hasWall(MazeGenerator.OPPOSITE_DIRECTION.get(directions[k]))) {
                                throw new Exception();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ok = false;
        }

        assertThat(ok).isTrue();
    }

    @Test
    @DisplayName("Проверка работы generate()")
    void generateCycleTest() {
        MazeGenerator mazeGenerator = new MazeGenerator(10, 10);
        mazeGenerator.generate();

        List<List<Cell>> maze = mazeGenerator.getMaze();
        boolean[][] checkedCells = new boolean[10][10];

        Queue<Pair<Pair<Integer, Integer>, Direction>> pairQueue = new ArrayDeque<>();
        pairQueue.add(new ImmutablePair<>(new ImmutablePair<>(0, 0), Direction.TOP));
        boolean ok = true;

        try {
            while (!pairQueue.isEmpty()) {
                assert pairQueue.peek() != null;
                Pair<Integer, Integer> currentPos = pairQueue.peek().getLeft();
                assert pairQueue.peek() != null;
                Direction direction = pairQueue.peek().getRight();
                pairQueue.poll();
                checkedCells[currentPos.getRight()][currentPos.getLeft()] = true;

                if (!maze.get(currentPos.getRight()).get(currentPos.getLeft()).hasWall(Direction.TOP)
                    && !direction.equals(Direction.TOP)) {

                    if (checkedCells[currentPos.getRight() - 1][currentPos.getLeft()]) {
                        throw new Exception();
                    }
                    checkedCells[currentPos.getRight() - 1][currentPos.getLeft()] = true;
                    pairQueue.add(new ImmutablePair<>(new ImmutablePair<>(currentPos.getLeft(),
                        currentPos.getRight() - 1), MazeGenerator.OPPOSITE_DIRECTION.get(Direction.TOP)));
                }

                if (!maze.get(currentPos.getRight()).get(currentPos.getLeft()).hasWall(Direction.BOTTOM)
                    && !direction.equals(Direction.BOTTOM)) {

                    if (checkedCells[currentPos.getRight() + 1][currentPos.getLeft()]) {
                        throw new Exception();
                    }
                    checkedCells[currentPos.getRight() + 1][currentPos.getLeft()] = true;
                    pairQueue.add(new ImmutablePair<>(new ImmutablePair<>(currentPos.getLeft(),
                        currentPos.getRight() + 1), MazeGenerator.OPPOSITE_DIRECTION.get(Direction.BOTTOM)));
                }

                if (!maze.get(currentPos.getRight()).get(currentPos.getLeft()).hasWall(Direction.LEFT)
                    && !direction.equals(Direction.LEFT)) {

                    if (checkedCells[currentPos.getRight()][currentPos.getLeft() - 1]) {
                        throw new Exception();
                    }
                    checkedCells[currentPos.getRight()][currentPos.getLeft() - 1] = true;
                    pairQueue.add(new ImmutablePair<>(new ImmutablePair<>(currentPos.getLeft() - 1,
                        currentPos.getRight()), MazeGenerator.OPPOSITE_DIRECTION.get(Direction.LEFT)));
                }

                if (!maze.get(currentPos.getRight()).get(currentPos.getLeft()).hasWall(Direction.RIGHT)
                    && !direction.equals(Direction.RIGHT)) {

                    if (checkedCells[currentPos.getRight()][currentPos.getLeft() + 1]) {
                        throw new Exception();
                    }
                    checkedCells[currentPos.getRight()][currentPos.getLeft() + 1] = true;
                    pairQueue.add(new ImmutablePair<>(new ImmutablePair<>(currentPos.getLeft() + 1,
                        currentPos.getRight()), MazeGenerator.OPPOSITE_DIRECTION.get(Direction.RIGHT)));
                }
            }
        } catch (Exception ex) {
            ok = false;
        }

        assertThat(ok).isTrue();
    }
}
