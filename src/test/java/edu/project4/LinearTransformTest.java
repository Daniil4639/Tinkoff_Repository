package edu.project4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LinearTransformTest {

    @Test
    @DisplayName("Проверка работы LinearTransform")
    void linearTransformTest() {
        LinearTransform transform = new LinearTransform(1, -1, 1, -1);

        boolean transformOk = !(transform.a * transform.a + transform.d * transform.d >= 1);

        if (transformOk && transform.b * transform.b + transform.e * transform.e >= 1) {
            transformOk = false;
        }

        if (transformOk && transform.a * transform.a + transform.d * transform.d + transform.b * transform.b
            + transform.e * transform.e >= 1 + Math.pow(transform.a * transform.e - transform.b * transform.d, 2)) {
            transformOk = false;
        }

        assertThat(transformOk).isTrue();
    }
}
