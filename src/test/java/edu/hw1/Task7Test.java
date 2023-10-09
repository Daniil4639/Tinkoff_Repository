package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestLeftArgumentsProvider.class)
    @DisplayName("Проверка работы rotateLeft()")
    void testRotateLeft(int receivedNumber, int shift, int answer) {

        int[] correctArrLeft = {Task7.rotateLeft(56, 1),
            Task7.rotateLeft(102, 4),
            Task7.rotateLeft(0, 7),
            Task7.rotateLeft(23, -3),
            Task7.rotateLeft(-75, 9)};

        assertThat(correctArrLeft).containsExactly(49, 108, 0, 23, -75);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestRightArgumentsProvider.class)
    @DisplayName("Проверка работы rotateRight()")
    void testRotateRight() {

        int[] correctArrRight = {Task7.rotateRight(56, 1),
            Task7.rotateRight(102, 4),
            Task7.rotateRight(0, 7),
            Task7.rotateRight(23, -3),
            Task7.rotateRight(-75, 9)};

        assertThat(correctArrRight).containsExactly(28, 54, 0, 23, -75);
    }

    static class TestLeftArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(56, 1, 49),
                Arguments.of(102, 4, 108),
                Arguments.of(0, 7, 0),
                Arguments.of(23, -3, 23),
                Arguments.of(-75, 9, -75)
            );
        }
    }

    static class TestRightArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(56, 1, 28),
                Arguments.of(102, 4, 54),
                Arguments.of(0, 7, 0),
                Arguments.of(23, -3, 23),
                Arguments.of(-75, 9, -75)
            );
        }
    }
}
