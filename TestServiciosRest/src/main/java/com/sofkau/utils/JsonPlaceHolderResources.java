package com.sofkau.utils;

public enum JsonPlaceHolderResources {

    JSON_PLACE_HOLDER_BASE_URL("https://jsonplaceholder.typicode.com/"),
    DELETE_RESOURCE("posts/1");

    private final String value;

    JsonPlaceHolderResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
