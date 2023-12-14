package edu.hw11;

import edu.hw11.Task2.ArithmeticUtils;
import edu.hw11.Task2.ClassRedefiner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Изменение поведения класса с помощью ByteBuddy")
    void behaviourChangeTest() throws IOException {
        ClassRedefiner.redefineMethod();

        assertThat(ArithmeticUtils.sum(4, 2)).isEqualTo(8);
    }
}
