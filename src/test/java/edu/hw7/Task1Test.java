package edu.hw7;

import edu.hw7.Task1.SomeStreamsAddition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Проверка работы SomeStreamsAddition")
    void someStreamsAdditionTest() {
        SomeStreamsAddition streamsAddition = new SomeStreamsAddition();
        streamsAddition.incrementCount(3);

        assertThat(streamsAddition.getGeneralCount() != null && streamsAddition.getGeneralCount() instanceof AtomicInteger).isTrue();
        assertThat(streamsAddition.getGeneralCount().get()).isEqualTo(300_000);
    }
}
