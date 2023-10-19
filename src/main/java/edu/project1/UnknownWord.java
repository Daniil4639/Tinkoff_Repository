package edu.project1;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class UnknownWord extends Word {

    public UnknownWord() {
    }

    public UnknownWord(GuessedWord receivedWord) {
        super(StringUtils.repeat('*', receivedWord.getWord().length()));
    }

    public boolean checkTheLetter(GuessedWord guessedWord, char symbol) throws WordAlreadyHasTheLetterException {
        ArrayList<Integer> positionArray = guessedWord.whereIsTheLetter(symbol);

        if (positionArray.isEmpty()) {
            return false;
        }

        StringBuilder newWord = new StringBuilder(getWord());

        for (int iterator = 0; iterator < positionArray.size(); iterator++) {
            if (getWord().charAt(positionArray.get(iterator)) != '*') {
                throw new WordAlreadyHasTheLetterException();
            } else {
                newWord.setCharAt(positionArray.get(iterator), symbol);
            }
        }

        setWord(newWord.toString());
        return true;
    }
}
