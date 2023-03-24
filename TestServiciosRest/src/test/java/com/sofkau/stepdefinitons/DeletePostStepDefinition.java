package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.JsonPlaceholderResources.DELETE_FIRST_POST_RESOURCE;
import static com.sofkau.utils.JsonPlaceholderResources.JSON_PLACEHOLDER_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeletePostStepDefinition extends ApiSetUp {
    @Given("that the user is logged into the application")
    public void thatTheUserIsLoggedIntoTheApplication() {
        setUp(JSON_PLACEHOLDER_BASE_URL.getValue());
    }

    @When("the user sends a request to remove the post")
    public void theUserSendsARequestToRemoveThePost() {
        actor.attemptsTo(
                doDelete()
                        .withTheResource(DELETE_FIRST_POST_RESOURCE.getValue())
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("the user should see a status response code {int}")
    public void theUserShouldSeeAStatusResponseCode(Integer statusCode) {
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(statusCode))
        );
    }


}
