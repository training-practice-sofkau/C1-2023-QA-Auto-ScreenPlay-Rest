package com.sofkau.questions;

import com.sofkau.models.ResponseRegister;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnConsultaSuccessfulJsonResponse implements Question<Response> {
    @Override
    public Response answeredBy(Actor actor) {
        return (Response) SerenityRest.lastResponse().body();
    }
    public static ReturnConsultaSuccessfulJsonResponse returnConsultaSuccessfulJsonResponse(){
        return new ReturnConsultaSuccessfulJsonResponse();
    }
}
