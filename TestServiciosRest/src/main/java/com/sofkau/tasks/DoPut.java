package com.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class DoPut implements Task {
    private String resource;
    private Object requestBody;
    public DoPut withTheResource(String resource){
        this.resource=resource;
        return this;
    }

    public DoPut andTheRequestBody(Object requestBody){
        this.requestBody=requestBody;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(requestBody)
                        )
        );

    }
    public static DoPut doPut(){
        return new DoPut();
    }
}
