package com.sofkau.tasks;

import com.sofkau.interactions.OurGet;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.sofkau.utils.ProductResources.PRODUCT_SUCCESSFUL_RESOURCES;

public class DoGetGame implements Task {

    private String resource;
    private Object requestBody;

    public DoGetGame withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoGetGame andTheRequestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurGet.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                        )

        );
    }

    public static DoGetGame doGetGame() {
        return new DoGetGame();
    }


}
