package edu.hw7;

import edu.hw7.Task4.MonteCarloPiSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.lang.Math.abs;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    private final static Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Проверка работы countPi")
    void countPiTest() {
        /*long firstCalculation = System.nanoTime();
        double firstPi = MonteCarloPiSearch.countPi((long) 10e6);
        long firstCalculationThread = System.nanoTime();
        double firstPiThread = MonteCarloPiSearch.countPiThread((long) 10e6);
        long firstCalculationEnd = System.nanoTime();

        assertThat(firstCalculationEnd - firstCalculationThread < firstCalculationThread - firstCalculation).isTrue();
        LOGGER.info("Кол - во потоков: 16");
        LOGGER.info("");
        LOGGER.info("Время поиска при 10млн итерациях:");
        LOGGER.info("Однопоточный: " + (firstCalculationThread - firstCalculation));
        LOGGER.info("Значение: " + firstPi);
        LOGGER.info("Примерная погрешность: " + abs(Math.PI - firstPi));
        LOGGER.info("Многопоточный: " + (firstCalculationEnd - firstCalculationThread));
        LOGGER.info("Значение " + firstPiThread);
        LOGGER.info("Примерная погрешность: " + abs(Math.PI - firstPiThread));
        LOGGER.info("Ускорение в " + (firstCalculationThread - firstCalculation) * 1.0 / (firstCalculationEnd - firstCalculationThread) + " раз");;

        long secondCalculation = System.nanoTime();
        double secondPi = MonteCarloPiSearch.countPi((long) 100e6);
        long secondCalculationThread = System.nanoTime();
        double secondPiThread = MonteCarloPiSearch.countPiThread((long) 100e6);
        long secondCalculationEnd = System.nanoTime();

        assertThat(secondCalculationEnd - secondCalculationThread < secondCalculationThread - secondCalculation).isTrue();
        LOGGER.info("");
        LOGGER.info("Время поиска при 100млн итерациях:");
        LOGGER.info("Однопоточный: " + (secondCalculationThread - secondCalculation));
        LOGGER.info("Значение: " + secondPi);
        LOGGER.info("Примерная погрешность: " + abs(Math.PI - secondPi));
        LOGGER.info("Многопоточный: " + (secondCalculationEnd - secondCalculationThread));
        LOGGER.info("Значение " + secondPiThread);
        LOGGER.info("Примерная погрешность: " + abs(Math.PI - secondPiThread));
        LOGGER.info("Ускорение в " + (secondCalculationThread - secondCalculation) * 1.0 / (secondCalculationEnd - secondCalculationThread) + " раз");;

        long thirdCalculation = System.nanoTime();
        double thirdPi = MonteCarloPiSearch.countPi((long) 1000e6);
        long thirdCalculationThread = System.nanoTime();
        double thirdPiThread = MonteCarloPiSearch.countPiThread((long) 1000e6);
        long thirdCalculationEnd = System.nanoTime();

        assertThat(thirdCalculationEnd - thirdCalculationThread < thirdCalculationThread - thirdCalculation).isTrue();
        LOGGER.info("");
        LOGGER.info("Время поиска при 1млрд итерациях:");
        LOGGER.info("Однопоточный: " + (thirdCalculationThread - thirdCalculation));
        LOGGER.info("Значение: " + thirdPi);
        LOGGER.info("Примерная погрешность: " + abs(Math.PI - thirdPi));
        LOGGER.info("Многопоточный: " + (thirdCalculationEnd - thirdCalculationThread));
        LOGGER.info("Значение " + thirdPiThread);
        LOGGER.info("Примерная погрешность: " + abs(Math.PI - thirdPiThread));
        LOGGER.info("Ускорение в " + (thirdCalculationThread - thirdCalculation) * 1.0 / (thirdCalculationEnd - thirdCalculationThread) + " раз");;
*/
    }
}
