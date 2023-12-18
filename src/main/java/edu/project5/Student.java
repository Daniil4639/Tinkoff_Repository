package edu.project5;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class Student extends Person{

    public Student(String name) {
        super(name);
    }
}
