package edu.hw6.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    private static final Pattern FILE_NAME_PATTERN =
        Pattern.compile("^([a-zA-Z0-9._ -]+[a-zA-Z0-9_ -])(\\.[a-z]+)$");

    private static final String COPY_STRING_PART = " - копия";

    private Task2() {}

    public static void cloneFile(Path path) {
        File file = path.toFile();

        try {
            if (!file.createNewFile()) {
                String fileName = path.getFileName().toString();
                Matcher fileNameMatcher = FILE_NAME_PATTERN.matcher(fileName);
                StringBuilder resultName = new StringBuilder();

                if (fileNameMatcher.find()) {
                    resultName.append(fileNameMatcher.group(1)).append(COPY_STRING_PART);

                    if (!new File(resultName + fileNameMatcher.group(2)).createNewFile()) {
                        int fileCreateAttempt = 1;

                        while (!new File(resultName + "(" + fileCreateAttempt + ")"
                            + fileNameMatcher.group(2)).createNewFile()) {

                            fileCreateAttempt++;
                        }
                    }
                } else {
                    resultName.append(fileName).append(COPY_STRING_PART);
                    if (!new File(resultName.toString()).createNewFile()) {
                        int fileCreateAttempt = 1;

                        while (!new File(resultName + "(" + fileCreateAttempt + ")").createNewFile()) {
                            fileCreateAttempt++;
                        }
                    }
                }
            }
        } catch (IOException ignored) {
        }
    }
}
