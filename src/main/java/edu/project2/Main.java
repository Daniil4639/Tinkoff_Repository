package edu.project2;

import java.util.Scanner;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static Logger logger = LogManager.getLogger();
    private static Scanner in = new Scanner(System.in);

    private Main() {}

    public static void main(String[] args) {
        Pair<Integer, Integer> inputValues = MazeRender.inputMazeSize();

        MazeGenerator maze = new MazeGenerator(inputValues.getLeft(), inputValues.getRight());
        maze.generate();
        MazeRender.render(maze.getMaze());

        Pair<Integer, Integer> coordinatesA = MazeRender.inputPoints(maze.getMaze(), "A");
        Pair<Integer, Integer> coordinatesB = MazeRender.inputPoints(maze.getMaze(), "B");

        logger.info("Find way with BFS:");

        MazeRender.renderWithPath(maze.getMaze(), MazeBFSSearch.findWay(
            maze.getMaze(), coordinatesA, coordinatesB),
            coordinatesA, coordinatesB);

        logger.info("Find way with DFS:");

        MazeRender.renderWithPath(maze.getMaze(), MazeDFSSearch.findWay(
                maze.getMaze(), coordinatesA, coordinatesB),
            coordinatesA, coordinatesB);
    }
}
