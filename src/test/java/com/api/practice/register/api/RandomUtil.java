package com.api.practice.register.api;

import java.util.Random;

public class RandomUtil {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    public static String randomUsername() {
        int length = RANDOM.nextInt(7) + 6; // 6-12 ký tự

        StringBuilder username = new StringBuilder();

        for (int i = 0; i < length; i++) {
            username.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }

        return username.toString();
    }
}