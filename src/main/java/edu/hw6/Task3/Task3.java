package edu.hw6.Task3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;

public class Task3 {

    private Task3() {}

    public static AbstractFilter readableFile() {
        return Files::isReadable;
    }

    public static AbstractFilter writableFile() {
        return Files::isWritable;
    }

    public static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    public static CompositeFilter largerThan(int fileSize) {
        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.predicate = path -> path.toFile().length() > fileSize;

        return compositeFilter;
    }

    public static CompositeFilter lessThan(int fileSize) {
        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.predicate = path -> path.toFile().length() < fileSize;

        return compositeFilter;
    }

    public static CompositeFilter globMatches(String globString) {
        String extension = globString.substring(2);
        Predicate<Path> predicate = path -> extension.equals(FilenameUtils.getExtension(path.getFileName().toString()));

        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.predicate = predicate;

        return compositeFilter;
    }

    public static CompositeFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Predicate<Path> predicate = path -> pattern.matcher(path.getFileName().toString()).find();

        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.predicate = predicate;

        return compositeFilter;
    }

    public static CompositeFilter magicNumber(char... identifiers) {
        Predicate<Path> predicate = path -> {
            try (InputStream inputStream = new FileInputStream(path.toFile())) {
                byte[] bytes = new byte[1];
                int readByte = inputStream.read(bytes);

                if (readByte == 1) {
                    for (var identifier: identifiers) {
                        if (bytes[0] == identifier) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (IOException ignored) {
                return false;
            }
        };

        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.predicate = predicate;

        return compositeFilter;
    }

    public interface AbstractFilter extends DirectoryStream.Filter<Path> {

        default CompositeFilter and(AbstractFilter extraFilter) {
            CompositeFilter compositeFilter = new CompositeFilter();
            compositeFilter.previousFilters.add(extraFilter);
            compositeFilter.previousFilters.add(this);

            return compositeFilter;
        }
    }

    public static class CompositeFilter implements AbstractFilter {

        public List<AbstractFilter> previousFilters = new ArrayList<>();
        public Predicate<Path> predicate;

        @Override
        public boolean accept(Path entry) throws IOException {
            boolean testsPassed = true;
            if (predicate != null) {
                testsPassed = predicate.test(entry);
            }

            if (testsPassed) {

                for (var test: previousFilters) {
                    if (!test.accept(entry)) {
                        testsPassed = false;
                        break;
                    }
                }

                return testsPassed;
            }

            return false;
        }
    }
}
