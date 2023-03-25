package com.sofkau.interactions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class OurPatch extends RestInteraction {
    private final String  resource;
    public  OurPatch(String resource){
        this.resource=resource;
    }
    @Step("{0} executes a PATCH on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().log().all().patch(as(actor).resolve(resource)).then().log().all();

    }
    public static OurPatch to(String resource){
        return instrumented(OurPatch.class,resource);
    }
}
