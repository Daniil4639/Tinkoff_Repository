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

public class Task5Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы isPalindromeDescendant()")
    void testIsPalindromeDescendant(long number, boolean answer) {

        assertThat(Task5.isPalindromeDescendant(number)).isEqualTo(answer);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(11211230, true),
                Arguments.of(432112, false),
                Arguments.of(23336014, true),
                Arguments.of(11, true),
                Arguments.of(-12321, false),
                Arguments.of(5311028, true)
            );
        }
    }
}
