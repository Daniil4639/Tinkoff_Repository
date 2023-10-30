package edu.project2;

import java.util.Scanner;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRender {

    private static Logger logger = LogManager.getLogger();
    private static Scanner in = new Scanner(System.in);
    private static final String WALL_STRING = "▉▉▉";
    private static final String SPACE_STRING = "   ";

    private MazeRender() {}

    public static void render(Cell[][] maze) {
        logger.info(WALL_STRING.repeat(Math.max(0, 2 * maze[0].length + 1)));

        for (int externalIterator = 0; externalIterator < maze.length; externalIterator++) {
            StringBuilder line = new StringBuilder(WALL_STRING);
            StringBuilder lowerLine = new StringBuilder(WALL_STRING);

            for (int internalIterator = 0; internalIterator < maze[0].length; internalIterator++) {
                line.append(SPACE_STRING);

                if (maze[externalIterator][internalIterator].hasWall(Cell.DIRECTION.RIGHT)) {
                    line.append(WALL_STRING);
                } else {
                    line.append(SPACE_STRING);
                }

                if (maze[externalIterator][internalIterator].hasWall(Cell.DIRECTION.BOTTOM)) {
                    lowerLine.append(WALL_STRING);
                } else {
                    lowerLine.append(SPACE_STRING);
                }

                lowerLine.append(WALL_STRING);
            }

            logger.info(line);
            logger.info(lowerLine);
        }
    }

    private static int[][] transform(Pair[][] previousArray, Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2) {

        int[][] resultPath = new int[previousArray.length][previousArray[0].length];
        Pair previousPair = point2;

        while (!(previousPair.getLeft() == point1.getLeft() && previousPair.getRight() == point1.getRight())) {
            resultPath[(int) previousPair.getRight()][(int) previousPair.getLeft()] = 1;
            previousPair = previousArray[(int) previousPair.getRight()][(int) previousPair.getLeft()];
        }

        resultPath[point1.getRight()][point1.getLeft()] = 1;

        return resultPath;
    }

    public static void renderWithPath(Cell[][] maze, Pair[][] previousArray, Pair<Integer, Integer> point1,
        Pair<Integer, Integer> point2) {

        int[][] pathArray = transform(previousArray, point1, point2);

        logger.info(WALL_STRING.repeat(Math.max(0, 2 * maze.length + 1)));

        for (int externalIterator = 0; externalIterator < maze.length; externalIterator++) {
            StringBuilder line = new StringBuilder(WALL_STRING);
            StringBuilder lowerLine = new StringBuilder(WALL_STRING);

            for (int internalIterator = 0; internalIterator < maze.length; internalIterator++) {
                if (pathArray[externalIterator][internalIterator] == 1) {
                    line.append(" ● ");
                } else {
                    line.append(SPACE_STRING);
                }

                if (maze[externalIterator][internalIterator].hasWall(Cell.DIRECTION.RIGHT)) {
                    line.append(WALL_STRING);
                } else {
                    line.append(SPACE_STRING);
                }

                if (maze[externalIterator][internalIterator].hasWall(Cell.DIRECTION.BOTTOM)) {
                    lowerLine.append(WALL_STRING);
                } else {
                    lowerLine.append(SPACE_STRING);
                }

                lowerLine.append(WALL_STRING);
            }

            logger.info(line);
            logger.info(lowerLine);
        }
    }

    public static Pair<Integer, Integer> inputMazeSize() {
        int width;
        int height;
        String inputWidth;
        String inputHeight;

        while (true) {
            logger.info("Input maze width: ");
            inputWidth = in.nextLine();

            try {
                width = Integer.parseInt(inputWidth);
                break;
            } catch (NumberFormatException ignored) {
            }
        }

        while (true) {
            logger.info("Input maze height: ");
            inputHeight = in.nextLine();

            try {
                height = Integer.parseInt(inputHeight);
                break;
            } catch (NumberFormatException ignored) {
            }
        }

        return new ImmutablePair<>(width, height);
    }

    public static Pair<Integer, Integer> inputPoints(Cell[][] maze, String name) {
        int aX;
        int aY;
        String strAX;
        String strAY;

        while (true) {
            logger.info("Input coordinate X for " + name + " point:");
            strAX = in.nextLine();

            try {
                aX = Integer.parseInt(strAX);
                if (aX < 0 || aX >= maze[0].length) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException ignored) {
            }
        }
        while (true) {
            logger.info("Input coordinate Y for " + name + " point: ");
            strAY = in.nextLine();

            try {
                aY = Integer.parseInt(strAY);
                if (aY < 0 || aY >= maze.length) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException ignored) {
            }
        }

        return new ImmutablePair<>(aX, aY);
    }
}
