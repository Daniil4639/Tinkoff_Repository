package edu.project1;

public final class WordsExceptions {

    public static class WordAlreadyHasTheLetterException extends Exception {

        public WordAlreadyHasTheLetterException() {
        }
    }

    public static class ProgramEnd extends Exception {

        public ProgramEnd() {
        }
    }
}
