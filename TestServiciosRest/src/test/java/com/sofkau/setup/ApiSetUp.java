package com.sofkau.setup;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import static com.sofkau.utils.Log4jValues.LOG4J_PROPERTY_PATH;
import org.apache.log4j.PropertyConfigurator;

public class ApiSetUp {
    protected Actor actor = new Actor("Juanes");

    protected void setUp(String urlBase){
        setUpLog4j();
        actorCallAnApi(urlBase);
    }

    private void actorCallAnApi(String urlBase){
        actor.can(CallAnApi.at(urlBase));
    }

    private void setUpLog4j(){
        PropertyConfigurator.configure(LOG4J_PROPERTY_PATH.getValue());
    }
}
