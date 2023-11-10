package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Comparator;

public class Task5 {

    private Task5() {}

    private static final String SPACE_SEQUENCE = "\\s+";

    public static String[] parseContacts(String[] contactsArray, String param) {
        if (contactsArray == null) {
            return new String[0];
        } else {
            Arrays.sort(contactsArray, new SecondNameComparator(param));
        }

        return contactsArray;
    }

    public static final class SecondNameComparator implements Comparator<String> {

        private int mode;

        public SecondNameComparator(String mode) {
            if (mode.equals("ASC")) {
                this.mode = 1;
            } else {
                this.mode = -1;
            }
        }

        @Override
        public int compare(String firstStr, String secondStr) {
            String[] firstArray = firstStr.split(SPACE_SEQUENCE);
            String[] secondArray = secondStr.split(SPACE_SEQUENCE);

            int compareIndex = firstArray[firstArray.length - 1]
                .compareTo(secondArray[secondArray.length - 1]);

            if (compareIndex > 0) {
                return mode;
            } else if (compareIndex < 0) {
                return -mode;
            } else {
                return 0;
            }
        }
    }
}
