package edu.hw10.Task2;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CacheInvocationHandler implements InvocationHandler {

    private static final String ERROR_MESSAGE = "Something went wrong!";
    private final Object object;

    public CacheInvocationHandler(Object obj) {
        object = obj;
    }

    @Override
    @SuppressWarnings("RegexpSinglelineJava")
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] annotations = object.getClass().getDeclaredMethod(method.getName(),
            method.getParameterTypes()).getDeclaredAnnotations();
        Method calledMethod = object.getClass().getDeclaredMethod(method.getName(),
            method.getParameterTypes());

        Object result = calledMethod.invoke(object, args);

        boolean saveFileOk = false;

        for (var annot: annotations) {
            if (annot.annotationType().equals(Cache.class)) {
                Cache cache = (Cache) annot;
                if (cache.persist()) {
                    saveFileOk = true;
                    break;
                }
            }
        }

        if (saveFileOk) {
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(
                Paths.get(object.getClass().getSimpleName() + "_" + method.getName() + "_cache.txt"),
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE
            ))) {

                out.write((result.toString() + System.lineSeparator()).getBytes());
            } catch (IOException exception) {
                System.out.println(ERROR_MESSAGE);
            }
        }

        return result;
    }
}
