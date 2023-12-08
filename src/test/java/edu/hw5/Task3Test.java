package edu.hw5;

import edu.hw5.Task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы parseDate")
    void parseDateTest(String date, LocalDate result) {
        Optional<LocalDate> optionalLocalDate = Task3.parseDate(date);
        if (date.equals("20-12-2")) {
            assertThat(optionalLocalDate).isEqualTo(Optional.empty());
        } else {
            assert optionalLocalDate.isPresent();

            assertThat(optionalLocalDate.get()).isEqualTo(result);
        }
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
                Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
                Arguments.of("1/3/1976", LocalDate.of(1976, 3, 1)),
                Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
                Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
                Arguments.of("today", LocalDate.now()),
                Arguments.of("yesterday", LocalDate.now().minusDays(1)),
                Arguments.of("1 day ago", LocalDate.now().minusDays(1)),
                Arguments.of("2234 days ago", LocalDate.now().minusDays(2234)),
                Arguments.of("20-12-2", null)
                );
        }
    }
}
