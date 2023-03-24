package com.sofkau.utils;

public enum ListFruitsResources {
    FRUITYVICE_BASE_URL("https://fruityvice.com/api"),
    LIST_FRUITS_RESOURCES("/fruit/");
    private final String  value;

    ListFruitsResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
