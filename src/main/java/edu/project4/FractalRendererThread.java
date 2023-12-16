package edu.project4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;

public class FractalRendererThread {

    private static final int START_ITERATION_COUNT = -20;
    private static final int ROTATION_COUNT_45_DEG = 8;
    private static final double DEG_45 = Math.PI / 4;
    private static final int X_MAX = 1;
    private static final int X_MIN = -1;
    private static final int Y_MAX = 1;
    private static final int Y_MIN = -1;

    private static int screenWidth;
    private static int screenHeight;
    private static FractalImage image;
    private static SymmetricType symmetricType;
    private static NonLinearType nonLinearType;
    private static int linearCount;
    private static List<LinearTransform> linearTransforms;

    private FractalRendererThread() {}

    public static FractalImage render(Pair<Integer, Integer> size, int samples,
        int iterations, int lCount, SymmetricType sType, NonLinearType nLType, int threadCount) {

        screenWidth = size.getLeft();
        screenHeight = size.getRight();
        image = new FractalImage(screenWidth, screenHeight);
        symmetricType = sType;
        nonLinearType = nLType;
        linearCount = lCount;

        linearTransforms = new ArrayList<>();
        for (int ind = 0; ind < linearCount; ind++) {
            linearTransforms.add(new LinearTransform(X_MAX, X_MIN, Y_MAX, Y_MIN));
        }

        List<FractalThread> threads = new ArrayList<>();
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            threads.add(new FractalThread(iterations, samples / threadCount));
        }

        var futures = Stream.generate(() -> CompletableFuture
                .runAsync(new FractalThread(iterations, samples / threadCount)))
                .limit(threadCount)
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();

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

    private record FractalThread(int iterations, int samples) implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < samples; i++) {
                double nextX = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
                double nextY = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);

                for (int step = START_ITERATION_COUNT; step < iterations; step++) {
                    int linearIndex = ThreadLocalRandom.current().nextInt(0, linearCount);

                    Pair<Double, Double> linearValues = linearTransforms.get(
                        linearIndex).eval(nextX, nextY);

                    Pair<Double, Double> nonLinearValues = NonLinearTransform.eval(linearValues, nonLinearType);

                    nextX = nonLinearValues.getLeft();
                    nextY = nonLinearValues.getRight();

                    if (step >= 0) {
                        int x1 = (int) (((X_MAX - nextX) / (X_MAX - X_MIN)) * screenWidth);
                        int y1 = (int) (((Y_MAX - nextY) / (Y_MAX - Y_MIN)) * screenHeight);

                        if (x1 < screenWidth && x1 >= 0 && y1 < screenHeight && y1 >= 0) {
                            colorPixel(image.getMatrix().get(y1).get(x1), linearTransforms.get(linearIndex));

                            makeSymmetric(symmetricType, image, linearTransforms.get(linearIndex), x1, y1);
                        }
                    }
                }
            }
        }
    }
}
