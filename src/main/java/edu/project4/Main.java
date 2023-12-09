package edu.project4;

import java.nio.file.Paths;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class Main {

    private Main() {}

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        FractalImage image = FractalRendererThread.render(new ImmutablePair<>(1000, 1000), 1000,
            200, 3, SymmetricType.NONE, NonLinearType.SPHERICAL, 2);

        FractalRenderer.gammaCorrection(image, 1000, 1000, 2);
        SaveImage.save(image, Paths.get("image.png"), "png");
    }
}
