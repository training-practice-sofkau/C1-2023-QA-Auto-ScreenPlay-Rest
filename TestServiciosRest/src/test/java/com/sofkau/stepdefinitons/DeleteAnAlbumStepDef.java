package com.sofkau.stepdefinitons;

import com.sofkau.setup.APISetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.Constants.ALBUMS_RESOURCE;
import static com.sofkau.utils.Constants.JSON_PLACEHOLDER_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteAnAlbumStepDef extends APISetup {
    private final Logger log = LoggerFactory.getLogger(DeleteAnAlbumStepDef.class);

    @Given("I have access to JSONPlaceholder API server")
    public void iHaveAccessToJSONPlaceholderAPIServer() {
        try {
            log.info("Init scenario");
            setUp(JSON_PLACEHOLDER_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Wrong Setup provided");
        }
    }

    @When("I try to delete an album with id {int}")
    public void iTryToDeleteAnAlbumWithId(Integer id) {
        try {
            log.info("Running selection");
            actor.attemptsTo(
                    doDelete().theResource(String.format(ALBUMS_RESOURCE.getValue(), id))
            );
        } catch (Exception e) {
            log.error("Wrong Setup provided");
        }
    }

    @Then("I will see a response code {int}")
    public void iWillSeeAResponseCode(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Status code cause deleted Album should be shown",
                            response -> response.statusCode(code))
            );
            log.info("Test passed");
        } catch (Exception e) {
            log.error("Test failed");
            Assertions.fail();
        } finally {
            log.error("Test completed");
        }
    }
}