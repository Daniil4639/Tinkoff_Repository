package edu.hw10;

import edu.hw10.Task1.MyClass;
import edu.hw10.Task1.MyInterface;
import edu.hw10.Task2.CacheProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Проверка работы CacheProxy")
    void cacheProxyTest() {
        File getStrFile = new File("MyClass_getStr_cache.txt");
        File setStrFile = new File("MyClass_setStr_cache.txt");
        MyClass object = new MyClass(1, "test");

        MyInterface objectProxy = (MyInterface) CacheProxy.create(object, object.getClass());
        assertThat(objectProxy.getStr()).isEqualTo("test");
        assertThat(getStrFile.exists()).isTrue();

        try (BufferedReader reader = Files.newBufferedReader(getStrFile.toPath())) {
            String content = reader.readLine();

            assertThat(content).isEqualTo("test");
        } catch (IOException exception) {
            assertThat(true).isFalse();
        }

        getStrFile.delete();

        objectProxy.setStr("test1");

        assertThat(object.str).isEqualTo("test1");
        assertThat(setStrFile.exists()).isFalse();
    }
}
