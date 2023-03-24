package com.sofkau.tasks;

import com.sofkau.interactions.OurGet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class DoGetPlaceHolder implements Task {
    private String resource;

    public DoGetPlaceHolder withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurGet.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()

                        )
        );
    }

    public static DoGetPlaceHolder doGet(){
        return new DoGetPlaceHolder();
    }
}
