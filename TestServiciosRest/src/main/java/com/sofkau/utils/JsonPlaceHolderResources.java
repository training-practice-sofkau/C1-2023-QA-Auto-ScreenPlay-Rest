package com.sofkau.utils;

public enum JsonPlaceHolderResources {

    JSONPLACE_BASE_URL("https://jsonplaceholder.typicode.com/"),
    POSTS_BY_ID_RESOURCE("posts/"),

    COMMENTS_BY_POST("comments?postId=");

    private final String value;

    JsonPlaceHolderResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
