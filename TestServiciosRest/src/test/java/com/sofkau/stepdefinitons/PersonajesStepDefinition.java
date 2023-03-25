package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiRickAndMortySetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static com.sofkau.questions.Return_Get.returnPersonajesSuccessfullJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.RickAndMortyResources.RESOURCE;
import static com.sofkau.utils.RickAndMortyResources.RICK_AND_MORTY_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class PersonajesStepDefinition extends ApiRickAndMortySetup {
    public static final Logger LOGGER = Logger.getLogger(PersonajesStepDefinition.class);

    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();


    @Given("el usuario esta en la pagina de busqueda")
    public void elUsuarioEstaEnLaPaginaDeBusqueda() {
        setUp(RICK_AND_MORTY_BASE_URL.getValue());

    }

    @When("cuando el usuario envia solicitud de busqueda  por id {string}")
    public void cuandoElUsuarioEnviaSolicitudDeBusquedaPorId(String id) {
        actor.attemptsTo(doGet().withTheResource(RESOURCE.getValue() + id));
        System.out.println(SerenityRest.lastResponse().body().asString());

    }


    @Then("la pagina retornara un estatus con codigo {int} y nombre {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYNombre(Integer code, String name) {
        try {
            Response actualResponse = returnPersonajesSuccessfullJsonResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, CoreMatchers.notNullValue()),
                    seeThat("Comparar id",
                            iName -> responseBody.get("name").toString(), equalTo(name))
            );
            LOGGER.info("Datos esperados y actuales correctos");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
