package edu.hw1;

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
    @DisplayName("Проверка работы minutesToSeconds()")
    void testMinutesToSeconds(String time, Long answer) {

        assertThat(Task1.minutesToSeconds(time)).isEqualTo(answer);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("12:00", 720L),
                Arguments.of("20:32", 1232L),
                Arguments.of("00:26", 26L),
                Arguments.of("02:83", -1L),
                Arguments.of("24", -1L),
                Arguments.of("12:22:03", -1L),
                Arguments.of("UPS:22", -1L)
            );
        }
    }
}
