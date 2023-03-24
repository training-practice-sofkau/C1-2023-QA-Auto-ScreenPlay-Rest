package com.sofkau.tasks;

import com.sofkau.models.PutPostRequest;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class PutPostTask implements Task {
    private String resource;
    private PutPostRequest requestBody;

    public PutPostTask withResource(String resource) {
        this.resource = resource;
        return this;
    }

    public PutPostTask andRequestBody(PutPostRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(resource)
                        .with(
                                request -> request.contentType(ContentType.JSON)
                                        .body(requestBody)
                        )
        );
    }

    public static PutPostTask putPost() {
        return new PutPostTask();
    }
}
