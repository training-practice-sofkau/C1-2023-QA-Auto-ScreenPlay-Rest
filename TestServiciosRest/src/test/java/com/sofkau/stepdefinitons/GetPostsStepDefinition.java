package com.sofkau.stepdefinitons;

import com.sofkau.models.Posts;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;



import static com.sofkau.questions.ReturnGetResponse.returnGetResponse;
import static com.sofkau.tasks.DoGetPlaceHolder.doGet;
import static com.sofkau.utils.JSONPlaceHolder.GET_POSTS;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetPostsStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(GetPostsStepDefinition.class.getName());

    Posts posts = new Posts();

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

            posts.setId(id);

            actor.attemptsTo(
                    doGet().withTheResource(GET_POSTS.getValue() + id)
            );

            System.out.println(lastResponse().body().asString());
        } catch (Exception e) {
            LOGGER.error("Error durante GET request: " + e.getMessage());
        }
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer code) throws ParseException {


        try {
            /**
             * En este caso, se llama al método returnGetResponse() que devuelve una solicitud HTTP GET.
             * Luego, se llama al método answeredBy(actor) que envía la solicitud HTTP y devuelve la respuesta como un objeto Response.
             */

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
