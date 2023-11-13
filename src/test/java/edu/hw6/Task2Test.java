package edu.hw6;

import edu.hw6.Task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Paths;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    private final static String FIRST_FILE_PATH = "file1.txt";
    private final static String SECOND_FILE_PATH = "file1";

    @Test
    @DisplayName("Проверка работы cloneFile()")
    void cloneFileTest() {
        assertThat(new File("file1.txt").exists()).isEqualTo(false);

        Task2.cloneFile(Paths.get(FIRST_FILE_PATH));
        assertThat(new File("file1.txt").exists()).isEqualTo(true);

        Task2.cloneFile(Paths.get(FIRST_FILE_PATH));
        assertThat(new File("file1 - копия.txt").exists()).isEqualTo(true);

        Task2.cloneFile(Paths.get(FIRST_FILE_PATH));
        assertThat(new File("file1 - копия(1).txt").exists()).isEqualTo(true);

        assertThat(new File("file1").exists()).isEqualTo(false);

        Task2.cloneFile(Paths.get(SECOND_FILE_PATH));
        assertThat(new File("file1.txt").exists()).isEqualTo(true);

        Task2.cloneFile(Paths.get(SECOND_FILE_PATH));
        assertThat(new File("file1 - копия").exists()).isEqualTo(true);

        Task2.cloneFile(Paths.get(SECOND_FILE_PATH));
        assertThat(new File("file1 - копия(1)").exists()).isEqualTo(true);

        clear();
    }

    private void clear() {
        File file = new File("file1.txt");
        file.delete();
        file = new File("file1 - копия.txt");
        file.delete();
        file = new File("file1 - копия(1).txt");
        file.delete();
        file = new File("file1");
        file.delete();
        file = new File("file1 - копия");
        file.delete();
        file = new File("file1 - копия(1)");
        file.delete();
    }
}
