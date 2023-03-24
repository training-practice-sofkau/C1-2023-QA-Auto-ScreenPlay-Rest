package com.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import java.util.HashMap;

public class DoDelete implements Task {
    private String resource;
    private HashMap<String, Object> headers = new HashMap<>();

    public DoDelete usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoDelete withHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(resource)
                        .with(requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                .contentType(ContentType.JSON)
                                .headers(headers)
                        )
        );
    }

    public static DoDelete doDelete () { return new DoDelete() ;}
}

