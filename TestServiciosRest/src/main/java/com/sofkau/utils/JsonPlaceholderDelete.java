package com.sofkau.utils;

public enum JsonPlaceholderDelete {
    REQRES_BASE_URL_PLACEHOLDER("https://jsonplaceholder.typicode.com"),
    DELETE_SUCCESSFUL_RESOURCE("/albums/@id");

    private final String  value;

    JsonPlaceholderDelete(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}