package edu.hw9;

import edu.hw9.Task2.TreeThreadSearcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.apache.commons.io.FilenameUtils;

public class Task2Test {

    @Test
    @DisplayName("Проверка работы findDirectories()")
    void findDirectoriesTest() {
        List<File> files = TreeThreadSearcher.findDirectories(Paths.get("src"), 3);

        assertThat(!files.isEmpty()).isTrue();

        for (int i = 0; i < files.size(); i++) {
            assertThat(checkFile(files.get(i))).isTrue();
            for (int j = i + 1; j < files.size(); j++) {
                assertThat(files.get(i).equals(files.get(j))).isFalse();
            }
        }
    }

    @Test
    @DisplayName("Проверка работы findFiles()")
    void findFilesTest() throws IOException {
        List<File> files = TreeThreadSearcher.findFiles(Paths.get("src"), new Predicate<File>() {
            @Override
            public boolean test(File file) {
                try {
                    return Files.size(file.toPath()) >= 2500 && FilenameUtils.getExtension(file.getName()).equals("java");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        assertThat(!files.isEmpty()).isTrue();

        for (var elem: files) {
            assertThat(FilenameUtils.getExtension(elem.getName()).equals("java") && Files.size(elem.toPath()) >= 2500).isTrue();
        }
    }

    boolean checkFile(File file) {
        Queue<File> files = new ArrayDeque<>();
        files.add(file);

        boolean ok = false;
        int fileCount = 0;
        while (!files.isEmpty()) {
            File currentFile = files.poll();
            if (currentFile.isFile()) {
                fileCount++;
            } else {
                List<File> f = Arrays.asList(currentFile.listFiles());
                for (var elem: f) {
                    files.add(elem);
                }
            }

            if (fileCount >= 3) {
                ok = true;
                break;
            }
        }

        return ok;
    }
}
