package edu.project4;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class LinearTransform {

    private static final int MAXIMUM_COLOR_FRACTION = 255;

    public double a;
    public double b;
    public double c;
    public double d;
    public double e;
    public double f;
    public byte red;
    public byte green;
    public byte blue;

    public LinearTransform(double xMax, double xMin, double yMax, double yMin) {
        c = ThreadLocalRandom.current().nextDouble(xMin, xMax);
        f = ThreadLocalRandom.current().nextDouble(yMin, yMax);

        generateNumbers();
        generateColor();
    }

    public Pair<Double, Double> eval(double nextX, double nextY) {
        double x = a * nextX + b * nextY + c;
        double y = d * nextX + e * nextY + f;

        return new ImmutablePair<>(x, y);
    }

    private void generateNumbers() {
        double tempA;
        double tempB;
        double tempD;
        double tempE;

        while (true) {
            tempA = ThreadLocalRandom.current().nextDouble(-1, 1);
            tempB = ThreadLocalRandom.current().nextDouble(-1, 1);
            tempD = ThreadLocalRandom.current().nextDouble(-1, 1);
            tempE = ThreadLocalRandom.current().nextDouble(-1, 1);

            if ((tempA * tempA + tempB * tempB + tempD * tempD + tempE * tempE
                < 1 + Math.pow(tempA * tempE - tempB * tempD, 2))
                && (tempA * tempA + tempD + tempD < 1) && (tempB * tempB + tempE * tempE < 1)) {

                a = tempA;
                b = tempB;
                d = tempD;
                e = tempE;
                break;
            }
        }
    }

    private void generateColor() {
        blue = (byte) ThreadLocalRandom.current().nextDouble(0, MAXIMUM_COLOR_FRACTION);
        red = (byte) ThreadLocalRandom.current().nextDouble(0, MAXIMUM_COLOR_FRACTION);
        green = (byte) ThreadLocalRandom.current().nextDouble(0, MAXIMUM_COLOR_FRACTION);
    }
}
