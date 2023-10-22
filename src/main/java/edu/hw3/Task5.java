package edu.hw3;

import java.util.Arrays;
import java.util.Comparator;

public class Task5 {

    private Task5() {}

    public static String[] parseContacts(String[] contactsArray, String param) {
        if (contactsArray == null) {
            return new String[0];
        } else if (param.equals("ASC")) {
            Arrays.sort(contactsArray, new Comparator<String>() {
                public int compare(String firstStr, String secondStr) {
                    String[] firstArray = firstStr.split(" ");
                    String[] secondArray = secondStr.split(" ");

                    int compareIndex = firstArray[firstArray.length - 1]
                        .compareTo(secondArray[secondArray.length - 1]);

                    if (compareIndex >= 0) {
                        return 1;
                    }  else {
                        return -1;
                    }
                }
            });
        } else if (param.equals("DESC")) {
            Arrays.sort(contactsArray, new Comparator<String>() {
                public int compare(String firstStr, String secondStr) {
                    String[] firstArray = firstStr.split(" ");
                    String[] secondArray = secondStr.split(" ");

                    int compareIndex = firstArray[firstArray.length - 1]
                        .compareTo(secondArray[secondArray.length - 1]);

                    if (compareIndex >= 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
        }

        return contactsArray;
    }
}
