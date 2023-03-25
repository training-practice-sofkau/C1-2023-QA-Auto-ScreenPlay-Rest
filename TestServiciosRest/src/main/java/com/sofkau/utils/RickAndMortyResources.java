package com.sofkau.utils;

public enum RickAndMortyResources {
    RICK_AND_MORTY_BASE_URL("https://rickandmortyapi.com/api"),
    //RICK_AND_MORTY_BASE_URL("https://reqres.in/api/users?"),
    RESOURCE("/character/");
    //RESOURCE("page=?");

    private final String  value;

    RickAndMortyResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
