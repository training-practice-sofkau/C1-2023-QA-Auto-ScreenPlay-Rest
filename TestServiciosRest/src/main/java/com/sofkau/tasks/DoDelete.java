package com.sofkau.tasks;

import com.sofkau.interactions.OurDelete;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DoDelete implements Task {
    private String resource;

    public DoDelete withTheResource(String resource){
    this.resource=resource;
    return this;
}


    @Override
    public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(

            OurDelete.to(resource)
                    .with(
                            requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                    .contentType(ContentType.JSON)
                    )
    );

    }


    public static DoDelete doDelete(){
        return new DoDelete();
    }
}
