package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы friday13ThSearch")
    void friday13ThSearchTest(int year, String result) {
        assertThat(Task2.friday13ThSearch(year).toString()).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider2.class)
    @DisplayName("Проверка работы nextFriday13Th")
    void nextFriday13ThTest(String date, String result) {
        assertThat(Task2.nextFriday13Th(date)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(2024, "[2024-09-13, 2024-12-13]"),
                Arguments.of(1925, "[1925-02-13, 1925-03-13, 1925-11-13]"),
                Arguments.of(3742, "[3742-04-13, 3742-07-13]")
                );
        }
    }

    static class TestArgumentsProvider2 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("2024-09-13", "2024-12-13"),
                Arguments.of("1925-02-13", "1925-03-13"),
                Arguments.of("3412-11-13", "3413-08-13")
            );
        }
    }
}
