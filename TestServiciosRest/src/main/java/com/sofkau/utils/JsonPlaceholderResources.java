package com.sofkau.utils;

public enum JsonPlaceholderResources {
    BASE_URL("https://jsonplaceholder.typicode.com/"),
    PUT_POST_RESOURCE("posts/1");

    private final String value;

    JsonPlaceholderResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
