package main.java.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final DateTimeFormatter DEFAULT_DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Parse a date string to LocalDate using the default date pattern (dd-MM-yyyy).
     *
     * @param dateStr the date string to parse
     * @return a LocalDate object
     * @throws IllegalArgumentException if the string cannot be parsed
     */
    public static LocalDate parseDate(final String dateStr) {
        try {
            return LocalDate.parse(dateStr, DEFAULT_DATE_PATTERN);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr + ". Expected format is dd-MM-yyyy.");
        }
    }

    /**
     * Format a LocalDate object to a string using the default date pattern (dd-MM-yyyy).
     *
     * @param date the LocalDate to format
     * @return the formatted date string
     */
    public static String formatDate(final LocalDate date) {
        return date.format(DEFAULT_DATE_PATTERN);
    }
}
