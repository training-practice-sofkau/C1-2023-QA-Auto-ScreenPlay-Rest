package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL("https://reqres.in/"),
    CREATE_USERS("api/users"),
    DELETE_PRODUCTS_BASE("https://dummyjson.com/"),
    DELETE_PRODUCTS("products/"),
    CAT_BASE_URI("https://catfact.ninja/fact?max_length=140"),
    REGISTER_SUCCESSFUL_RESOURCE("api/register");



    private final String  value;

    ReqresResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
