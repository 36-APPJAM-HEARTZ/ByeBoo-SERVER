package com.heartz.byeboo.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static final Pattern graphemePattern = Pattern.compile("\\X");

    public static int lengthWithEmoji(String text) {
        if (text == null) return 0;

        Matcher matcher = graphemePattern.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
