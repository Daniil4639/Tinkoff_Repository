package edu.project2;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {}

    public static void main(String[] args) {
        Pair<Integer, Integer> inputValues = MazeRender.inputMazeSize();

        MazeGenerator maze = new MazeGenerator(inputValues.getLeft(), inputValues.getRight());
        maze.generate();
        MazeRender.render(maze.getMaze());

        Pair<Integer, Integer> coordinatesA = MazeRender.inputPoints(maze.getMaze(), "A");
        Pair<Integer, Integer> coordinatesB = MazeRender.inputPoints(maze.getMaze(), "B");

        LOGGER.info("Find way with BFS:");

        MazeRender.renderWithPath(maze.getMaze(), MazeBFSSearch.findWay(
            maze.getMaze(), coordinatesA, coordinatesB),
            coordinatesA, coordinatesB);

        LOGGER.info("Find way with DFS:");

        MazeRender.renderWithPath(maze.getMaze(), MazeDFSSearch.findWay(
                maze.getMaze(), coordinatesA, coordinatesB),
            coordinatesA, coordinatesB);
    }
}
