package com.example.model;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(String s, Locale locale) throws ParseException {
        try {
            return LocalTime.parse(s, DateTimeFormatter.ofPattern("HH:mm", locale));
        } catch (DateTimeParseException ex) {
            throw new ParseException(ex.getMessage(), ex.getErrorIndex());
        }
    }

    @Override
    public String print(LocalTime localTime, Locale locale) {
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm", locale));
    }

}
