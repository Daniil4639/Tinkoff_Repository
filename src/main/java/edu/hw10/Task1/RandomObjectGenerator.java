package edu.hw10.Task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomObjectGenerator {

    private static final int MIN_BOUND = -50;
    private static final int MAX_BOUND = 50;
    private static final String ERROR_MESSAGE = "Something went wrong!";
    private static final List<Class<?>> PRIMITIVES = new ArrayList<>(List.of(int.class, Integer.class,
        long.class, Long.class, double.class, Double.class, float.class, Float.class, char.class,
        Character.class, String.class));

    public RandomObjectGenerator() {}

    @SuppressWarnings("RegexpSinglelineJava")
    public Object nextObject(Class<?> currentClass) {
        try {
            if (isPrimitive(currentClass)) {
                return randomValue(currentClass, false, MIN_BOUND, MAX_BOUND);
            }

            Constructor<?>[] constructors = currentClass.getDeclaredConstructors();

            Constructor<?> constructor = constructors[new Random().nextInt(0, constructors.length)];
            Parameter[] paramsTypes = constructor.getParameters();

            return constructor.newInstance(generateParameters(paramsTypes, currentClass).toArray());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return null;
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public Object nextObject(Class<?> currentClass, String methodName) {
        try {
            Method fabricMethod = currentClass.getMethod(methodName, new Class<?>[] {int.class, String.class});
            Parameter[] paramsTypes = fabricMethod.getParameters();

            return fabricMethod.invoke(null, generateParameters(paramsTypes, currentClass).toArray());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            return null;
        }
    }

    private List<Object> generateParameters(Parameter[] paramsTypes, Class<?> currentClass)
        throws NoSuchFieldException {

        List<Object> params = new ArrayList<>();

        for (var param: paramsTypes) {
            Annotation[] annotations = currentClass.getDeclaredField(param.getName()).getAnnotations();

            boolean nullMode = false;
            int startNum = MIN_BOUND;
            int endNum = MAX_BOUND;

            for (var annotation: annotations) {
                if (annotation.annotationType().equals(NotNull.class)) {
                    nullMode = true;
                } else if (annotation.annotationType().equals(Max.class)) {
                    Max maxAnnot = (Max) annotation;
                    endNum = (int) maxAnnot.value();
                } else if (annotation.annotationType().equals(Min.class)) {
                    Min minAnnot = (Min) annotation;
                    startNum = (int) minAnnot.value();
                }
            }

            params.add(randomValue((Class<?>) param.getParameterizedType(), nullMode, startNum, endNum));
        }

        return params;
    }

    @SuppressWarnings("CyclomaticComplexity")
    private Object randomValue(Class<?> valueType, boolean nullMode, int start, int end) {
        Object resultValue;
        Random random = new Random();

        switch (valueType.getName()) {
            case "int", "long" -> {
                resultValue = random.nextInt(start, end);
            }
            case "java.lang.Integer", "java.lang.Long" -> {
                if (!nullMode && random.nextBoolean()) {
                    resultValue = null;
                } else {
                    resultValue = random.nextInt(start, end);
                }
            }
            case "double" -> {
                resultValue = random.nextDouble(start, end);
            }
            case "java.lang.Double" -> {
                if (!nullMode && random.nextBoolean()) {
                    resultValue = null;
                } else {
                    resultValue = random.nextDouble(start, end);
                }
            }
            case "float" -> {
                resultValue = random.nextFloat(start, end);
            }
            case "java.lang.Float" -> {
                if (!nullMode && random.nextBoolean()) {
                    resultValue = null;
                } else {
                    resultValue = random.nextFloat(start, end);
                }
            }
            case "char" -> {
                resultValue = (char) random.nextInt(start, end);
            }
            case "java.lang.Character" -> {
                if (!nullMode && random.nextBoolean()) {
                    resultValue = null;
                } else {
                    resultValue = (char) random.nextInt(start, end);
                }
            }
            case "java.lang.String" -> {
                if (!nullMode && random.nextBoolean()) {
                    resultValue = null;
                } else {
                    int strLen = random.nextInt(end);
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < strLen; i++) {
                        result.append(randomValue(char.class, false, MIN_BOUND, MAX_BOUND));
                    }

                    resultValue = result.toString();
                }
            }
            case "boolean" -> {
                resultValue = random.nextBoolean();
            }
            case "java.lang.Boolean" -> {
                if (!nullMode && random.nextBoolean()) {
                    resultValue = null;
                } else {
                    resultValue = random.nextBoolean();
                }
            }
            default -> {
                resultValue = null;
            }
        }

        return resultValue;
    }

    private boolean isPrimitive(Class<?> type) {
        return PRIMITIVES.contains(type);
    }
}
