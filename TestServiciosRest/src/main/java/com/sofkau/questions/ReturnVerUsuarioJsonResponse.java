package com.sofkau.questions;

import com.sofkau.models.Response;
import com.sofkau.models.ResponseVerUsuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnVerUsuarioJsonResponse implements Question<ResponseVerUsuario> {
    @Override
    public ResponseVerUsuario answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseVerUsuario.class);
    }
    public static ReturnVerUsuarioJsonResponse returnVerUsuarioJsonResponse(){
        return new ReturnVerUsuarioJsonResponse();
    }


}
