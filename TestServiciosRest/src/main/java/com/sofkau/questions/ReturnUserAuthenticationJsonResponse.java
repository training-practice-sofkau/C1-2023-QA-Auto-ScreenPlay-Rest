package com.sofkau.questions;

import com.sofkau.models.Response;
import com.sofkau.models.ResponseAuthentication;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnUserAuthenticationJsonResponse implements Question<ResponseAuthentication> {
    @Override
    public ResponseAuthentication answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseAuthentication.class);
    }

    public static ReturnUserAuthenticationJsonResponse returnUserAuthenticationJsonResponse() {
        return new ReturnUserAuthenticationJsonResponse();
    }
}
