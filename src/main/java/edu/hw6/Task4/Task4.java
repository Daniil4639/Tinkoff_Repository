package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    private Task4() {}

    public static void streamSequenceWriting(String fileName, String massage) {
        File file = new File(fileName);

        try (PrintWriter printWriter = new PrintWriter(
            new OutputStreamWriter(
                new BufferedOutputStream(
                    new CheckedOutputStream(
                        new FileOutputStream(file), new CRC32())), StandardCharsets.UTF_8))) {

            printWriter.write(massage);
        } catch (IOException ignored) {
        }
    }
}
