package com.sofkau.utils;

public enum JsonPlaceholderPut {
    REQRES_BASE_URL("https://jsonplaceholder.typicode.com"),
    LIST_SUCCESSFUL_RESOURCE("/todos/@id");

    private final String  value;

    JsonPlaceholderPut(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
