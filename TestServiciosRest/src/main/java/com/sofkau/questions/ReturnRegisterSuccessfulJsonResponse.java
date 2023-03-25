package com.sofkau.questions;

import com.sofkau.models.ResponseRegistro;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnRegisterSuccessfulJsonResponse implements Question<Response> {
    @Override
    public Response answeredBy(Actor actor) {
        return (Response) SerenityRest.lastResponse().body();
    }

    public static ReturnRegisterSuccessfulJsonResponse returnRegisterSuccessfulJsonResponse(){
        return new ReturnRegisterSuccessfulJsonResponse();
    }
}
