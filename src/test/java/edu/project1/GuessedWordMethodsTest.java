package edu.project1;

import edu.hw2.Task1Test;
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

public class GuessedWordMethodsTest {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы whereIsTheLetter")
    void correctLetterPosition(String word, char letter, ArrayList<Integer> positions){
        assertThat(new GuessedWord(word).whereIsTheLetter(letter)).isEqualTo(positions);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("assembler", 's',
                    new ArrayList<Integer>(Arrays.asList(1,2))),
                Arguments.of("programming", 'g',
                    new ArrayList<Integer>(Arrays.asList(3,10))),
                Arguments.of("reconstruction", 'o',
                    new ArrayList<Integer>(Arrays.asList(3,12)))
            );
        }
    }
}
