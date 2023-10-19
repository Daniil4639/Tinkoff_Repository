package edu.project1;

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

public class UnknownWordMethodsTest {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы checkTheLetter()")
    void letterIsInWord(String word, char firstSymbol, char secondSymbol,
        boolean firstAnswer, boolean secondAnswer) {

        GuessedWord guessedWord = new GuessedWord(word);
        UnknownWord unknownWord = new UnknownWord(guessedWord);

        try {
            boolean answer1 = unknownWord.checkTheLetter(guessedWord, firstSymbol);

            assertThat(answer1).isEqualTo(firstAnswer);

            boolean answer2 = unknownWord.checkTheLetter(guessedWord, secondSymbol);

            assertThat(answer2).isEqualTo(secondAnswer);
        } catch (WordAlreadyHasTheLetterException wordAlreadyHasTheLetterException){
            assertThat(true).isEqualTo(true);
        }
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("assembler", 's', 'a', true, true),
                Arguments.of("programming", 'j', 'm', false, true),
                Arguments.of("reconstruction", 'o', 'o', true, true)
            );
        }
    }
}
