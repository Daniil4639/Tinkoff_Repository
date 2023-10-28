package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanGameMethodsTest {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы wordWasOpened()")
    void isWordOpened(String word, boolean isOpened){
        assertThat(new HangmanGame().wordWasOpened(word)).isEqualTo(isOpened);
    }

    @Test
    @DisplayName("Проверка работы chooseRandomString()")
    void correctString() {
        for (int iterator = 0; iterator < 4; iterator++) {
            String newString = new HangmanGame().chooseRandomString();
            assertThat(newString == null).isEqualTo(false);
            assertThat(newString.isEmpty()).isEqualTo(false);
        }
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("assembler", true),
                Arguments.of("ass*mbl*r", false),
                Arguments.of("*********", false)
            );
        }
    }
}
