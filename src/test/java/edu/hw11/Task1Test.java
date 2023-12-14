package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Создание класса с помощью ByteBuddy")
    void classCreateTest() throws InstantiationException, IllegalAccessException {
        Object newClass = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString").and(ElementMatchers.returns(String.class)))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded()
            .newInstance();

        assertThat(newClass.toString()).isEqualTo("Hello, ByteBuddy!");
    }
}
