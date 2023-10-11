package edu.hw2;

import edu.hw1.Task1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Проверка работы CallingInfo()")
    void callingInfoTest(){
        assertThat(Task4.callingInfo().className()).isEqualTo("edu.hw2.Task4Test");
        assertThat(Task4.callingInfo().methodName()).isEqualTo("callingInfoTest");
    }
}
