package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiRickAndMortySetup;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.RickAndMortyResources.RESOURCE;
import static com.sofkau.utils.RickAndMortyResources.RICK_AND_MORTY_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonajesStepDefinition extends ApiRickAndMortySetup {
    public static final Logger LOGGER = Logger.getLogger(PersonajesStepDefinition.class);
    private Response response;
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;
    @Given("el usuario esta en la pagina de busqueda")
    public void elUsuarioEstaEnLaPaginaDeBusqueda() {
        setUp(RICK_AND_MORTY_BASE_URL.getValue());

    }

    @When("cuando el usuario envia solicitud de busqueda  por id {string}")
    public void cuandoElUsuarioEnviaSolicitudDeBusquedaPorId(String id) {
        actor.attemptsTo(doGet().withTheResource(RESOURCE.getValue() + id));
        System.out.println(SerenityRest.lastResponse().body().asString());

    }

    @Then("la pagina retornara un estatus con codigo {string} y nombre {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYNombre(String id, String code) throws ParseException {









    }
}
