package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы isSubstring")
    void isSubstringTest(String string, String sub, boolean result) {
        assertThat(Task6.isSubstring(string, sub)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("achfdbaabgabcaabg", "abc", true),
                Arguments.of("kjdiosmsdaapdkosmfwsnw", "osm", true),
                Arguments.of("tysba;w.dfpwlmws", "tysba;w.dfpwlmws", true),
                Arguments.of("ijagnjiwGNJIRNFGNTWGGVG", "isfn", false),
                Arguments.of(null, null, false)
            );
        }
    }
}
