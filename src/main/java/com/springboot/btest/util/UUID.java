package com.springboot.btest.util;

public class UUID {
    public static String gen() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
