package com.example.demo.entity.enums;

import java.util.Arrays;

public enum Language {
    CPP("cpp"),
    PYTHON("python"),
    C("c"),
    JAVA("java")
    ;

    private final String value;

    Language(String description) {
        this.value = description;
    }

    public static Language find(String value) {
        return Arrays.stream(values())
                .filter(language -> language.value.equals(value))
                .findAny()
                .orElse(null);
    }

    public static String find(Language language) {
        return Arrays.stream(values())
                .filter(l -> l == language)
                .map(l -> l.value)
                .findAny()
                .orElse(null);
    }
}
