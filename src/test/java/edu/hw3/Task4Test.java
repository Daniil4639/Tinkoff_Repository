package edu.hw3;

import edu.hw3.Task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы atBash()")
    void convertToRomanTest(int receivedNumber, String answer){
        assertThat(Task4.convertToRoman(receivedNumber)).isEqualTo(answer);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(2, "II"),
                Arguments.of(1663 , "MDCLXIII"),
                Arguments.of(39, "XXXIX"),
                Arguments.of(540, "DXL"),
                Arguments.of(0, "")
            );
        }
    }
}
