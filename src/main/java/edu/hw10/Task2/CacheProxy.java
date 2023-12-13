package edu.hw10.Task2;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy() {}

    public static Object create(Object obj, Class<?> objClass) {
        return Proxy.newProxyInstance(objClass.getClassLoader(), objClass.getInterfaces(),
            new CacheInvocationHandler(obj));
    }
}
