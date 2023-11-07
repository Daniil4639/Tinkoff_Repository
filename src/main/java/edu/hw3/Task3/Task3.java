package edu.hw3.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    private Task3() {}

    public static <T> Map<T, Integer> freqDict(ArrayList<T> receivedArray) {
        Map<T, Integer> freqMap = new HashMap<>();

        for (T element: receivedArray) {
            freqMap.merge(element, 1, Integer::sum);
        }

        return freqMap;
    }
}
