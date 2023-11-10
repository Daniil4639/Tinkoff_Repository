package edu.hw4;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsingClasses {

    private final static int CAT_AND_DOG_PAWS_COUNT = 4;
    private final static int BIRD_PAWS_COUNT = 2;
    private final static int FISH_PAWS_COUNT = 0;
    private final static int SPIDER_PAWS_COUNT = 8;
    private final static String FIELD_IS_NULL = ": is null";
    private final static String FIELD_IS_ZERO = ": is zero";
    private final static String FIELD_IS_NEGATIVE = ": is negative";
    private final static String FIELD_NAME = "name";
    private final static String FIELD_TYPE = "type";
    private final static String FIELD_SEX = "sex";
    private final static String FIELD_AGE = "age";
    private final static String FIELD_HEIGHT = "height";
    private final static String FIELD_WEIGHT = "weight";
    private static final List<Field> FIELD_ARRAY;

    static {
        try {
            FIELD_ARRAY = new ArrayList<>(List.of(
                Animal.class.getDeclaredField(FIELD_NAME),
                Animal.class.getDeclaredField(FIELD_TYPE),
                Animal.class.getDeclaredField(FIELD_SEX),
                Animal.class.getDeclaredField(FIELD_AGE),
                Animal.class.getDeclaredField(FIELD_HEIGHT),
                Animal.class.getDeclaredField(FIELD_WEIGHT)));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private UsingClasses() {}

    public static Set<ValidationError> getSetOfErrors(Animal object) {
        Set<UsingClasses.ValidationError> setOfErrors = new HashSet<>();

        for (Field field: FIELD_ARRAY) {
            try {
                setOfErrors.add(new ValidationError(object, field));
            } catch (IllegalAccessException ignored) {
            }
        }

        return setOfErrors;
    }

    public record Animal(
        String name,
        Type type,
        Sex sex,
        int age,
        int height,
        int weight,
        boolean bites
    ) {

        enum Type {
            CAT, DOG, BIRD, FISH, SPIDER
        }

        enum Sex {
            M, F
        }

        public int paws() {
            return switch (type) {
                case CAT, DOG -> CAT_AND_DOG_PAWS_COUNT;
                case BIRD -> BIRD_PAWS_COUNT;
                case FISH -> FISH_PAWS_COUNT;
                case SPIDER -> SPIDER_PAWS_COUNT;
            };
        }
    }

    public static class ValidationError {
        private final StringBuilder massage = new StringBuilder();

        public ValidationError(Animal object, Field field) throws IllegalAccessException {

            checkField(object, field);
        }

        private void checkField(Animal object, Field field) throws IllegalAccessException {
            if (field.get(object) == null) {

                massage.append(field.getName()).append(FIELD_IS_NULL)
                    .append(System.lineSeparator());

            } else if (field.getName().equals(FIELD_NAME) && field.get(object).equals(" ")) {

                massage.append(field.getName()).append(": is empty")
                    .append(System.lineSeparator());

            } else if (field.getName().equals(FIELD_AGE) || field.getName().equals(FIELD_HEIGHT)
                || field.getName().equals(FIELD_WEIGHT)) {

                if ((Integer) field.get(object) == 0) {
                    massage.append(field.getName()).append(FIELD_IS_ZERO)
                        .append(System.lineSeparator());
                } else if ((Integer) field.get(object) < 0) {
                    massage.append(field.getName()).append(FIELD_IS_NEGATIVE)
                        .append(System.lineSeparator());
                } else {
                    throw new IllegalAccessException();
                }
            } else {
                throw new IllegalAccessException();
            }
        }

        public String getMassage() {
            return massage.toString();
        }
    }
}
