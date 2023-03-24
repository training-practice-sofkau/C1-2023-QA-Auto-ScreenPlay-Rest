package com.sofkau.utils;

public enum ResourceCases {
    SINGLE_USER("/users"),
    UPDATE_USER("/users/2"),
    CREATE_POST("/posts"),
    DELETE_ALBUM("/albums/");

    private final String value;

    ResourceCases(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

