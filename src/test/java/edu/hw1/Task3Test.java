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

public class Task3Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы isNestable()")
    void testIsNestable(long[] array_1, long[] array_2, boolean answer) {

        assertThat(Task3.isNestable(array_1, array_2)).isEqualTo(answer);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(new long[] {12, 18, 33, 59, 102}, new long[] {144, 9}, true),
                Arguments.of(new long[] {14, 7}, new long[] {3, 15}, true),
                Arguments.of(new long[] {-111, 64, 312}, new long[] {312, -150}, false),
                Arguments.of(new long[] {3, 42, -204, 70}, new long[] {-105, 22, 5}, false),
                Arguments.of(new long[] {}, new long[] {2, 3, 17}, false),
                Arguments.of(new long[] {3, 5, 12}, null, false)
            );
        }
    }
}
