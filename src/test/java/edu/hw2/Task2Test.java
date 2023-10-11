package edu.hw2;

import edu.hw1.Task1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы Rectangle и Square")
    void areaTest(Task2.Rectangle rectangle, int width, int height, int correctArea){
        Task2.Rectangle resultRect = rectangle.setWidth(width);
        resultRect.setHeight(height);

        assertThat(resultRect.area()).isEqualTo(correctArea);
    }

        static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                // object, width, length, area
                Arguments.of(new Task2.Rectangle(), 20, 10, 200),
                Arguments.of(new Task2.Square(), 12, 8, 96),
                Arguments.of(new Task2.Rectangle(), 4, 4, 16),
                Arguments.of(new Task2.Square(), 1000, 1, 1000)
            );
        }
    }
}
