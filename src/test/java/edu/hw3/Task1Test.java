package edu.hw3;

import edu.hw3.Task1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы atBash()")
    void atBashTest(String receivedString, String answer){
        assertThat(Task1.atBash(receivedString)).isEqualTo(answer);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("Hello world!", "Svool dliow!"),
                Arguments.of("Any fool can write code that a computer can understand."
                    + " Good programmers write code that humans can understand. - Martin Fowler",
                    "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw."
                        + " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. - Nzigrm Uldovi"),
                Arguments.of("Хороший день, чтобы покодить!", "Хороший день, чтобы покодить!")
            );
        }
    }
}
