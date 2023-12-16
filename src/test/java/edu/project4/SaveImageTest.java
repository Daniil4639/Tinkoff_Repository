package edu.project4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SaveImageTest {

    @Test
    @DisplayName("Проверка работы SaveImage")
    void saveImageTest() throws IOException {
        FractalImage image = FractalRenderer.render(2000, 2000, 5000, 500,
            5, SymmetricType.ROTATION_45_DEG, NonLinearType.SPHERICAL);

        SaveImage.save(image, Paths.get("image.png"), "png");

        File fileWithImage = new File("image.png");
        assertThat(fileWithImage.exists()).isTrue();
        fileWithImage.delete();
    }
}
