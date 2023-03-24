package com.sofkau.stepdefinitons;

import com.sofkau.setup.APISetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.Constants.JSON_PLACEHOLDER_BASE_URL;
import static com.sofkau.utils.Constants.TO_DO_RESOURCE;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.hasItem;

public class SeeTODOStepDef extends APISetup {
    private final Logger log = LoggerFactory.getLogger(DeleteAnAlbumStepDef.class);

    @Given("I have access to the JSONPlaceholder API server")
    public void iHaveAccessToTheJSONPlaceholderAPIServer() {
        try {
            log.info("Init scenario");
            setUp(JSON_PLACEHOLDER_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Wrong Setup provided");
        }
    }

    @When("I check my to-do tasks with id {int}")
    public void iCheckMyToDoTasksWithId(Integer id) {
        try {
            log.info("Running selection");
            actor.attemptsTo(
                    doGet().theResource(String.format(TO_DO_RESOURCE.getValue(), id))
            );
        } catch (Exception e) {
            log.error("Wrong Setup provided");
        }
    }

    @Then("I will see the title {string} of the task")
    public void iWillSeeTheCompletedStatusOfTheTask(String title) {
        try {
            actor.should(
                    seeThatResponse("Title of the to-do should be shown",
                            response -> response.body("title", hasItem(title)))
            );
            log.info("First assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            Assertions.fail();
        }
    }

    @And("I will receive a status code {int}")
    public void iWillReceiveAStatusCode(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Status code of To do consulted should be shown",
                            response -> response.statusCode(code))
            );
            log.info("Second assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            Assertions.fail();
        } finally {
            log.error("Test completed");
        }
    }
}