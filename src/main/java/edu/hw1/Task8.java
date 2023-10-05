package edu.hw1;

import java.awt.Point;
import java.util.ArrayList;

public final class Task8 {

    private Task8() {
    }

    private static final int CHESS_BOARD_SIZE = 8;

    private static final int KNIGHT_ATTACK_RADIUS = 3;

    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != CHESS_BOARD_SIZE || board[0].length != CHESS_BOARD_SIZE) {
            return false;
        }

        ArrayList<Point> knightPosArray = new ArrayList<>();

        for (int xIndex = 0; xIndex < CHESS_BOARD_SIZE; xIndex++) {
            for (int yIndex = 0; yIndex < CHESS_BOARD_SIZE; yIndex++) {
                if (board[xIndex][yIndex] == 1) {

                    knightPosArray.add(new Point(xIndex, yIndex));
                    for (int knightInArrayIndex = 0; knightInArrayIndex < knightPosArray.size() - 1;
                         knightInArrayIndex++) {
                        if (Math.abs(xIndex - knightPosArray.get(knightInArrayIndex).x) != 0
                            && Math.abs(yIndex - knightPosArray.get(knightInArrayIndex).y) != 0
                            && Math.abs(xIndex - knightPosArray.get(knightInArrayIndex).x)
                            + Math.abs(yIndex - knightPosArray.get(knightInArrayIndex).y) == KNIGHT_ATTACK_RADIUS) {

                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
