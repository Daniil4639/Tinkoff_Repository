package edu.project4;

import java.util.ArrayList;
import java.util.List;

public class FractalImage {

    private final List<List<Pixel>> matrix;
    private final int screenWidth;
    private final int screenHeight;

    public FractalImage(int width, int height) {
        screenWidth = width;
        screenHeight = height;

        matrix = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<Pixel> raw = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                raw.add(new Pixel(j, i));
            }
            matrix.add(raw);
        }
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public List<List<Pixel>> getMatrix() {
        return matrix;
    }
}
