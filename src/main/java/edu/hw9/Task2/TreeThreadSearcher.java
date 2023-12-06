package edu.hw9.Task2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TreeThreadSearcher {

    private static List<File> correctDirectories;
    private static int filesCountInDirectory;
    private static List<File> correctFiles;
    private static Predicate<File> filePredicate;

    private TreeThreadSearcher() {}

    public static List<File> findDirectories(Path start, int filesCount) {
        correctDirectories = new ArrayList<>();
        filesCountInDirectory = filesCount;

        DirectoryReader directoryReader = new DirectoryReader(start.toFile());
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(directoryReader);

        pool.close();
        return correctDirectories;
    }

    public static List<File> findFiles(Path start, Predicate<File> predicate) {
        correctFiles = new ArrayList<>();
        filePredicate = predicate;

        FileSearcher fileSearcher = new FileSearcher(start.toFile());
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(fileSearcher);

        pool.close();
        return correctFiles;
    }

    private static class FileSearcher extends RecursiveAction {

        private final File currentFile;

        FileSearcher(File file) {
            this.currentFile = file;
        }

        @Override
        protected void compute() {
            if (currentFile.isFile()) {
                if (filePredicate.test(currentFile)) {
                    correctFiles.add(currentFile);
                }
            } else {
                List<File> filesInDirectory = Arrays.asList(Objects.requireNonNull(currentFile.listFiles()));

                if (!filesInDirectory.isEmpty()) {

                    Stream<FileSearcher> fileStream = filesInDirectory.stream()
                        .map(FileSearcher::new);

                    fileStream.forEach(ForkJoinTask::fork);
                }
            }
        }
    }

    private static class DirectoryReader extends RecursiveTask<Integer> {

        private final File currentFile;

        DirectoryReader(File file) {
            this.currentFile = file;
        }

        @Override
        protected Integer compute() {
            if (currentFile.isDirectory()) {
                List<File> filesInDirectory = Arrays.asList(Objects.requireNonNull(currentFile.listFiles()));

                if (filesInDirectory.isEmpty()) {
                    return 0;
                }

                int result =  filesInDirectory.stream()
                        .map(DirectoryReader::new)
                        .peek(ForkJoinTask::fork)
                        .map(ForkJoinTask::join)
                        .mapToInt(Integer::intValue)
                        .sum();

                if (result >= filesCountInDirectory) {
                    correctDirectories.add(currentFile);
                }

                return result;
            } else {
                return 1;
            }
        }
    }
}
