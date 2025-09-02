package com.heartz.byeboo.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static final Pattern graphemePattern = Pattern.compile("\\X");
    private static final DateTimeFormatter PERIOD_FORMATTER = DateTimeFormatter.ofPattern("yyyy. MM. dd.");

    public static int lengthWithEmoji(String text) {
        if (text == null) return 0;

        Matcher matcher = graphemePattern.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static String formatPeriod(LocalDate start, LocalDate end) {
        if (start == null || end == null) return "";
        return start.format(PERIOD_FORMATTER) + " - " + end.format(PERIOD_FORMATTER);
    }
}
