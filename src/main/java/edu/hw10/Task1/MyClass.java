package edu.hw10.Task1;

import edu.hw10.Task2.Cache;

public class MyClass implements MyInterface {

    @Max(value = 90)
    @Min(value = 80)
    public int a1;

    @NotNull
    public String str;

    public MyClass(int a1, String str) {
        this.a1 = a1;
        this.str = str;
    }

    public static MyClass fabric(int a1, String str) {
        return new MyClass(a1, str);
    }

    @Override
    @Cache(persist = true)
    public String getStr() {
        return str;
    }

    @Cache(persist = false)
    public void setStr(String str) {
        this.str = str;
    }
}
