package edu.hw1;

public final class Task4 {

    private Task4() {
    }

    public static String fixString(String receivedString) {
        if (receivedString == null) {
            return null;
        }

        char[] lettersArray = receivedString.toCharArray();

        for (int letterIndex = 1; letterIndex < receivedString.length(); letterIndex += 2) {
            char buffer = lettersArray[letterIndex - 1];
            lettersArray[letterIndex - 1] = lettersArray[letterIndex];
            lettersArray[letterIndex] = buffer;
        }

        return new String(lettersArray);
    }
}
