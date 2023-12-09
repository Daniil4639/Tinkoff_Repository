package edu.project4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.tuple.Pair;

public class FractalRenderer {

    private static final int START_ITERATION_COUNT = -20;
    private static final int ROTATION_COUNT_45_DEG = 8;
    private static final double DEG_45 = Math.PI / 4;

    private FractalRenderer() {}

    public static FractalImage render(int width, int height, int samples,
        int iterations, int linearCount, SymmetricType symmetricType, NonLinearType nonLinearType) {

        double xMax = 1;
        double xMin = -1;
        double yMax = 1;
        double yMin = -1;

        FractalImage image = new FractalImage(width, height);

        List<LinearTransform> linearTransforms = new ArrayList<>();
        for (int ind = 0; ind < linearCount; ind++) {
            linearTransforms.add(new LinearTransform(xMax, xMin, yMax, yMin));
        }

        for (int i = 0; i < samples; i++) {
            double nextX = ThreadLocalRandom.current().nextDouble(xMin, xMax);
            double nextY = ThreadLocalRandom.current().nextDouble(yMin, yMax);

            for (int step = START_ITERATION_COUNT; step < iterations; step++) {
                int linearIndex = ThreadLocalRandom.current().nextInt(0, linearCount);

                Pair<Double, Double> linearValues = linearTransforms.get(
                    linearIndex).eval(nextX, nextY);

                Pair<Double, Double> nonLinearValues = NonLinearTransform.eval(linearValues, nonLinearType);

                nextX = nonLinearValues.getLeft();
                nextY = nonLinearValues.getRight();

                if (step >= 0) {
                    int x1 = (int) (((xMax - nextX) / (xMax - xMin)) * width);
                    int y1 = (int) (((yMax - nextY) / (yMax - yMin)) * height);

                    if (x1 < width && x1 >= 0 && y1 < height && y1 >= 0) {
                        colorPixel(image.getMatrix().get(y1).get(x1), linearTransforms.get(linearIndex));

                        makeSymmetric(symmetricType, image, linearTransforms.get(linearIndex), x1, y1);
                    }
                }
            }
        }

        return image;
    }

    private static void makeSymmetric(SymmetricType type, FractalImage image, LinearTransform transform,
        int x1, int y1) {

        int width = image.getScreenWidth();
        int height = image.getScreenHeight();

        switch (type) {
            case HALF_SCREEN -> {
                int tempX1 = width - x1 - 1;
                int tempY1 = height - y1 - 1;
                colorPixel(image.getMatrix().get(tempY1).get(tempX1), transform);

                tempX1 = x1;
                tempY1 = height - y1 - 1;
                colorPixel(image.getMatrix().get(tempY1).get(tempX1), transform);

                tempX1 = width - x1 - 1;
                tempY1 = y1;
                colorPixel(image.getMatrix().get(tempY1).get(tempX1), transform);
            }
            case ROTATION_45_DEG -> {
                double r = Math.sqrt((x1 - width / 2.0) * (x1 - width / 2.0)
                    + (y1 - height / 2.0) * (y1 - height / 2.0));

                double u = Math.atan((y1 - height / 2.0) / (x1 - width / 2.0));

                for (int rotate = 0; rotate < ROTATION_COUNT_45_DEG; rotate++) {
                    u += DEG_45;

                    int tempX = (int) (r * Math.cos(u) + width / 2.0);
                    int tempY = (int) (r * Math.sin(u) + height / 2.0);

                    if (tempX < width && tempX >= 0 && tempY < height && tempY >= 0) {
                        colorPixel(image.getMatrix().get(tempY).get(tempX), transform);
                    }
                }
            }
            case null, default -> {
            }
        }
    }

    private static void colorPixel(Pixel pixel, LinearTransform transform) {
        if (pixel.hitCount == 0) {
            pixel.rChanel = transform.red;
            pixel.gChanel = transform.green;
            pixel.bChanel = transform.blue;
        } else {
            pixel.rChanel = (byte) ((pixel.rChanel
                + transform.red) / 2);

            pixel.gChanel = (byte) ((pixel.gChanel
                + transform.green) / 2);

            pixel.bChanel = (byte) ((pixel.bChanel
                + transform.blue) / 2);
        }

        pixel.hitCount++;
    }

    public static void gammaCorrection(FractalImage image, int xRes, int yRes, double gamma) {
        double max = 0;

        for (List<Pixel> raw: image.getMatrix()) {
            for (Pixel elem: raw) {
                if (elem.hitCount != 0) {
                    elem.normal = Math.log10(elem.hitCount);
                }

                if (elem.normal > max) {
                    max = elem.normal;
                }
            }
        }

        for (List<Pixel> raw: image.getMatrix()) {
            for (Pixel elem : raw) {
                elem.normal /= max;
                elem.rChanel = (byte) (elem.rChanel * Math.pow(elem.normal, (1.0 / gamma)));
                elem.gChanel = (byte) (elem.gChanel * Math.pow(elem.normal, (1.0 / gamma)));
                elem.bChanel = (byte) (elem.bChanel * Math.pow(elem.normal, (1.0 / gamma)));
            }
        }
    }
}
