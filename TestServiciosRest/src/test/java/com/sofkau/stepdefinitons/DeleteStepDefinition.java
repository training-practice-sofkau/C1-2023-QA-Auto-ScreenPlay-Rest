package com.sofkau.stepdefinitons;

import com.sofkau.models.Album;
import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.JsonPlaceholderDelete.DELETE_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.JsonPlaceholderDelete.REQRES_BASE_URL_PLACEHOLDER;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteStepDefinition extends ApiSetUp {
    private Album album = new Album();
    public static Logger LOGGER = Logger.getLogger(RegisterStepDefinition.class);

    @Given("the user is in the delete page")
    public void theUserIsInTheDeletePage() {
        setUp(REQRES_BASE_URL_PLACEHOLDER.getValue());
    }

    @When("the user send a Delete request with the {int}")
    public void theUserSendADeleteRequestWithThe(Integer id) {
        String resource = DELETE_SUCCESSFUL_RESOURCE.getValue();
        resource = resource.replace("@id", id.toString());
        this.album.setId(id);

        actor.attemptsTo(
                doDelete()
                        .withTheResource(resource)
        );
        LOGGER.info(SerenityRest.lastResponse().asString());
    }

    @Then("the user see a response with property title that was removed and a correct status code {int}")
    public void theUserSeeAResponseWithPropertyTitleThatWasRemovedAndACorrectStatusCode(Integer statusCode) {
        Response actualResponse= returnRegisterSuccessfulJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(statusCode))
        );
    }
}