package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.junit.annotations.TestData;

import static com.sofkau.utils.ListFruitsResources.LIST_FRUITS_RESOURCES;
import static io.restassured.http.ContentType.JSON;

public class DoGetListFruit implements Task {
    private Object resource;

    public DoGetListFruit withTheResource(Object resource){
        this.resource=resource;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(LIST_FRUITS_RESOURCES.getValue() + resource).with(resource->resource.contentType(JSON))
        );
    }
    public static DoGetListFruit doGetListFruit(){
        return new DoGetListFruit();
    }
}
