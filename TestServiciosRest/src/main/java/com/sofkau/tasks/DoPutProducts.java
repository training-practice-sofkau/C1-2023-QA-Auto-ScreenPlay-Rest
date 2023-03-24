package com.sofkau.tasks;

import com.sofkau.interactions.OurPost;
import com.sofkau.interactions.OurPut;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class DoPutProducts implements Task {


    private String resource;
    private Object requestBody;


    public DoPutProducts withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoPutProducts andTheRequestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurPut.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(requestBody)
                        )
        );
    }


    public static DoPutProducts doPutProducts() {
        return new DoPutProducts();
    }
}
