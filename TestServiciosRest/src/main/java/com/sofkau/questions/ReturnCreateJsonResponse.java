package com.sofkau.questions;


import com.sofkau.models.ResponseCreate;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnCreateJsonResponse implements Question<ResponseCreate> {
    @Override
    public ResponseCreate answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseCreate.class);
    }

    public static ReturnCreateJsonResponse returnCreateJsonResponse(){
        return new ReturnCreateJsonResponse();
    }
}
