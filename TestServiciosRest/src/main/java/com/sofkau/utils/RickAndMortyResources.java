package com.sofkau.utils;

public enum RickAndMortyResources {
    RICK_AND_MORTY_BASE_URL("https://rickandmortyapi.com/"),
    RICK_AND_MORTY_SELECCION_ID("api/character/");
    private final String  value;
    RickAndMortyResources(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
