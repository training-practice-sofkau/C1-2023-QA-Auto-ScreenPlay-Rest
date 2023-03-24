package com.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DoGetPokemon implements Task {

    private final String page;

    public DoGetPokemon(String page) {
        this.page = page;
    }

    public static Performable fromPage(String page) {
        return instrumented(DoGetPokemon.class, page);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/v2" + page)
                        .with(requestSpecification
                                -> requestSpecification.contentType(ContentType.JSON)
                                .header("header1", "value1")
                        )
        );
    }
}