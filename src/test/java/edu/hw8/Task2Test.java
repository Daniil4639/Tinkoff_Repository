package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Проверка работы FixedThreadPool")
    void fixedThreadPoolTest() throws Exception {
        FixedThreadPool pool = new FixedThreadPool();
        pool.create(8);

        List<Integer> fibNumbers = new ArrayList<>(Collections.nCopies(12, -1));

        List<Thread> listThreads = IntStream.range(0, 12).mapToObj(elem -> new Thread(() -> {
            if (elem == 0 || elem == 1) {
                fibNumbers.set(elem, 1);
            } else {
                while (fibNumbers.get(elem - 1) == -1 || fibNumbers.get(elem - 2) == -1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                fibNumbers.set(elem, fibNumbers.get(elem - 1) + fibNumbers.get(elem - 2));
            }
        })).toList();

        for (Thread thread: listThreads) {
            pool.execute(thread);
        }

        pool.start();

        while (fibNumbers.get(11) == -1) {
            Thread.sleep(10);
        }

        pool.close();

        assertThat(fibNumbers.toArray()).containsExactly(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
    }
}
