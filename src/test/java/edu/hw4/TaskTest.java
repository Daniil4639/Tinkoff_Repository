package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TaskTest {

    private static final List<UsingClasses.Animal> ANIMAL_LIST = new ArrayList<>(List.of(
        new UsingClasses.Animal("cat first", UsingClasses.Animal.Type.CAT, UsingClasses.Animal.Sex.M, 4, 210, 390, true),
        new UsingClasses.Animal("cat_2", UsingClasses.Animal.Type.CAT, UsingClasses.Animal.Sex.F, 14, 15, 4, false),
        new UsingClasses.Animal("bird_1", UsingClasses.Animal.Type.BIRD, UsingClasses.Animal.Sex.F, 2, 2, 7, false),
        new UsingClasses.Animal("bird_2", UsingClasses.Animal.Type.BIRD, UsingClasses.Animal.Sex.F, 7, 7, 4, true),
        new UsingClasses.Animal("just a bird", UsingClasses.Animal.Type.BIRD, UsingClasses.Animal.Sex.M, 3, 19, 19, false),
        new UsingClasses.Animal("longest fish in the task", UsingClasses.Animal.Type.FISH, UsingClasses.Animal.Sex.F, 1, 114, 115, true),
        new UsingClasses.Animal("very tall dog", UsingClasses.Animal.Type.DOG, UsingClasses.Animal.Sex.M, 0, 315, 54, true)
    ));

    @Test
    @DisplayName("Тестирование heightAnimalSort()")
    void heightAnimalSortTest() {

        List<UsingClasses.Animal> sortedList = Task.heightAnimalSort(ANIMAL_LIST);

        assertThat(sortedList.toArray(new UsingClasses.Animal[0])).extracting(UsingClasses.Animal::height).contains(2, 7, 15, 19, 114, 210, 315);
    }

    @Test
    @DisplayName("Тестирование weightAnimalSort")
    void weightAnimalSortTest() {

        //ситуация с k < размера
        assertThat(Task.weightAnimalSort(ANIMAL_LIST, 2).toArray(new UsingClasses.Animal[0])).extracting(
            UsingClasses.Animal::weight).contains(390, 115);

        //ситуация с k > размера
        assertThat(Task.weightAnimalSort(ANIMAL_LIST, 15).toArray(new UsingClasses.Animal[0])).extracting(
            UsingClasses.Animal::weight).contains(390, 115, 54, 19, 7, 4, 4);
    }

    @Test
    @DisplayName("Тестирование animalCounterPerType")
    void animalCounterPerTypeTest() {

        //непустой массив
        Map<UsingClasses.Animal.Type, Integer> animalCountMap = Task.animalCounterPerType(ANIMAL_LIST);
        assertThat(animalCountMap.size()).isEqualTo(4);
        assertThat(animalCountMap.get(UsingClasses.Animal.Type.CAT)).isEqualTo(2);
        assertThat(animalCountMap.get(UsingClasses.Animal.Type.BIRD)).isEqualTo(3);
        assertThat(animalCountMap.get(UsingClasses.Animal.Type.FISH)).isEqualTo(1);
        assertThat(animalCountMap.get(UsingClasses.Animal.Type.DOG)).isEqualTo(1);

        //пустой массив
        assertThat(Task.animalCounterPerType(new ArrayList<>()).size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Тестирование longestName")
    void longestNameTest() {

        assertThat(Task.longestName(ANIMAL_LIST).name()).isEqualTo("longest fish in the task");
    }

    @Test
    @DisplayName("Тестирование sexCounter")
    void sexCounterTest() {

        assertThat(Task.sexCounter(ANIMAL_LIST)).isEqualTo(UsingClasses.Animal.Sex.F);
    }

    @Test
    @DisplayName("Тестирование biggestWeightPerType")
    void biggestWeightPerTypeTest() {

        Map<UsingClasses.Animal.Type, UsingClasses.Animal> weightAnimalMap = Task.biggestWeightPerType(ANIMAL_LIST);

        assertThat(weightAnimalMap.size()).isEqualTo(4);
        assertThat(weightAnimalMap.get(UsingClasses.Animal.Type.CAT).name()).isEqualTo("cat first");
        assertThat(weightAnimalMap.get(UsingClasses.Animal.Type.BIRD).name()).isEqualTo("just a bird");
        assertThat(weightAnimalMap.get(UsingClasses.Animal.Type.FISH).name()).isEqualTo("longest fish in the task");
        assertThat(weightAnimalMap.get(UsingClasses.Animal.Type.DOG).name()).isEqualTo("very tall dog");
    }

    @Test
    @DisplayName("Тестирование oldestAnimalByNumberK")
    void oldestAnimalByNumberKTest() {

        //k < размера
        assertThat(Task.oldestAnimalByNumberK(ANIMAL_LIST, 2).name()).isEqualTo("bird_2");

        //k > размера
        assertThat(Task.oldestAnimalByNumberK(ANIMAL_LIST, 15).name()).isEqualTo("very tall dog");
    }

    @Test
    @DisplayName("Тестирование biggestWeightWithOptionalHeight")
    void biggestWeightWithOptionalHeightTest() {

        assertThat(Task.biggestWeightWithOptionalHeight(ANIMAL_LIST, 16).get().name()).isEqualTo("bird_1");
    }

    @Test
    @DisplayName("Тестирование sumOfPaws")
    void sumOfPawsTest() {
        List<UsingClasses.Animal> animalList = new ArrayList<>(List.of(
            new UsingClasses.Animal("cat_1", UsingClasses.Animal.Type.CAT, UsingClasses.Animal.Sex.M, 2, 32, 14, true),
            new UsingClasses.Animal("cat_2", UsingClasses.Animal.Type.CAT, UsingClasses.Animal.Sex.F, 14, 15, 4, true),
            new UsingClasses.Animal("bird_1", UsingClasses.Animal.Type.BIRD, UsingClasses.Animal.Sex.F, 3, 2, 10, true)
            ));

        //непустой массив
        assertThat(Task.sumOfPaws(animalList)).isEqualTo(10);

        //пустой массив
        assertThat(Task.sumOfPaws(new ArrayList<UsingClasses.Animal>())).isEqualTo(0);
    }

    @Test
    @DisplayName("Тестирование animalsWithDiffPawsAndAge")
    void animalsWithDiffPawsAndAgeTest() {

        assertThat(Task.animalsWithDiffPawsAndAge(ANIMAL_LIST).size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Тестирование animalWhichCanBite")
    void animalWhichCanBiteTest() {

        List<UsingClasses.Animal> newAnimalList = Task.animalWhichCanBite(ANIMAL_LIST);
        assertThat(newAnimalList.size()).isEqualTo(3);
        assertThat(newAnimalList.stream().mapToInt(UsingClasses.Animal::height).sorted().toArray()).containsExactly(114, 210, 315);
    }

    @Test
    @DisplayName("Тестирование weightBiggerThanHeight")
    void weightBiggerThanHeightTest() {

        assertThat(Task.weightBiggerThanHeight(ANIMAL_LIST)).isEqualTo(3);
    }

    @Test
    @DisplayName("Тестирование animalsWithDoubleName")
    void animalsWithDoubleNameTest() {

        assertThat(Task.animalsWithDoubleName(ANIMAL_LIST).size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Тестирование isDogWithSuchHeightInList")
    void isDogWithSuchHeightInListTest() {

        assertThat(Task.isDogWithSuchHeightInList(ANIMAL_LIST, 300)).isTrue();

        assertThat(Task.isDogWithSuchHeightInList(ANIMAL_LIST, 400)).isFalse();
    }

    @Test
    @DisplayName("Тестирование sumWeightWithBorders")
    void sumWeightWithBordersTest() {

        assertThat(Task.sumWeightWithBorders(ANIMAL_LIST, 2, 7)).isEqualTo(420);
    }

    @Test
    @DisplayName("Тестирование paramsSortAnimals")
    void paramsSortAnimalsTest() {

        ArrayList<String> nameList = new ArrayList<>();
        for (var elem: Task.paramsSortAnimals(ANIMAL_LIST)) {
            nameList.add(elem.name());
        }

        assertThat(nameList.toArray(new String[0])).containsExactly("cat first", "cat_2", "very tall dog",
            "just a bird", "bird_1", "bird_2", "longest fish in the task");
    }

    @Test
    @DisplayName("Тестирование spidersOfDogs")
    void spidersOfDogsTest() {
        List<UsingClasses.Animal> animalList = new ArrayList<>(List.of(
            new UsingClasses.Animal("dog_1", UsingClasses.Animal.Type.DOG, UsingClasses.Animal.Sex.M, 4, 210, 390, true),
            new UsingClasses.Animal("spider_1", UsingClasses.Animal.Type.SPIDER, UsingClasses.Animal.Sex.F, 14, 15, 4, false),
            new UsingClasses.Animal("spider_2", UsingClasses.Animal.Type.SPIDER, UsingClasses.Animal.Sex.F, 2, 2, 7, true),
            new UsingClasses.Animal("dog_2", UsingClasses.Animal.Type.DOG, UsingClasses.Animal.Sex.F, 7, 7, 4, true)
        ));

        //достаточно данных
        assertThat(Task.spidersOfDogs(animalList)).isFalse();

        //недостаточно информации (нет типа SPIDER)
        assertThat(Task.spidersOfDogs(new ArrayList<>(List.of(animalList.get(0))))).isFalse();
    }

    @Test
    @DisplayName("Тестирование theBiggestFish")
    void theBiggestFishTest() {
        List<UsingClasses.Animal> animalList_1 = new ArrayList<>(List.of(
            new UsingClasses.Animal("fish_1", UsingClasses.Animal.Type.FISH, UsingClasses.Animal.Sex.M, 4, 210, 390, true),
            new UsingClasses.Animal("fish_2", UsingClasses.Animal.Type.FISH, UsingClasses.Animal.Sex.F, 14, 15, 4, false)
        ));

        List<UsingClasses.Animal> animalList_2 = new ArrayList<>(List.of(
            new UsingClasses.Animal("fish_3", UsingClasses.Animal.Type.FISH, UsingClasses.Animal.Sex.M, 4, 210, 415, true),
            new UsingClasses.Animal("fish_4", UsingClasses.Animal.Type.FISH, UsingClasses.Animal.Sex.F, 14, 15, 77, false)
        ));

        assertThat(Task.theBiggestFish(animalList_1, animalList_2).name()).isEqualTo("fish_3");
    }

    @Test
    @DisplayName("Тестирование findAnimalErrorsLikeString")
    void findAnimalErrorsLikeStringTest() throws NoSuchFieldException, IllegalAccessException {
        List<UsingClasses.Animal> animalList = new ArrayList<>(List.of(
            new UsingClasses.Animal("an1", UsingClasses.Animal.Type.DOG, null, 4, 0, 390, true),
            new UsingClasses.Animal("an2", null, null, 14, 15, 4, false),
            new UsingClasses.Animal("an3", UsingClasses.Animal.Type.SPIDER, UsingClasses.Animal.Sex.F, -4, 2, 7, true),
            new UsingClasses.Animal("an4", UsingClasses.Animal.Type.DOG, UsingClasses.Animal.Sex.F, 7, 7, 2, true)
        ));

        Map<String, String> resultMap = Task.findAnimalErrorsLikeString(animalList);

        assertThat(resultMap.get("an1")).isEqualTo("sex: is null" + System.lineSeparator() + "height: is zero" + System.lineSeparator());
        assertThat(resultMap.get("an2")).isEqualTo("type: is null" + System.lineSeparator() + "sex: is null" + System.lineSeparator());
        assertThat(resultMap.get("an3")).isEqualTo("age: is negative" + System.lineSeparator());
        assertThat(resultMap.get("an4")).isEqualTo("");
    }
}
