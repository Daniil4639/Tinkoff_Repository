package edu.hw6.Task1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {

    private final Path filePath;
    private final static Logger LOGGER = LogManager.getLogger();
    private final Pattern entryPattern = Pattern.compile("^(.*): (.*)$");
    private final String openError = "Undefined error during file opening!";
    private final String readError = "Undefined error during file reading!";

    public DiskMap() {
        filePath = Paths.get("Map_Storage.txt");

        try (OutputStream outputStream = new BufferedOutputStream(
            Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {

            outputStream.write("".getBytes());
        } catch (IOException ioException) {
            LOGGER.info(openError);
        }
    }

    @Override
    public int size() {
        int lineCount = 0;

        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {

            while (bufferedReader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException ioException) {
            LOGGER.info(readError);
        }

        return lineCount;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean containsKey(Object key) {

        return getKeyAndValuesArray((String) key, 1) == null;
    }

    @Override
    public boolean containsValue(Object value) {

        return getKeyAndValuesArray((String) value, 2) == null;
    }

    @Override
    public String get(Object key) {

        List<Pair<String, String>> elementsArray = getKeyAndValuesArray(null, 1);
        if (elementsArray != null) {
            for (var element : elementsArray) {
                if (element.getLeft().equals(key)) {
                    return element.getRight();
                }
            }
        }

        return null;
    }

    @Override
    public String put(String key, String value) {
        List<Pair<String, String>> elementsArray = getKeyAndValuesArray(key, 1);
        if (elementsArray != null && key != null && value != null) {
            elementsArray.add(new ImmutablePair<>(key, value));

            elementsArray.sort(Comparator.comparing(Pair::getLeft));

            putKeyAndValuesArray(elementsArray);
        }

        return null;
    }

    @Override
    public String remove(Object key) {
        List<Pair<String, String>> elementsArray = getKeyAndValuesArray(null, 0);
        if (elementsArray != null) {
            putKeyAndValuesArray(elementsArray.stream()
                .filter(elem -> !elem.getLeft().equals(key))
                .toList());
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        List<Pair<String, String>> elementsArray = getKeyAndValuesArray(null, 0);
        if (elementsArray != null) {

            for (var entry: m.entrySet()) {
                boolean isOk = true;

                for (var element: elementsArray) {
                    if (element.getLeft().equals(entry.getKey())) {
                        isOk = false;
                        break;
                    }
                }

                if (isOk) {
                    elementsArray.add(new ImmutablePair<>(entry.getKey(), entry.getValue()));
                }
            }

            elementsArray.sort(Comparator.comparing(Pair::getLeft));
            putKeyAndValuesArray(elementsArray);
        }
    }

    @Override
    public void clear() {
        try (OutputStream outputStream = new BufferedOutputStream(
            Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {

            outputStream.write("".getBytes());
        } catch (IOException ioException) {
            LOGGER.info(openError);
        }
    }

    @NotNull
    @Override
    public Set keySet() {
        List<Pair<String, String>> elementsArray = getKeyAndValuesArray(null, 0);
        Set<String> setOfKeys = new HashSet<>();

        if (elementsArray != null) {
            for (var element: elementsArray) {
                setOfKeys.add(element.getLeft());
            }
        }

        return setOfKeys;
    }

    @NotNull
    @Override
    public Collection values() {
        return Objects.requireNonNull(getKeyAndValuesArray(null, 0)).stream()
            .map(Pair::getRight)
            .toList();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        List<Pair<String, String>> elementsArray = getKeyAndValuesArray(null, 0);
        Set<Entry<String, String>> setOfEntries = new HashSet<>();

        if (elementsArray != null) {
            for (var element: elementsArray) {
                setOfEntries.add(new ImmutablePair<>(element.getLeft(), element.getRight()));
            }
        }

        return setOfEntries;
    }

    private List<Pair<String, String>> getKeyAndValuesArray(String checkString, int mode) {
        List<Pair<String, String>> elementsArray = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {

            String entry;
            while ((entry = bufferedReader.readLine()) != null) {
                Matcher entryMather = entryPattern.matcher(entry);

                if (entryMather.find()) {
                    if (mode == 1 && entryMather.group(1).equals(checkString)
                        || mode == 2 && entryMather.group(2).equals(checkString)) {
                        return null;
                    }

                    elementsArray.add(new ImmutablePair<>(entryMather.group(1), entryMather.group(2)));
                }
            }
        } catch (IOException ioException) {
            LOGGER.info(readError);
        }

        return elementsArray;
    }

    private void putKeyAndValuesArray(List<Pair<String, String>> elementsArray) {

        try (OutputStream outputStream = new BufferedOutputStream(
            Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {

            StringBuilder resultString = new StringBuilder();
            for (int index = 0; index < elementsArray.size(); index++) {
                resultString.append(elementsArray.get(index).getLeft()).append(": ")
                    .append(elementsArray.get(index).getRight()).append(System.lineSeparator());

                outputStream.write(resultString.toString().getBytes());
                resultString.setLength(0);
            }
        } catch (IOException ioException) {
            LOGGER.info(openError);
        }
    }
}
