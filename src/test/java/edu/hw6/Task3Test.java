package edu.hw6;

import edu.hw6.Task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Проверка работы AbstractFilter")
    void abstractFilterTest() {
        DirectoryStream.Filter<Path> filter = Task3.readableFile().and(Task3.regularFile()).and(Task3.globMatches("*.xml"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Paths.get(""), filter)) {
            List<Path> paths = new ArrayList<>();
            entries.forEach(paths::add);

            assertThat(paths.toArray().length).isEqualTo(2);
            assertThat(paths.toArray()).contains(Paths.get("checkstyle.xml"));
            assertThat(paths.toArray()).contains(Paths.get("pom.xml"));
        } catch (IOException ignored) {
        }

        DirectoryStream.Filter<Path> filter1 = Task3.writableFile().and(Task3.largerThan(11_000))
            .and(Task3.lessThan(18_000)).and(Task3.regexContains("vnw"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Paths.get(""), filter1)) {
            List<Path> paths = new ArrayList<>();
            entries.forEach(paths::add);

            assertThat(paths.toArray().length).isEqualTo(1);
            assertThat(paths.toArray()).contains(Paths.get("mvnw"));
        } catch (IOException ignored) {
        }
    }
}
