package edu.project2;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRender {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Scanner IN = new Scanner(System.in);
    private static final String INCORRECT_INPUT = "Incorrect input!";
    private static final String WALL_STRING = "▉▉▉";
    private static final String SPACE_STRING = "   ";

    private MazeRender() {}

    public static void render(List<List<Cell>> maze) {
        LOGGER.info(WALL_STRING.repeat(Math.max(0, 2 * maze.get(0).size() + 1)));

        for (List<Cell> cells : maze) {
            StringBuilder line = new StringBuilder(WALL_STRING);
            StringBuilder lowerLine = new StringBuilder(WALL_STRING);

            for (int internalIterator = 0; internalIterator < maze.get(0).size(); internalIterator++) {
                line.append(SPACE_STRING);

                if (cells.get(internalIterator).hasWall(Direction.RIGHT)) {
                    line.append(WALL_STRING);
                } else {
                    line.append(SPACE_STRING);
                }

                if (cells.get(internalIterator).hasWall(Direction.BOTTOM)) {
                    lowerLine.append(WALL_STRING);
                } else {
                    lowerLine.append(SPACE_STRING);
                }

                lowerLine.append(WALL_STRING);
            }

            LOGGER.info(String.valueOf(line));
            LOGGER.info(String.valueOf(lowerLine));
        }
    }

    public static int[][] transform(
        List<List<Pair<Integer, Integer>>> previousArray,
        Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2
    ) {

        int[][] resultPath = new int[previousArray.size()][previousArray.get(0).size()];
        Pair<Integer, Integer> previousPair = point2;

        while (!(Objects.equals(previousPair.getLeft(), point1.getLeft())
            && Objects.equals(previousPair.getRight(), point1.getRight()))) {

            resultPath[previousPair.getRight()][previousPair.getLeft()] = 1;
            previousPair = previousArray.get(previousPair.getRight()).get(previousPair.getLeft());
        }

        resultPath[point1.getRight()][point1.getLeft()] = 1;

        return resultPath;
    }

    public static void renderWithPath(
        List<List<Cell>> maze,
        List<List<Pair<Integer, Integer>>> previousArray,
        Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2
    ) {

        int[][] pathArray = transform(previousArray, point1, point2);

        LOGGER.info(WALL_STRING.repeat(Math.max(0, 2 * maze.get(0).size() + 1)));

        for (int externalIterator = 0; externalIterator < maze.size(); externalIterator++) {
            StringBuilder line = new StringBuilder(WALL_STRING);
            StringBuilder lowerLine = new StringBuilder(WALL_STRING);

            for (int internalIterator = 0; internalIterator < maze.get(0).size(); internalIterator++) {
                if (pathArray[externalIterator][internalIterator] == 1) {
                    line.append(" ● ");
                } else {
                    line.append(SPACE_STRING);
                }

                if (maze.get(externalIterator).get(internalIterator).hasWall(Direction.RIGHT)) {
                    line.append(WALL_STRING);
                } else {
                    line.append(SPACE_STRING);
                }

                if (maze.get(externalIterator).get(internalIterator).hasWall(Direction.BOTTOM)) {
                    lowerLine.append(WALL_STRING);
                } else {
                    lowerLine.append(SPACE_STRING);
                }

                lowerLine.append(WALL_STRING);
            }

            LOGGER.info(String.valueOf(line));
            LOGGER.info(String.valueOf(lowerLine));
        }
    }

    public static Pair<Integer, Integer> inputMazeSize() {
        int width;
        int height;
        String inputWidth;
        String inputHeight;

        while (true) {
            LOGGER.info("Input maze width: ");
            inputWidth = IN.nextLine();

            try {
                width = Integer.parseInt(inputWidth);
                if (width <= 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                LOGGER.info(INCORRECT_INPUT);
            }
        }

        while (true) {
            LOGGER.info("Input maze height: ");
            inputHeight = IN.nextLine();

            try {
                height = Integer.parseInt(inputHeight);
                if (height <= 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                LOGGER.info(INCORRECT_INPUT);
            }
        }

        return new ImmutablePair<>(width, height);
    }

    public static Pair<Integer, Integer> inputPoints(List<List<Cell>> maze, String name) {
        int aX;
        int aY;
        String strAX;
        String strAY;

        while (true) {
            LOGGER.info("Input coordinate X for " + name + " point:");
            strAX = IN.nextLine();

            try {
                aX = Integer.parseInt(strAX);
                if (aX < 0 || aX >= maze.get(0).size()) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                LOGGER.info(INCORRECT_INPUT);
            }
        }
        while (true) {
            LOGGER.info("Input coordinate Y for " + name + " point: ");
            strAY = IN.nextLine();

            try {
                aY = Integer.parseInt(strAY);
                if (aY < 0 || aY >= maze.size()) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                LOGGER.info(INCORRECT_INPUT);
            }
        }

        return new ImmutablePair<>(aX, aY);
    }
}
