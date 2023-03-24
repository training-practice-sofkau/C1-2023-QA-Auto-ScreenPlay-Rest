package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL("https://reqres.in/"),
    POKEAPI_BASE_URL("https://pokeapi.co/"),
    GENERATION1_RESOURCE("api/v2/generation/"),
    REGISTER_SUCCESSFUL_RESOURCE("api/register"),
    PHOTO_POST_RESOURCE("/photos"),
    LOGIN_SUCCESSFUL_RESOURCE("api/login"),
    JSONPLACEHOLDER_BASE_URL("https://jsonplaceholder.typicode.com");

    private final String  value;

    ReqresResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
