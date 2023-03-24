package com.sofkau.questions;

import com.sofkau.models.ResponseRegistro;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPersonajesSuccessfullJsonResponse implements Question<Response> {
    @Override
    public Response answeredBy(Actor actor) {
        return (Response) SerenityRest.lastResponse().body();
    }

    public static ReturnPersonajesSuccessfullJsonResponse returnPersonajesSuccessfullJsonResponse() {
        return new ReturnPersonajesSuccessfullJsonResponse();
    }
}

