package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.utils.JsonPlaceHolderResources.JSON_PLACE_HOLDER_BASE_URL;
import static com.sofkau.utils.JsonPlaceHolderResources.DELETE_RESOURCE;
import static com.sofkau.tasks.DoDelete.doDelete;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class DeletePostStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(DeletePostStepDefinition.class);


    @Given("the user is in the json place holder web page")
    public void theUserIsInTheJsonPlaceHolderWebPage() {
        setUp(JSON_PLACE_HOLDER_BASE_URL.getValue());
        LOGGER.info("inicia automatizacion");
    }

    @When("the user sends a request to the Delete service")
    public void theUserSendsARequestToTheDeleteService() {
        actor.attemptsTo(
                doDelete().withTheResource(DELETE_RESOURCE.getValue())
        );

    }

    @Then("the user gets a status code response Ok")
    public void theUserGetsAStatusCodeResponseOk() {
        try {
            actor.should(
                    seeThatResponse(response -> response.statusCode(200))
            );

        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
