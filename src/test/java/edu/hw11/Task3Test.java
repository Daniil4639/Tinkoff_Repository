package edu.hw11;

import edu.hw11.Task3.FibAppender;
import edu.hw11.Task3.TestClass;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Создание класса с помощью ByteCodeAppender")
    void byteCodeAppenderTest()
        throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException,
        InvocationTargetException {

        FibAppender fibAppender = new FibAppender();

        Object obj =
            new ByteBuddy()
                .subclass(Object.class)
                .name("FibClass")
                .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
                .withParameter(int.class, "n")
                .intercept(new Implementation.Simple(fibAppender))
                .modifiers(Opcodes.ACC_PUBLIC)
                .make()
                .load(Task3Test.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded()
                .newInstance();

        assertThat(obj.getClass().getMethod("fib", int.class).invoke(obj, 7)).isEqualTo(13L);
    }
}
