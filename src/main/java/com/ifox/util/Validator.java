package com.ifox.util;

public class Validator {
    private static String timeMatcher = "\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}";
    private static String idMatcher = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";

    public static boolean timeValidate(String input) {
        if (input.matches(timeMatcher)) {
            return true;
        }
        return false;
    }

    public static boolean idValidate(String input) {
        if (input.matches(idMatcher)) {
            return true;
        }
        return false;
    }

}
