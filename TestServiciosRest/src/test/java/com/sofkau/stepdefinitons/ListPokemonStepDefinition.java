package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;


import static com.sofkau.questions.ReturnListPokemonResponse.returnListPokemonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import org.apache.log4j.Logger;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.equalTo;

public class ListPokemonStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(ListPokemonStepDefinition.class);
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = null;

    @Given("Que estoy en la Poke API")
    public void queEstoyEnLaPokeAPI() {
        setUp(POKEAPI_BASE_URL.getValue());
        LOGGER.info("Inicio de la automatizacion");
    }

    @When("Hago una peticion para listar la info de los pokemones")
    public void hagoUnaPeticionParaListarLaInfoDeLosPokemones() {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(GENERATION1_RESOURCE.getValue())
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("Debo ver un codigo de estado exitoso")
    public void deboVerUnCodigoDeEstadoExitoso() {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(200))

            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("La respuesta debe contener un body con toda la info")
    public void laRespuestaDebeContenerUnBodyConTodaLaInfo() {
        try {
            Response actualResponse = returnListPokemonResponse().answeredBy(actor);
            resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            JSONObject main_region = (JSONObject) resBody.get("main_region");
            actor.should(
                    seeThat("Retorna info",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar valores",
                            id -> resBody.get("id").toString(), equalTo("1")),
                    seeThat("name de la region",
                            name_region ->main_region.get("name"),equalTo("kanto"))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
}
