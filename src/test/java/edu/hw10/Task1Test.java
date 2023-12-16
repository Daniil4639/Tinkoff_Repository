package edu.hw10;

import edu.hw10.Task1.MyClass;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Проверка работы RandomObjectGenerator")
    void randomObjectGenerator() throws NoSuchFieldException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

        for (int i = 0;i < 100; i++) {

            MyClass obj1 = (MyClass) randomObjectGenerator.nextObject(MyClass.class);

            MyClass obj2 = (MyClass) randomObjectGenerator.nextObject(MyClass.class, "fabric");

            assertThat(obj1 == null).isFalse();
            assertThat(obj2 == null).isFalse();

            assert obj1 != null;
            assertThat(obj1.a1 <= 90 && obj1.a1 >= 80).isTrue();
            assertThat(obj2.a1 <= 90 && obj2.a1 >= 80).isTrue();

            assertThat(obj1.str == null).isFalse();
            assertThat(obj2.str == null).isFalse();
        }
    }
}
