package edu.hw5.Task3;

import java.time.LocalDate;

public abstract class DateParser {
    protected DateParser nextParser;

    public abstract LocalDate stringTransform(String string);
}
