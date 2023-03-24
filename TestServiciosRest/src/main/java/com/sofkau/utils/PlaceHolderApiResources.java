package com.sofkau.utils;

public enum PlaceHolderApiResources {


    PLACE_HOLDER_BASE_URL("https://jsonplaceholder.typicode.com/"),
    PLACE_HOLDER_BASE_URL_POST_PHOTOS("photos/");

    private final String  value;
    PlaceHolderApiResources(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

}
