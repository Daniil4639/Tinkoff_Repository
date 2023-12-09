package edu.project4;

import java.nio.file.Paths;

public class Main {

    private Main() {}

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        FractalImage image = FractalRendererThread.render(4000, 4000, 20000, 7000,
            10, SymmetricType.ROTATION_45_DEG, NonLinearType.SPHERICAL);

        FractalRenderer.gammaCorrection(image, 4000, 4000, 2);
        SaveImage.save(image, Paths.get("image.png"), "png");
    }
}
