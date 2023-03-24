package com.sofkau.questions;

import com.sofkau.models.Pokemons;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetPokemons implements Question {
    @Override
    public Pokemons answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Pokemons.class);
    }
}
