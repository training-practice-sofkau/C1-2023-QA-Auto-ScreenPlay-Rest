package com.sofkau.questions;

import com.sofkau.models.Fruit;
import com.sofkau.models.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import java.lang.reflect.Type;

public class ReturnInformationFruit implements Question<Fruit> {
    @Override
    public Fruit answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getObject("name",Fruit.class);
    }
    public static ReturnInformationFruit returnInformationFruit(){
        return new ReturnInformationFruit();
    };
}
