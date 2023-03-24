package com.sofkau.tasks;

import com.sofkau.interactions.OurPost;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class DoPostProducts implements Task {

    private String resource;
    private Object requestBody;


    public DoPostProducts withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoPostProducts andTheRequestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurPost.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(requestBody)
                        )
        );
    }


    public static DoPostProducts doPostProducts() {
        return new DoPostProducts();
    }
}
