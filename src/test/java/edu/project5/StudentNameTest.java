package edu.project5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaConversionException;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Supplier;

public class StudentNameTest {

    @Test
    @DisplayName("Проверка StudentReflection")
    void studentReflectionTest() throws Throwable {
        StudentReflection reflection = new StudentReflection();
        reflection.check();
    }
}
