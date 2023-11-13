package edu.hw6;

import edu.hw6.Task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Проверка работы streamSequenceWriting()")
    void streamSequenceWritingTest() {
        Task4.streamSequenceWriting("file1.txt",
            "Programming is learned by writing programs. ― Brian Kernighan");

        File file = new File("file1.txt");
        assertThat(file.exists()).isTrue();

        int lineCount = 0;
        String massage = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            massage = bufferedReader.readLine();
            lineCount++;
        } catch (Exception ignored) {
        }

        assertThat(lineCount).isEqualTo(1);
        assertThat(massage).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");

        file.delete();
    }
}
