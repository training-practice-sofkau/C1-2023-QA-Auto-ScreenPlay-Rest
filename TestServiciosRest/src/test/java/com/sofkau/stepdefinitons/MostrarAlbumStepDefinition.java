package com.sofkau.stepdefinitons;

import com.sofkau.models.Album;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.Assertions;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnLisAlbumJsonResponse.returnLisAlbumJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.JsonPlaceholder.LIST_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.JsonPlaceholder.REQRES_BASE_URL;

public class MostrarAlbumStepDefinition extends ApiSetUp {
    private Album album = new Album();
    public static Logger LOGGER = Logger.getLogger(RegisterStepDefinition.class);

    @Given("the user is in the list page")
    public void theUserIsInTheListPage() {
        setUp(REQRES_BASE_URL.getValue());
    }

    @When("the user send a list request with the {int}")
    public void theUserSendAListRequestWithThe(Integer id) {
        String resource = LIST_SUCCESSFUL_RESOURCE.getValue();
        resource = resource.replace("@id", id.toString());
        this.album.setId(id);

        actor.attemptsTo(
                doGet()
                        .withTheResource(resource)
        );
        LOGGER.info(SerenityRest.lastResponse().asString());
    }

    @Then("the user see a response with property {string}")
    public void theUserSeeAResponseWithProperty(String title) {
        Album actualResponse= returnLisAlbumJsonResponse().answeredBy(actor);
        Assertions.assertEquals(title, actualResponse.getTitle());
    }
}
