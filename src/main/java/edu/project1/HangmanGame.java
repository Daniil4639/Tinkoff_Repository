package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HangmanGame {

    private Scanner scanner = new Scanner(System.in);
    private final int maxAttemptsCount = 8;
    private final int systemSizeValue = 10;
    private final String theLetterString = "The letter '";
    private final static Logger LOGGER = LogManager.getLogger();

    private final ArrayList<String> dictionary = new ArrayList<>(Arrays.asList(
        "program", "assembler", "computer", "recursion", "debugging"));

    public String chooseRandomString() {
        int randomNumber = ((int) (Math.random() * systemSizeValue)) % dictionary.size();
        return dictionary.get(randomNumber);
    }

    private void instructionOutput() {
        LOGGER.info("*------------------------------------------------------------------------*");
        LOGGER.info("Training: the computer guesses a word related to programming;"
            + " your task is to guess this word.\n");
        LOGGER.info("You can enter the letters that you think are contained in this word.");
        LOGGER.info("If you make a mistake, the attempt counter will decrease by 1.");
        LOGGER.info("When the zero value is reached, the game ends.");
        LOGGER.info("If you want to finish the game earlier, input 'end'.\n");
        LOGGER.info("Good Luck!");
        LOGGER.info("*-----------------------------------------------------------------------*");
    }

    private boolean yesNoListener() {
        String inputStr;

        while (true) {
            inputStr = scanner.nextLine();
            if (inputStr.equals("no")) {
                return false;
            } else if (inputStr.equals("yes")) {
                return true;
            }
        }
    }

    public boolean wordWasOpened(String word) {

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '*') {
                return false;
            }
        }

        return true;
    }

    private char getInputLetter() {
        String letter;
        boolean inputOk = false;
        char returnValue = ' ';

        do {
            letter = scanner.nextLine();
            if (letter.length() == 1) {
                inputOk = true;
                returnValue = letter.charAt(0);

            } else if (letter.equals("end")) {
                inputOk = true;
                returnValue = '\0';
            }

            if (inputOk) {
                return returnValue;
            }

            LOGGER.info("Incorrect input! Repeat: (only 1 symbol)");
        } while (true);
    }

    private void gameEscalating(GuessedWord guessedWord, UnknownWord unknownWord)
        throws WordsExceptions.WordAlreadyHasTheLetterException {

        int currentAttempts = maxAttemptsCount;

        LOGGER.info(unknownWord.getWord());

        while (currentAttempts != 0) {
            LOGGER.info("You have " + Integer.toString(currentAttempts) + " attempts!");

            char inputLetter = getInputLetter();
            if (inputLetter == '\0') {
                throw new WordsExceptions.WordAlreadyHasTheLetterException();
            }

            boolean checkIsOk;

            try {
                checkIsOk = unknownWord.checkTheLetter(guessedWord, inputLetter);
            } catch (WordsExceptions.WordAlreadyHasTheLetterException
                wordAlreadyHasTheLetterException) {

                LOGGER.info("You have already entered this letter!");
                continue;
            }

            if (checkIsOk) {
                LOGGER.info(theLetterString + inputLetter + "' is in this word!");
            } else {
                LOGGER.info(theLetterString + inputLetter + "' is not in this word!");
                currentAttempts--;
            }
            LOGGER.info(unknownWord.getWord());

            if (wordWasOpened(unknownWord.getWord())) {
                LOGGER.info("You won!");
                return;
            }
        }

        LOGGER.info("You lose!");
    }

    public void run() {
        LOGGER.info("Welcome to the Game: Hangman!");
        LOGGER.info("Do you want to listen to the instructions (yes // no)?");

        if (yesNoListener()) {
            instructionOutput();
        } else {
            LOGGER.info("Let's start!\n");
        }


        GuessedWord guessedWord = new GuessedWord(chooseRandomString());
        UnknownWord unknownWord = new UnknownWord(guessedWord);

        try {
            gameEscalating(guessedWord, unknownWord);
        } catch (WordsExceptions.WordAlreadyHasTheLetterException wordAlreadyHasTheLetterException) {
            LOGGER.info("You decided to finish the game!");
        }

        LOGGER.info("The word was '" + guessedWord.getWord() + "'.");
    }
}
