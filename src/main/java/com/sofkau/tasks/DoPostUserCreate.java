package com.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DoPostUserCreate implements Task {

    private String resource;
    private Object requestBody;



    public DoPostUserCreate withTheResource(String resource){
        this.resource=resource;
        return this;
    }

    public DoPostUserCreate andTheRequestBody(Object requestBody){
        this.requestBody=requestBody;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(resource).with(requestSpecification -> requestSpecification
                .contentType(ContentType.JSON)
                .body(requestBody)));
    }
    public static DoPostUserCreate doPostUserCreate(){
        return new DoPostUserCreate();
    }
}
