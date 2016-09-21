package com.ifox.util;

import com.ifox.exception.InvalidFomatException;

public class Validator {
    private static final String TIME_MATCHER = "\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}";
    private static final String ID_MATCHER = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";
    private static final String INVALID_FORMAT = "Invalid Format";

    public static boolean timeValidate(String input) {
        return input.matches(TIME_MATCHER) ? true : false;
    }

    private static boolean idValidate(String input) {
        return input.matches(ID_MATCHER) ? true : false;
    }

    public static void checkIdFormat(String id) {
        if (!idValidate(id)) {
            throw  new InvalidFomatException(INVALID_FORMAT);

        }
    }

    public static void checkTimeFormat(String time) {
        if (!Validator.timeValidate(time)) {
            throw new InvalidFomatException(INVALID_FORMAT);
        }
    }

}
