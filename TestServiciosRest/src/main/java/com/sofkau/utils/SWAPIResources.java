package com.sofkau.utils;

public enum SWAPIResources {
    SWAPI_BASE_URL("https://swapi.dev/api"),
    DARTH_VADER_RESOURSE("/people/");

    private final String  value;

    SWAPIResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
