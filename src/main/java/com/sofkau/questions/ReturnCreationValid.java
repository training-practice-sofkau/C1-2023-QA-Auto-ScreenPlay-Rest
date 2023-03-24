package com.sofkau.questions;

import com.sofkau.models.Response;
import io.cucumber.java.sl.In;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnCreationValid implements Question {
    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }

    public static Question<Integer> was(){
        return new ReturnCreationValid();
    }

}
