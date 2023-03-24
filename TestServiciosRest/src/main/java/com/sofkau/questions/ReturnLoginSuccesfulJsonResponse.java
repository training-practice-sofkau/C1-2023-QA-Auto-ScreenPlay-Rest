package com.sofkau.questions;

import com.sofkau.models.ResponseLogin;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnLoginSuccesfulJsonResponse implements Question<ResponseLogin> {

    @Override
    public ResponseLogin answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseLogin.class);
    }

    public static ReturnLoginSuccesfulJsonResponse returnLoginSuccesfulJsonResponse(){
        return new ReturnLoginSuccesfulJsonResponse();
    }
}
