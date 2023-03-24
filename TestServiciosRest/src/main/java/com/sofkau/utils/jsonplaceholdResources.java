package com.sofkau.utils;

public enum jsonplaceholdResources {
    JSONPLACEHOLD__BASE_URL("https://jsonplaceholder.typicode.com"),
    JSONPLACEHOLD_RESOURCE("/posts"),
    JSONPLACEHOLD_RESOURCE_PHOTOS("/photos/");
    private final String  value;

    jsonplaceholdResources(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
