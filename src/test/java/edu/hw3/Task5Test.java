package edu.hw3;

import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы atBash()")
    void parseContactsTest(String[] receivedArray, String param, String answer){
        assertThat(Arrays.toString(Task5.parseContacts(receivedArray, param))).isEqualTo(answer);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                    "ASC", "[Thomas Aquinas, Rene Descartes, David Hume, John Locke]"),
                Arguments.of(new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                    "DESC", "[Carl Gauss, Leonhard Euler, Paul Erdos]"),
                Arguments.of(new String[] {},
                    "DESC", "[]"),
                Arguments.of(null,
                    "DESC", "[]")
            );
        }
    }
}
