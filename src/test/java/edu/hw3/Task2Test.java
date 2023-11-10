package edu.hw3;

import edu.hw3.Task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы clusterize()")
    void clusterizeTest(String receivedString, String answer){
        assertThat(Task2.clusterize(receivedString).toString()).isEqualTo(answer);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("()()()", "[(), (), ()]"),
                Arguments.of("((()))", "[((()))]"),
                Arguments.of("((()))(())()()(()())", "[((())), (()), (), (), (()())]"),
                Arguments.of("((())())(()(()()))", "[((())()), (()(()()))]")
            );
        }
    }
}
