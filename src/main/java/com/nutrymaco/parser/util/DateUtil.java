package com.nutrymaco.parser.util;

import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    private final static Map<String, Integer> monthNumbers = new HashMap<>(){{
        put("января", 1);
        put("февраля", 2);
        put("марта", 3);
        put("апреля", 4);
        put("мая", 5);
        put("июня", 6);
        put("июля", 7);
        put("августа", 8);
        put("сентября", 9);
        put("октября", 10);
        put("ноября", 11);
        put("декабря", 12);
    }};

    public static int getNumberOfMonth(String month) {
        return monthNumbers.get(month);
    }

}
