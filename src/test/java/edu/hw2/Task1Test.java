package edu.hw2;

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

public class Task1Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы Constant")
    void mathConstantTest(double[] values) {
        assertThat(new Task1.Constant(values[0]).evaluate()).isEqualTo(values[3]);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы Negate")
    void mathNegateTest(double[] values) {
        assertThat(new Task1.Negate(new Task1.Constant(values[0])).evaluate()).isEqualTo(values[4]);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы Exponent")
    void mathExponentTest(double[] values) {
        assertThat(new Task1.Exponent(
            new Task1.Constant(values[0]),
            values[2]
        ).evaluate()).isEqualTo(values[5]);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы Addition")
    void mathAdditionTest(double[] values) {
        assertThat(new Task1.Addition(
            new Task1.Constant(values[0]),
            new Task1.Constant(values[1])
        ).evaluate()).isEqualTo(values[6]);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы Multiplication")
    void mathMultiplicationTest(double[] values) {
        assertThat(new Task1.Multiplication(
            new Task1.Constant(values[0]),
            new Task1.Constant(values[1])
        ).evaluate()).isEqualTo(values[7]);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                // [first_value, second_value, exponent, Constant_Answer, Negate_Answer,
                // Exponent_Answer, Addition_Answer, Multiplication_Answer]
                Arguments.of((Object) new double[] {0, 2, 2, 0, 0, 0, 2, 0}),
                Arguments.of((Object) new double[] {2, 17, 0, 2, -2, 1, 19, 34}),
                Arguments.of((Object) new double[] {-4, 33, -2, -4, 4, 0.0625, 29, -132}),
                Arguments.of((Object) new double[] {1, -400, 12, 1, -1, 1, -399, -400})
            );
        }
    }
}
