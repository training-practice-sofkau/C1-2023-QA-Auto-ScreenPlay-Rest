package com.sofkau.questions;

import com.sofkau.models.Todos;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnActualizarJsonResponse implements Question<Todos> {
    @Override
    public Todos answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Todos.class);
    }

    public static ReturnActualizarJsonResponse returnActualizarJsonResponse(){
        return new ReturnActualizarJsonResponse();
    }
}