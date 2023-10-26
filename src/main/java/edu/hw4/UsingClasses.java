package edu.hw4;

import java.lang.reflect.Field;
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class UsingClasses {

    private final static ArrayList<Integer> PAWS_COUNT = new ArrayList<>(asList(4, 2, 0, 8));
    private final static String FIELD_IS_NULL = ": is null\n";
    private final static String FIELD_IS_ZERO = ": is zero\n";
    private final static String FIELD_IS_NEGATIVE = ": is negative\n";

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
                case CAT, DOG -> PAWS_COUNT.get(0);
                case BIRD -> PAWS_COUNT.get(1);
                case FISH -> PAWS_COUNT.get(2);
                case SPIDER -> PAWS_COUNT.get(PAWS_COUNT.size()) - 1;
            };
        }
    }

    public static class ValidationError {
        private String massage = null;

        private void checkName(Animal object, Field field) throws IllegalAccessException {
            if (field.get(object) == null) {
                massage = field.getName() + FIELD_IS_NULL;
            } else if (field.get(object).equals(" ")) {
                massage = field.getName() + ": is empty\n";
            } else {
                throw new IllegalAccessException();
            }
        }

        private void checkTypeAndSex(Animal object, Field field) throws IllegalAccessException {
            if (field.get(object) == null) {
                massage = field.getName() + FIELD_IS_NULL;
            } else {
                throw new IllegalAccessException();
            }
        }

        private void checkAgeHeightWeight(Animal object, Field field) throws IllegalAccessException {
            if ((Integer) field.get(object) == 0) {
                massage = field.getName() + FIELD_IS_ZERO;
            } else if ((Integer) field.get(object) < 0) {
                massage = field.getName() + FIELD_IS_NEGATIVE;
            } else {
                throw new IllegalAccessException();
            }
        }

        private void checkBites(Animal object, Field field) throws IllegalAccessException {
            if ((Integer) field.get(object) == 0) {
                massage = field.getName() + FIELD_IS_ZERO;
            } else if ((Integer) field.get(object) < 0) {
                massage = field.getName() + FIELD_IS_NEGATIVE;
            } else {
                throw new IllegalAccessException();
            }
        }

        public ValidationError(Animal object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            switch (fieldName) {
                case "name":
                    checkName(object, field);
                    break;
                case "type":
                case "sex":
                    checkTypeAndSex(object, field);
                    break;
                case "age":
                case "height":
                case "weight":
                    checkAgeHeightWeight(object, field);
                    break;
                default:
            }
        }

        public String getMassage() {
            return massage;
        }
    }
}
