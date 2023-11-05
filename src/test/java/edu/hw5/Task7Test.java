package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы firstRegular")
    void firstRegularTest(String string, boolean result) {
        assertThat(Task7.firstRegular(string)).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider2.class)
    @DisplayName("Проверка работы secondRegular")
    void secondRegularTest(String string, boolean result) {
        assertThat(Task7.secondRegular(string)).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider3.class)
    @DisplayName("Проверка работы thirdRegular")
    void thirdRegularTest(String string, boolean result) {
        assertThat(Task7.thirdRegular(string)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("110111001", true),
                Arguments.of("0011100", false),
                Arguments.of("01", false),
                Arguments.of(null, false)
            );
        }
    }

    static class TestArgumentsProvider2 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("110111001", true),
                Arguments.of("0011100", true),
                Arguments.of("1", true),
                Arguments.of("0", true),
                Arguments.of("1001010", false),
                Arguments.of("", false),
                Arguments.of(null, false)
            );
        }
    }

    static class TestArgumentsProvider3 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("110", true),
                Arguments.of("0011", false),
                Arguments.of("10", true),
                Arguments.of("1", true),
                Arguments.of("", false),
                Arguments.of(null, false)
            );
        }
    }
}
