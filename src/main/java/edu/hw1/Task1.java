package edu.hw1;

public final class Task1 {

    public static long minutesToSeconds(String time) {
        String[] tokenArray = time.split(":");

        if (Long.parseLong(tokenArray[1]) >= 60 || tokenArray.length != 2) {
            return -1;
        }

        return Long.parseLong(tokenArray[1]) + Long.parseLong(tokenArray[0]) * 60;
    }
}
