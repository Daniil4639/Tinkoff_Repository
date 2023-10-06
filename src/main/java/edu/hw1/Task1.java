package edu.hw1;

public final class Task1 {

    private Task1() {
    }

    private static final int SECONDS_IN_MINUTE = 60;

    public static long minutesToSeconds(String time) {
        String[] tokenArray = time.split(":");

        if (tokenArray.length != 2) {
            return -1;
        }

        long receivedTimeSeconds;
        long receivedTimeMinutes;

        try {
            receivedTimeSeconds = Long.parseLong(tokenArray[1]);
            receivedTimeMinutes = Long.parseLong(tokenArray[0]);
        } catch (NumberFormatException numberFormatException) {
            return -1;
        }

        if (receivedTimeSeconds >= SECONDS_IN_MINUTE) {
            return -1;
        } else {
            return receivedTimeSeconds + receivedTimeMinutes * SECONDS_IN_MINUTE;
        }
    }
}
