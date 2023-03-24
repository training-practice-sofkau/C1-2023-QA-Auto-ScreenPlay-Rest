package com.sofkau.questions;

import com.sofkau.models.Album;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnLisAlbumJsonResponse implements Question<Album> {
    @Override
    public Album answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Album.class);
    }

    public static ReturnLisAlbumJsonResponse returnLisAlbumJsonResponse(){
        return new ReturnLisAlbumJsonResponse();
    }
}