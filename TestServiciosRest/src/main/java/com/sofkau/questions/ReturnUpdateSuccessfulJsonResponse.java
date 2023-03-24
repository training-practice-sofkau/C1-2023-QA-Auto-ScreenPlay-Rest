package com.sofkau.questions;

import com.sofkau.models.ResponseFotos;
import com.sofkau.models.ResponseRegistro;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnUpdateSuccessfulJsonResponse implements Question<ResponseFotos> {
    @Override
    public ResponseFotos answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(ResponseFotos.class);
    }

    public static ReturnUpdateSuccessfulJsonResponse returnUpdateSuccessfulJsonResponse(){
        return new ReturnUpdateSuccessfulJsonResponse();
    }
}
