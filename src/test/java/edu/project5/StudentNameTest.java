package edu.project5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentNameTest {

    @Test
    @DisplayName("Проверка StudentReflection")
    void studentReflectionTest() throws Throwable {
        StudentReflection reflection = new StudentReflection();
        reflection.check();
    }
}
