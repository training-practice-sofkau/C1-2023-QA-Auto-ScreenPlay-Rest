package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;


import static com.sofkau.questions.ReturnGetResponse.returnGetResponse;
import static com.sofkau.tasks.DoGetPlaceHolder.doGet;
import static com.sofkau.utils.JSONPlaceHolder.GET_POSTS;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;


public class GetPostsStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(GetPostsStepDefinition.class.getName());

    @Given("the JSONPlaceholder API is available")
    public void theJSONPlaceholderAPIIsAvailable() {
        try {
            setUp(PLACE_HOLDER_BASE_URL.getValue());
            LOGGER.info("Se inicio la automatizacion en la URL: " + PLACE_HOLDER_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.error("Error al iniciar la automatizacion : Detalles: "+ e.getMessage());
            actor.should(
                    seeThatResponse("El servidor no está disponible",
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
        }

    }

    @When("I make a GET request by {string}")
    public void iMakeAGETRequestBy(String id) {

        try {
            LOGGER.info("Realizando peticion GET...");

            actor.attemptsTo(
                    doGet().withTheResource(GET_POSTS.getValue() + id)
            );

            System.out.println(SerenityRest.lastResponse().body().asString());
        } catch (Exception e) {
            LOGGER.error("Error durante GET request: " + e.getMessage());
        }
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer code) throws ParseException {

        try {
            Response actualResponse = returnGetResponse().answeredBy(actor);

            actor.should(
                    seeThatResponse("El codigo de respuesta deberia ser: " + code,
                            response -> response.statusCode(code)),
                    seeThat("Retorna informacion",
                            act -> actualResponse, notNullValue())
            );

            LOGGER.info("Respuesta status code: " + code);
        } catch (Exception e) {
            LOGGER.error("Error: ", e);
            throw e;
        }

    }

}
