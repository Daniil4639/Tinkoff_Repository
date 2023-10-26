package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Task {

    private Task() {}

    // func 1
    public static void heightAnimalSort(List<UsingClasses.Animal> receivedAnimals) {
        receivedAnimals.sort(Comparator.comparingInt(UsingClasses.Animal::height));
    }

    //func 2
    public static List<UsingClasses.Animal> weightAnimalSort(
        List<UsingClasses.Animal> receivedAnimals, int neededAnimalsCount) {
        List<UsingClasses.Animal> newAnimalList = new ArrayList<>(List.copyOf(receivedAnimals));

        newAnimalList.sort(Comparator.comparingInt(UsingClasses.Animal::weight));

        return newAnimalList.reversed().stream().limit(neededAnimalsCount).collect(Collectors.toList());
    }

    //func 3
    public static Map<UsingClasses.Animal.Type, Integer> animalCounterPerType(
        List<UsingClasses.Animal> receivedAnimals) {
        Map<UsingClasses.Animal.Type, Integer> typeCounter = new HashMap<>();

        for (UsingClasses.Animal element: receivedAnimals) {
            typeCounter.merge(element.type(), 1, Integer::sum);
        }

        return typeCounter;
    }

    //func 4
    public static UsingClasses.Animal longestName(List<UsingClasses.Animal> receivedAnimals) {
        List<UsingClasses.Animal> newAnimalList = new ArrayList<>(List.copyOf(receivedAnimals));

        newAnimalList.sort(new Comparator<>() {
            @Override
            public int compare(UsingClasses.Animal o1, UsingClasses.Animal o2) {
                return Integer.compare(o2.name().length(), o1.name().length());
            }
        });

        return newAnimalList.get(0);
    }

    //func 5
    public static UsingClasses.Animal.Sex sexCounter(List<UsingClasses.Animal> receivedAnimals) {
        int cntM = 0;
        int cntF = 0;

        for (UsingClasses.Animal element: receivedAnimals) {
            if (element.sex().equals(UsingClasses.Animal.Sex.M)) {
                cntM++;
            } else {
                cntF++;
            }
        }

        if (cntM >= cntF) {
            return UsingClasses.Animal.Sex.M;
        } else {
            return UsingClasses.Animal.Sex.F;
        }
    }

    //func 6
    public static Map<UsingClasses.Animal.Type, UsingClasses.Animal> biggestWeightPerType(
        List<UsingClasses.Animal> receivedAnimals) {

        Map<UsingClasses.Animal.Type, UsingClasses.Animal> weights = new HashMap<>();

        for (UsingClasses.Animal element: receivedAnimals) {
            if (!weights.containsKey(element.type()) || weights.get(element.type()).weight() < element.weight()) {
                weights.put(element.type(), element);
            }
        }

        return weights;
    }

    //func 7
    public static UsingClasses.Animal oldestAnimalByNumberK(List<UsingClasses.Animal> receivedAnimals,
        int neededAnimalsCount) {

        List<UsingClasses.Animal> newAnimalList = new ArrayList<>(List.copyOf(receivedAnimals));

        newAnimalList.sort(Comparator.comparingInt(UsingClasses.Animal::age));
        UsingClasses.Animal resultAnimal;

        if (neededAnimalsCount >= newAnimalList.size()) {
            resultAnimal = newAnimalList.get(newAnimalList.size() - 1);
        } else {
            resultAnimal = newAnimalList.get(newAnimalList.size() - 1 - neededAnimalsCount);
        }

        return resultAnimal;
    }

    //func 8
    public static Optional<UsingClasses.Animal> biggestWeightWithOptionalHeight(List<UsingClasses.Animal>
        receivedAnimals, int optionalHeight) {

        List<UsingClasses.Animal> newAnimalList =
            new ArrayList<>(receivedAnimals.stream().filter(it -> it.height() < optionalHeight)
                .toList());

        newAnimalList.sort(Comparator.comparingInt(UsingClasses.Animal::weight));

        if (newAnimalList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(newAnimalList.get(newAnimalList.size() - 1));
    }

    //func 9
    public static Integer sumOfPaws(List<UsingClasses.Animal> receivedAnimals) {
        return Arrays.stream(receivedAnimals.toArray(new UsingClasses.Animal[0]))
            .mapToInt(UsingClasses.Animal::paws).sum();
    }

    //func 10
    public static List<UsingClasses.Animal> animalsWithDiffPawsAndAge(List<UsingClasses.Animal> receivedAnimals) {
        return receivedAnimals.stream().filter(it -> it.paws() != it.age()).collect(Collectors.toList());
    }

    //func 11
    public static List<UsingClasses.Animal> animalWhichCanBite(List<UsingClasses.Animal> receivedAnimals) {
        final int MINIMUM_HEIGHT = 100;
        return receivedAnimals.stream().filter(it -> it.bites() && it.height() > MINIMUM_HEIGHT)
            .collect(Collectors.toList());
    }

    //func 12
    public static Integer weightBiggerThanHeight(List<UsingClasses.Animal> receivedAnimals) {
        return receivedAnimals.stream().filter(it -> it.weight() > it.height()).toList().size();
    }

    //func 13
    public static List<UsingClasses.Animal> animalsWithDoubleName(List<UsingClasses.Animal> receivedAnimals) {
        return receivedAnimals.stream().filter(it -> it.name().contains(" ")).collect(Collectors.toList());
    }

    //func 14
    public static Boolean isDogWithSuchHeightInList(List<UsingClasses.Animal> receivedAnimals, int receivedHeight) {
        return !receivedAnimals.stream().filter(it -> it.type().equals(UsingClasses.Animal.Type.DOG)
                && it.height() > receivedHeight).toList().isEmpty();
    }

    //func 15
    public static Integer sumWeightWithBorders(List<UsingClasses.Animal> receivedAnimals, int k, int l) {
        return Arrays.stream(receivedAnimals.toArray(new UsingClasses.Animal[0]))
            .filter(it -> it.age() >= k && it.age() <= l).mapToInt(UsingClasses.Animal::weight).sum();
    }

    //func 16
    public static List<UsingClasses.Animal> paramsSortAnimals(List<UsingClasses.Animal> receivedAnimals) {
        List<UsingClasses.Animal> newAnimalList = new ArrayList<>(List.copyOf(receivedAnimals));
        newAnimalList.sort(new Comparator<UsingClasses.Animal>() {
            @Override
            public int compare(UsingClasses.Animal o1, UsingClasses.Animal o2) {
                if (!o1.type().equals(o2.type())) {
                    return o1.type().toString().compareTo(o2.type().toString());
                } else {
                    if (!o1.sex().equals(o2.sex())) {
                        return o1.sex().toString().compareTo(o2.sex().toString());
                    } else {
                        return o1.name().compareTo(o2.name());
                    }
                }
            }
        });

        return newAnimalList;
    }

    //func 17
    public static Boolean spidersOfDogs(List<UsingClasses.Animal> receivedAnimals) {
        double spidersCount = 0;
        double dogCount = 0;
        double spiderBite = 0;
        double dogBite = 0;

        for (UsingClasses.Animal element: receivedAnimals) {
            if (element.type().equals(UsingClasses.Animal.Type.SPIDER)) {
                spidersCount++;
                if (element.bites()) {
                    spiderBite++;
                }
            } else if (element.type().equals(UsingClasses.Animal.Type.DOG)) {
                dogCount++;
                if (element.bites()) {
                    dogBite++;
                }
            }
        }

        if (spidersCount == 0 || dogCount == 0) {
            return false;
        } else {
            return spiderBite / spidersCount > dogBite / dogCount;
        }
    }

    //func 18
    @SafeVarargs public static UsingClasses.Animal theBiggestFish(List<UsingClasses.Animal>...someAnimalLists) {
        UsingClasses.Animal resultFish = someAnimalLists[0].get(0);
        for (List<UsingClasses.Animal> list: someAnimalLists) {
            for (UsingClasses.Animal element: list) {
                if (element.type().equals(UsingClasses.Animal.Type.FISH) && element.weight() > resultFish.weight()) {
                    resultFish = element;
                }
            }
        }

        return resultFish;
    }

    //func 19
    public static Map<String, Set<UsingClasses.ValidationError>> findAnimalErrors(
        List<UsingClasses.Animal> receivedAnimals) throws NoSuchFieldException, IllegalAccessException {

        ArrayList<String> fieldArray = new ArrayList<>(List.of("name", "type", "sex", "age", "height", "weight"));
        Map<String, Set<UsingClasses.ValidationError>> mapOfErrors = new HashMap<>();

        for (UsingClasses.Animal animal: receivedAnimals) {
            Set<UsingClasses.ValidationError> setOfErrors = new HashSet<>();
            for (String field: fieldArray) {
                try {
                    setOfErrors.add(new UsingClasses.ValidationError(animal, field));
                } catch (IllegalAccessException ignored) {
                }
            }

            if (!setOfErrors.isEmpty()) {
                mapOfErrors.put(animal.name(), setOfErrors);
            }
        }

        return mapOfErrors;
    }

    //func 20
    public static Map<String, String> findAnimalErrorsLikeString(
        List<UsingClasses.Animal> receivedAnimals) throws NoSuchFieldException, IllegalAccessException {

        Map<String, Set<UsingClasses.ValidationError>> mapOfErrors = findAnimalErrors(receivedAnimals);
        Map<String, String> resultStringMap = new HashMap<>();

        for (var mapElement: mapOfErrors.entrySet()) {
            StringBuilder resultErrorsString = new StringBuilder();
            for (UsingClasses.ValidationError errorStrings: mapElement.getValue()) {
                resultErrorsString.append(errorStrings.getMassage());
            }

            resultStringMap.put(mapElement.getKey(), resultErrorsString.toString());
        }

        return resultStringMap;
    }
}
