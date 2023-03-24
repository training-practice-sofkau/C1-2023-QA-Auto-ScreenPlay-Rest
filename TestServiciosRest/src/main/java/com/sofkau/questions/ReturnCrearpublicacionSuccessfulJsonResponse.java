package com.sofkau.questions;

import com.sofkau.models.PostPublicacion;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnCrearpublicacionSuccessfulJsonResponse implements Question<PostPublicacion> {


    @Override
    public PostPublicacion answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(PostPublicacion.class);
    }

    public static ReturnCrearpublicacionSuccessfulJsonResponse returnCrearpublicacionSuccessfulJsonResponse(){
        return new ReturnCrearpublicacionSuccessfulJsonResponse();
    }
}
