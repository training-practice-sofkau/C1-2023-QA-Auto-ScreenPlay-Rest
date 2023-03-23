package com.sofkau.models;

import lombok.Data;

@Data
public class Response {
    private Integer id;
    private String token;
    private String error;
}