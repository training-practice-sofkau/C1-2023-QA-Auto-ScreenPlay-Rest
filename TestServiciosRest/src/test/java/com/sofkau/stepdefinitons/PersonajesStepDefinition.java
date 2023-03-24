package com.sofkau.stepdefinitons;

import com.sofkau.models.ResponsePersonajes;
import com.sofkau.setup.ApiRickAndMortySetup;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.parser.JSONParser;
import static com.sofkau.questions.ReturnPersonajesSuccessfullJsonResponse.returnPersonajesSuccessfullJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.RickAndMortyResources.RESOURCE;
import static com.sofkau.utils.RickAndMortyResources.RICK_AND_MORTY_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;


public class PersonajesStepDefinition extends ApiRickAndMortySetup {
    public static final Logger LOGGER = Logger.getLogger(PersonajesStepDefinition.class);

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
    public void laPaginaRetornaraUnEstatusConCodigoYNombre(Integer code, String nombre) {
        Response actualResponse = returnPersonajesSuccessfullJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(code)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())


        );
    }
}
