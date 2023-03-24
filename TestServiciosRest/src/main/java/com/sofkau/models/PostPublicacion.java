package com.sofkau.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostPublicacion {

    private String titulo;

    private String cuerpo;

    private Integer userId;

    public String getTitulo() {
        return titulo;
    }

    public PostPublicacion setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public PostPublicacion setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public PostPublicacion setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}

