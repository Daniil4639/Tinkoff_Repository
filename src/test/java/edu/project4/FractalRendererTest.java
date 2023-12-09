package edu.project4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FractalRendererTest {

    @Test
    @DisplayName("Проверка работы FractalRenderer")
    void fractalRendererTest() {
        boolean imageIsNotEmpty = false;

        FractalImage image = FractalRenderer.render(1000, 1000, 5000, 500, 2, SymmetricType.NONE, NonLinearType.POLAR);

        for (List<Pixel> raw: image.getMatrix()) {
            for (Pixel elem: raw) {
                if (elem.rChanel != 0 || elem.bChanel != 0 || elem.gChanel != 0) {
                    imageIsNotEmpty = true;
                    break;
                }
            }

            if (imageIsNotEmpty) {
                break;
            }
        }

        assertThat(imageIsNotEmpty).isTrue();
    }

    @Test
    @DisplayName("Проверка работы FractalRendererThread")
    void fractalRendererThreadTest() {
        boolean imageIsNotEmpty = false;

        FractalImage image = FractalRendererThread.render(1000, 1000, 5000, 500, 2, SymmetricType.NONE, NonLinearType.POLAR);

        for (List<Pixel> raw: image.getMatrix()) {
            for (Pixel elem: raw) {
                if (elem.rChanel != 0 || elem.bChanel != 0 || elem.gChanel != 0) {
                    imageIsNotEmpty = true;
                    break;
                }
            }

            if (imageIsNotEmpty) {
                break;
            }
        }

        assertThat(imageIsNotEmpty).isTrue();
    }
}
