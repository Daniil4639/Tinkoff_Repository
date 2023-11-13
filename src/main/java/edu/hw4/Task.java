package edu.hw4;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task {

    private final static int MINIMUM_HEIGHT = 100;

    private Task() {}

    // Task1: Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public static List<UsingClasses.Animal> heightAnimalSort(List<UsingClasses.Animal> receivedAnimals) {
        return receivedAnimals.stream()
            .sorted(Comparator.comparingInt(UsingClasses.Animal::height))
            .collect(Collectors.toList());
    }

    // Task2: Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public static List<UsingClasses.Animal> weightAnimalSort(
        List<UsingClasses.Animal> receivedAnimals, int neededAnimalsCount) {

        return receivedAnimals.stream()
            .sorted(Collections.reverseOrder(Comparator.comparingInt(UsingClasses.Animal::weight)))
            .limit(neededAnimalsCount)
            .collect(Collectors.toList())
            .reversed();
    }

    // Task3: Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public static Map<UsingClasses.Animal.Type, Integer> animalCounterPerType(
        List<UsingClasses.Animal> receivedAnimals) {

        return receivedAnimals.stream()
            .collect(Collectors.groupingBy(UsingClasses.Animal::type, Collectors.summingInt(elem -> 1)));
    }

    // Task4: У какого животного самое длинное имя -> Animal
    public static UsingClasses.Animal longestName(List<UsingClasses.Animal> receivedAnimals) {

        Comparator<UsingClasses.Animal> comparator = new Comparator<>() {
            @Override
            public int compare(UsingClasses.Animal o1, UsingClasses.Animal o2) {
                return Integer.compare(o2.name().length(), o1.name().length());
            }
        };

        return receivedAnimals.stream().min(comparator).get();
    }

    // Task5: Каких животных больше: самцов или самок -> Sex
    public static UsingClasses.Animal.Sex sexCounter(List<UsingClasses.Animal> receivedAnimals) {

        long maleCount = receivedAnimals.stream()
            .filter(elem -> elem.sex().equals(UsingClasses.Animal.Sex.M))
            .count();
        long femaleCount = receivedAnimals.stream()
            .filter(elem -> elem.sex().equals(UsingClasses.Animal.Sex.F))
            .count();

        if (maleCount >= femaleCount) {
            return UsingClasses.Animal.Sex.M;
        } else {
            return UsingClasses.Animal.Sex.F;
        }
    }

    // Task6: Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public static Map<UsingClasses.Animal.Type, UsingClasses.Animal> biggestWeightPerType(
        List<UsingClasses.Animal> receivedAnimals) {

        return receivedAnimals.stream()
            .collect(Collectors.toMap(UsingClasses.Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(UsingClasses.Animal::weight))));
    }

    // Task7: K-е самое старое животное -> Animal
    public static UsingClasses.Animal oldestAnimalByNumberK(List<UsingClasses.Animal> receivedAnimals,
        int neededAnimalsCount) {

        return receivedAnimals.stream()
            .sorted(Collections.reverseOrder(Comparator.comparing(UsingClasses.Animal::age)))
            .limit(neededAnimalsCount)
            .toList()
            .getLast();
    }

    // Task8: Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public static Optional<UsingClasses.Animal> biggestWeightWithOptionalHeight(List<UsingClasses.Animal>
        receivedAnimals, int optionalHeight) {

        return Optional.of(receivedAnimals.stream()
            .filter(elem -> elem.height() < optionalHeight)
            .max(Comparator.comparing(UsingClasses.Animal::weight))
            .get());
    }

    // Task9: Сколько в сумме лап у животных в списке -> Integer
    public static Integer sumOfPaws(List<UsingClasses.Animal> receivedAnimals) {
        return receivedAnimals.stream()
            .mapToInt(UsingClasses.Animal::paws)
            .sum();
    }

    // Task10: Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public static List<UsingClasses.Animal> animalsWithDiffPawsAndAge(List<UsingClasses.Animal> receivedAnimals) {
        return receivedAnimals.stream()
            .filter(it -> it.paws() != it.age())
            .collect(Collectors.toList());
    }

    // Task11: Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см -> List<Animal>
    public static List<UsingClasses.Animal> animalWhichCanBite(List<UsingClasses.Animal> receivedAnimals) {
        return receivedAnimals.stream()
            .filter(it -> it.bites() && it.height() > MINIMUM_HEIGHT)
            .collect(Collectors.toList());
    }

    // Task12: Сколько в списке животных, вес которых превышает рост -> Integer
    public static Integer weightBiggerThanHeight(List<UsingClasses.Animal> receivedAnimals) {
        return Math.toIntExact(receivedAnimals.stream()
            .filter(it -> it.weight() > it.height())
            .count());
    }

    // Task13: Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public static List<UsingClasses.Animal> animalsWithDoubleName(List<UsingClasses.Animal> receivedAnimals) {
        return receivedAnimals.stream()
            .filter(it -> it.name().contains(" "))
            .collect(Collectors.toList());
    }

    // Task14: Есть ли в списке собака ростом более k см -> Boolean
    public static Boolean isDogWithSuchHeightInList(List<UsingClasses.Animal> receivedAnimals, int receivedHeight) {
        return !receivedAnimals.stream()
            .filter(it -> it.type().equals(UsingClasses.Animal.Type.DOG)
                && it.height() > receivedHeight)
            .toList()
            .isEmpty();
    }

    // Task15: Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    public static Integer sumWeightWithBorders(List<UsingClasses.Animal> receivedAnimals, int k, int l) {
        return receivedAnimals.stream()
            .filter(it -> it.age() >= k && it.age() <= l)
            .mapToInt(UsingClasses.Animal::weight)
            .sum();
    }

    // Task16: Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public static List<UsingClasses.Animal> paramsSortAnimals(List<UsingClasses.Animal> receivedAnimals) {
        Comparator<UsingClasses.Animal> comparator = Comparator.comparing(UsingClasses.Animal::type)
            .thenComparing(UsingClasses.Animal::sex).thenComparing(UsingClasses.Animal::name);

        return receivedAnimals.stream()
            .sorted(comparator)
            .toList();
    }

    //Task17: Правда ли, что пауки кусаются чаще, чем собаки -> Boolean
    public static Boolean spidersOfDogs(List<UsingClasses.Animal> receivedAnimals) {
        List<UsingClasses.Animal> spiderList = receivedAnimals.stream()
            .filter(elem -> elem.type().equals(UsingClasses.Animal.Type.SPIDER))
            .toList();

        List<UsingClasses.Animal> dogsList = receivedAnimals.stream()
            .filter(elem -> elem.type().equals(UsingClasses.Animal.Type.DOG))
            .toList();

        if (spiderList.isEmpty() || dogsList.isEmpty()) {
            return false;
        } else {
            return spiderList.stream().filter(UsingClasses.Animal::bites).toList().size() / spiderList.size()
                > dogsList.stream().filter(UsingClasses.Animal::bites).toList().size() / dogsList.size();
        }
    }

    // Task18: Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    @SafeVarargs public static UsingClasses.Animal theBiggestFish(List<UsingClasses.Animal>...someAnimalLists) {

        Stream<UsingClasses.Animal> fishStream = Stream.empty();

        for (List<UsingClasses.Animal> list: someAnimalLists) {
            fishStream = Stream.concat(
                fishStream, list.stream().filter(elem -> elem.type().equals(UsingClasses.Animal.Type.FISH)));
        }

        return fishStream
            .max(Comparator.comparing(UsingClasses.Animal::weight))
            .get();
    }

    // Task19: Животные, в записях о которых есть ошибки -> Map<String, Set<ValidationError>>.
    public static Map<String, Set<UsingClasses.ValidationError>> findAnimalErrors(
        List<UsingClasses.Animal> receivedAnimals) {

        return receivedAnimals.stream()
            .collect(Collectors.toMap(UsingClasses.Animal::name,
                UsingClasses::getSetOfErrors));
    }

    // Task20: Сделать результат предыдущего задания более читабельным -> Map<String, String>
    public static Map<String, String> findAnimalErrorsLikeString(
        List<UsingClasses.Animal> receivedAnimals) {

        Map<String, Set<UsingClasses.ValidationError>> mapOfErrors = findAnimalErrors(receivedAnimals);
        Map<String, String> resultStringMap = new HashMap<>();

        for (var mapElement: mapOfErrors.entrySet()) {
            resultStringMap.put(mapElement.getKey(), mapElement.getValue().stream()
                .map(UsingClasses.ValidationError::getMassage)
                .sorted()
                .collect(Collectors.joining()));
        }

        return resultStringMap;
    }
}
