package com.sofkau.stepdefinitons;

import com.sofkau.models.Response;
import com.sofkau.models.Todos;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.JsonPlaceholder.LIST_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REGISTER_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ActualizarStepDefinition extends ApiSetUp {
    private Todos todos = new Todos();

    @Given("the user is in the update page")
    public void theUserIsInTheUpdatePage() {
        setUp(REQRES_BASE_URL.getValue());
    }

    @When("the user send a update request with the {int} the {string} and the {string}")
    public void theUserSendAUpdateRequestWithTheTheAndThe(Integer id, String title, String completed) {
        String resource = LIST_SUCCESSFUL_RESOURCE.getValue();
        resource = resource.replace("@id", id.toString());
        this.todos.setId(id);

        todos.setTitle(title);
        todos.setCompleted(completed);
        actor.attemptsTo(
                doPut()
                        .withTheResource(REGISTER_SUCCESSFUL_RESOURCE.getValue())
                        .andTheRequestBody(todos)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("the user see a status {int} response code")
    public void theUserSeeAStatusResponseCode(Integer statusCode) {
        Response actualResponse= returnRegisterSuccessfulJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(statusCode)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );
    }
}
