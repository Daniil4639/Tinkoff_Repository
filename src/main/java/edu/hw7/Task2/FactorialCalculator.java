package edu.hw7.Task2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactorialCalculator {

    private FactorialCalculator() {}

    public static BigInteger calculate(int number) {
        if (number <= 0) {
            return new BigInteger(String.valueOf(1));
        } else {
            List<BigInteger> numberList = new ArrayList<>();
            for (int i = 1; i <= number; i++) {
                numberList.add(BigInteger.valueOf(i));
            }

            return numberList.parallelStream().reduce(BigInteger::multiply).get();
        }
    }
}
