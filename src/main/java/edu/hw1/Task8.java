package edu.hw1;

import java.awt.Point;
import java.util.ArrayList;

public class Task8 {

    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != 8 || board[0].length != 8) {
            return false;
        }

        ArrayList<Point> knightPosArray = new ArrayList<>();

        for (int xIndex = 0; xIndex < 8; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                if (board[xIndex][yIndex] == 1) {

                    knightPosArray.add(new Point(xIndex, yIndex));
                    for (int knightInArrayIndex = 0; knightInArrayIndex < knightPosArray.size() - 1; knightInArrayIndex++) {
                        if (Math.abs(xIndex - knightPosArray.get(knightInArrayIndex).x) != 0 && Math.abs(yIndex - knightPosArray.get(knightInArrayIndex).y) != 0 &&
                            Math.abs(xIndex - knightPosArray.get(knightInArrayIndex).x) + Math.abs(yIndex - knightPosArray.get(knightInArrayIndex).y) == 3) {

                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
