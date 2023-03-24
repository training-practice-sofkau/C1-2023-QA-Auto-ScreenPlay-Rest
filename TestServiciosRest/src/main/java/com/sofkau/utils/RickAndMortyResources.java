package com.sofkau.utils;

public enum RickAndMortyResources {
    REQRES_BASE_URL("https://rickandmortyapi.com"),
    REGISTER_SUCCESSFUL_RESOURCE("api/character/2");
    private final String  value;
    RickAndMortyResources(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
