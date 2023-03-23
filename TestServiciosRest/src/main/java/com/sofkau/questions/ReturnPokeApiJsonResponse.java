package com.sofkau.questions;

import com.sofkau.models.ResponseCreate;
import com.sofkau.models.ResponsePokeApi;
import io.cucumber.java.en_old.Ac;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;



public class ReturnPokeApiJsonResponse implements Question<ResponsePokeApi> {


    @Override
    public ResponsePokeApi answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponsePokeApi.class);
    }

    public static ReturnPokeApiJsonResponse returnPokeApiJsonResponse(){
        return new ReturnPokeApiJsonResponse();
    }
}
