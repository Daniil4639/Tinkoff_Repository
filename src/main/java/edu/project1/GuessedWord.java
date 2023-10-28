package edu.project1;

import java.util.ArrayList;

public class GuessedWord extends Word {

    public GuessedWord() {
    }

    public GuessedWord(String word) {
        super(word);
    }

    public ArrayList<Integer> whereIsTheLetter(char symbol) {
        ArrayList<Integer> symbolPositions = new ArrayList<>();

        for (int iterator = 0; iterator < getWord().length(); iterator++) {
            if (getWord().charAt(iterator) == symbol) {
                symbolPositions.add(iterator);
            }
        }

        return symbolPositions;
    }
}
