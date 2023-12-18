package edu.project5;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class StudentReflection {

    private edu.project5.Student student;
    private MethodHandle handle;
    private Method method;


    public StudentReflection() {}

    @SuppressWarnings("MagicNumber")
    public void check() {
        try {
            Options options = new OptionsBuilder()
                .include(StudentReflection.class.getSimpleName())
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.NANOSECONDS)
                .forks(1)
                .warmupForks(1)
                .warmupIterations(1)
                .warmupTime(TimeValue.seconds(5))
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(5))
                .build();

            new Runner(options).run();
        } catch (RunnerException ex) {
            throw new RuntimeException();
        }
    }

    @Setup
    public void createMethod() {
        student = new Student("Alan");

        MethodType type = MethodType.methodType(String.class);
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        try {
            String methodName = "getName";
            handle = lookup.findVirtual(Student.class, methodName, type);
            method = Student.class.getMethod(methodName);
        } catch (IllegalAccessException | NoSuchMethodException error) {
            throw new RuntimeException();
        }
    }

//    @Benchmark
//    public void checkLambda(Blackhole bH) {
//        try {
//            MethodHandles.Lookup lookup = MethodHandles.publicLookup();
//            MethodType type = MethodType.methodType(String.class);
//            CallSite site = LambdaMetafactory.metafactory(lookup, "get",
//                MethodType.methodType(Supplier.class, Student.class), MethodType.methodType(Object.class)
//                , lookup.findVirtual(Student.class, "getName", type), type
//            );
//
//            Supplier<Student> lambda = (Supplier<Student>) site.getTarget().bindTo(student).invoke();
//            bH.consume(lambda.get());
//        } catch (Throwable ex) {
//            throw new RuntimeException();
//        }
//    }

    @Benchmark
    public void checkMethodHandler(Blackhole bH) {
        try {
            bH.consume(handle.invoke(student));
        } catch (Throwable ex) {
            throw new RuntimeException();
        }
    }

    @Benchmark
    public void checkMethod(Blackhole bH) {
        try {
            bH.consume(method.invoke(student));
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException();
        }
    }

    @Benchmark
    public void checkDirect(Blackhole bH) {
        String name = student.getName();
        bH.consume(name);
    }
}
