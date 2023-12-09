package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Проверка работы DiskMap")
    void diskMapTest() {
        DiskMap diskMap = new DiskMap();
        diskMap.clear();
        assertThat(diskMap.isEmpty()).isEqualTo(true);
        assertThat(diskMap.containsKey("123")).isEqualTo(false);

        diskMap.put("123", "004");
        diskMap.put("123", "004");
        assertThat(diskMap.size()).isEqualTo(1);
        assertThat(diskMap.get("123")).isEqualTo("004");

        diskMap.put("22", "021");
        assertThat(diskMap.size()).isEqualTo(2);
        diskMap.remove("123");
        assertThat(diskMap.size()).isEqualTo(1);
        assertThat(diskMap.get("123")).isEqualTo(null);

        diskMap.putAll(new HashMap<>(Map.ofEntries(new ImmutablePair<>("325", "111"),
            new ImmutablePair<>("11", "03302"), new ImmutablePair<>("23", "112"))));

        assertThat(diskMap.size()).isEqualTo(4);

        assertThat(diskMap.values().toArray()).containsExactly("03302", "021", "112", "111");
        assertThat(Arrays.stream(diskMap.keySet().toArray()).sorted().toArray()).containsExactly(
            "11", "22", "23", "325"
        );

        assertThat(diskMap.containsValue("03302")).isEqualTo(true);

        diskMap.clear();
        assertThat(diskMap.isEmpty()).isEqualTo(true);

        //удаление файла
        File file = new File("Map_Storage.txt");
        file.delete();
    }
}
