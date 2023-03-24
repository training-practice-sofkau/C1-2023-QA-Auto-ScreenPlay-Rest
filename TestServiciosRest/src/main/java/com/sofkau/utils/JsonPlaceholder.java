package com.sofkau.utils;

public enum JsonPlaceholder {
    REQRES_BASE_URL("https://jsonplaceholder.typicode.com"),
    LIST_SUCCESSFUL_RESOURCE("/albums/@id");

    private final String  value;

    JsonPlaceholder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}