package com.sofkau.utils;

public enum JsonPlaceholderDelete {
    REQRES_BASE_URL("https://jsonplaceholder.typicode.com"),
    LIST_SUCCESSFUL_RESOURCE("/albums/@id");

    private final String  value;

    JsonPlaceholderDelete(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
