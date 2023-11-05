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

public class Task4Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы passwordIsCorrect")
    void passwordIsCorrectTest(String password, boolean result) {
        assertThat(Task4.passwordIsCorrect(password)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("serjgfnskjdfmwks", false),
                Arguments.of("archer-1212!", true),
                Arguments.of("@!^^&kjsokds##", true),
                Arguments.of(null, false)
            );
        }
    }
}
