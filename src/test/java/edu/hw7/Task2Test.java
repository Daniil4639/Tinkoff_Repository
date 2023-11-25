package edu.hw7;

import edu.hw7.Task2.FactorialCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Проверка работы FactorialCalculator")
    void factorialCalculatorTest() {
        assertThat(FactorialCalculator.calculate(3)).isEqualTo(new BigInteger("6"));
        assertThat(FactorialCalculator.calculate(200)).isEqualTo(new BigInteger("78865786736479050355236321"
            + "3932185062295135977687173263294742533244359449963403342920304284011984623904177212138919638830257642790"
            + "2426371050619266249528299311134628572707633172373969889439224456214516642402540332918641312274282948532"
            + "7752424240757390324032125740557956866022603190417032406235170085879617892222278962370389737472000000000"
            + "0000000000000000000000000000000000000000"));
    }
}
