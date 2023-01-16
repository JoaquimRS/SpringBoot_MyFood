package com.myfood.springboot_myfood.domain.common.utils;

public class Utils {
    public static String capitalize(String toCapitalize) {
        return String.valueOf(toCapitalize.charAt(0))
                .toUpperCase() + toCapitalize.substring(1);
    }
}
