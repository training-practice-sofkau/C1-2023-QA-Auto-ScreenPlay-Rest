package com.sofkau.utils;

public enum Constants {
    ACTOR("API consumer"),
    REQRES_BASE_URL("https://reqres.in/"),
    REGISTER_SUCCESSFUL_RESOURCE("api/register"),
    JSON_PLACEHOLDER_BASE_URL("https://jsonplaceholder.typicode.com"),
    ALBUMS_RESOURCE("/albums/%s"),
    TO_DO_RESOURCE("/users/1/todos?id=%s"),
    FAKE_REST_API_BASE_URL("https://fakerestapi.azurewebsites.net"),
    BOOKS_RESOURCE("/api/v1/Books/%s");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
