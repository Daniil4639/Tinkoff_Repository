package edu.project4;

import java.nio.file.Paths;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class Main {

    private Main() {}

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        FractalImage image = FractalRendererThread.render(new ImmutablePair<>(4000, 4000), 5000,
            700, 10, SymmetricType.ROTATION_45_DEG, NonLinearType.SPHERICAL, 2);

        FractalRenderer.gammaCorrection(image, 4000, 4000, 2);
        SaveImage.save(image, Paths.get("image.png"), "png");
    }
}
