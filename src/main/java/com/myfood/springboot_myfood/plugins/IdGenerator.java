package com.myfood.springboot_myfood.plugins;

import java.util.UUID;

public class IdGenerator {
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateWithLength(Integer length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }
}