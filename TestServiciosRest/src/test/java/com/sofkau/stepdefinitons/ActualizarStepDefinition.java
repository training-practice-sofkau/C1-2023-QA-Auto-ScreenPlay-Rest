package com.sofkau.stepdefinitons;

import com.sofkau.models.Todos;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnActualizarJsonResponse.returnActualizarJsonResponse;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.JsonPlaceholderPut.PUT_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.JsonPlaceholderPut.REQRES_BASE_URL_JSON;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ActualizarStepDefinition extends ApiSetUp {
    private Todos todos = new Todos();
    public static Logger LOGGER = Logger.getLogger(RegisterStepDefinition.class);

    @Given("the user is in the update page")
    public void theUserIsInTheUpdatePage() {
        setUp(REQRES_BASE_URL_JSON.getValue());
    }

    @When("the user send a update request with the {int} the {string} and the {string}")
    public void theUserSendAUpdateRequestWithTheTheAndThe(Integer id, String title, String completed) {
        String resource = PUT_SUCCESSFUL_RESOURCE.getValue();
        resource = resource.replace("@id", id.toString());
        this.todos.setId(id);

        todos.setTitle(title);
        todos.setCompleted(completed);
        actor.attemptsTo(
                doPut()
                        .withTheResource(resource)
                        .andTheRequestBody(todos)
        );
        LOGGER.info(SerenityRest.lastResponse().body().asString());
    }

    @Then("the user see a status {int} response code")
    public void theUserSeeAStatusResponseCode(Integer statusCode) {
        Todos actualResponse= returnActualizarJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(statusCode)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );
    }
}
