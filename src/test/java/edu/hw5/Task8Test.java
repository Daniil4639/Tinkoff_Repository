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

public class Task8Test {

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider.class)
    @DisplayName("Проверка работы regularOddLength")
    void regularOddLengthTest(String string, boolean result) {
        assertThat(Task8.regularOddLength(string)).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider2.class)
    @DisplayName("Проверка работы regularZeroWithOddLenOrOneWithEvenLen")
    void regularZeroWithOddLenOrOneWithEvenLenTest(String string, boolean result) {
        assertThat(Task8.regularZeroWithOddLenOrOneWithEvenLen(string)).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider3.class)
    @DisplayName("Проверка работы regularZeroMultiplyThree")
    void regularZeroMultiplyThreeTest(String string, boolean result) {
        assertThat(Task8.regularZeroMultiplyThree(string)).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider4.class)
    @DisplayName("Проверка работы regularNo11No111")
    void regularNo11No111Test(String string, boolean result) {
        assertThat(Task8.regularNo11No111(string)).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider5.class)
    @DisplayName("Проверка работы regularEveryOddSymbolIsOne")
    void regularEveryOddSymbolIsOneTest(String string, boolean result) {
        assertThat(Task8.regularEveryOddSymbolIsOne(string)).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider6.class)
    @DisplayName("Проверка работы regularWithTwoOrMoreZeroAndWithOneOrLessOne")
    void regularWithTwoOrMoreZeroAndWithOneOrLessOne(String string, boolean result) {
        assertThat(Task8.regularWithTwoOrMoreZeroAndWithOneOrLessOne(string)).isEqualTo(result);
    }

    @ParameterizedTest(name = "#{index} - Run with args = {0}")
    @ArgumentsSource(TestArgumentsProvider7.class)
    @DisplayName("Проверка работы regularWithoutOrderedOne")
    void regularWithoutOrderedOne(String string, boolean result) {
        assertThat(Task8.regularWithoutOrderedOne(string)).isEqualTo(result);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1010", false),
                Arguments.of("123", false),
                Arguments.of("11001", true),
                Arguments.of(null, false)
            );
        }
    }

    static class TestArgumentsProvider2 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1010", true),
                Arguments.of("123", false),
                Arguments.of("11001", false),
                Arguments.of("0110", false),
                Arguments.of(null, false)
            );
        }
    }

    static class TestArgumentsProvider3 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("011", false),
                Arguments.of("000", true),
                Arguments.of("123", false),
                Arguments.of("11001", false),
                Arguments.of("01100010110", true),
                Arguments.of(null, false)
            );
        }
    }

    static class TestArgumentsProvider4 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("011", true),
                Arguments.of("000", true),
                Arguments.of("123", false),
                Arguments.of("111", false),
                Arguments.of("11", false),
                Arguments.of("01100010110", true),
                Arguments.of(null, false)
            );
        }
    }

    static class TestArgumentsProvider5 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("1", true),
                Arguments.of("", false),
                Arguments.of("123", false),
                Arguments.of("101", true),
                Arguments.of("11111111", true),
                Arguments.of("01100010110", false),
                Arguments.of(null, false)
            );
        }
    }

    static class TestArgumentsProvider6 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("0100", true),
                Arguments.of("01", false),
                Arguments.of("123", false),
                Arguments.of("101", false),
                Arguments.of("000001", true),
                Arguments.of("1000000", true),
                Arguments.of(null, false)
            );
        }
    }

    static class TestArgumentsProvider7 implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of("0100", true),
                Arguments.of("01100", false),
                Arguments.of("123", false),
                Arguments.of("101", true),
                Arguments.of("01000101", true),
                Arguments.of("100011010001", false),
                Arguments.of(null, false)
            );
        }
    }
}
