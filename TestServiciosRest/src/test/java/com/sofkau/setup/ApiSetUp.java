package com.sofkau.setup;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class ApiSetUp {
    protected Actor actor = new Actor("Juanes");

    protected void setUp(String urlBase) {
        actorCallAnApi(urlBase);
    }

    private void actorCallAnApi(String urlBase) {
        actor.can(CallAnApi.at(urlBase));
    }
}