package com.sofkau.models;

public class Fotos {
    private String albumId;
    private String titulo;

    public Fotos() {
    }

    public Fotos(String albumId, String titulo) {
        this.albumId = albumId;
        this.titulo = titulo;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
