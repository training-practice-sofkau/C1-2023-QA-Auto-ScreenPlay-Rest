package com.sofkau.utils;

public enum PlaceholderResources {

    JSONPLACEHOLDER_BASE_URL("https://jsonplaceholder.typicode.com/"),
    GET_RESOURCE("posts/");

    private final String  value;

    PlaceholderResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
