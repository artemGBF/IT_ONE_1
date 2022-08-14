package ru.vk.competition.minbenchmark.util;

import java.util.Locale;

public class Util {

    public static String transferTypes(String type) {
        if (type.toLowerCase(Locale.ROOT).contains("int")) {
            return "INTEGER";
        }
        if (type.toLowerCase(Locale.ROOT).contains("char")) {
            return "CHARACTER VARYING";
        }
        return type.toUpperCase(Locale.ROOT);
    }
}
