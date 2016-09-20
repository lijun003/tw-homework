package com.ifox.util;

public class Validator {
    private static final String TIME_MATCHER = "\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}";
    private static final String ID_MATCHER = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";

    public static boolean timeValidate(String input) {
        return input.matches(TIME_MATCHER) ? true : false;
    }

    public static boolean idValidate(String input) {
        return input.matches(ID_MATCHER) ? true : false;
    }

}
