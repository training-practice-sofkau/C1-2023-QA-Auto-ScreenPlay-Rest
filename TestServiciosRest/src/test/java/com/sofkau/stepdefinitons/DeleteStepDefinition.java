package com.sofkau.stepdefinitons;

import com.sofkau.models.Album;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.JsonPlaceholder.LIST_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.JsonPlaceholder.REQRES_BASE_URL;

public class DeleteStepDefinition extends ApiSetUp {
    private Album album = new Album();

    @Given("the user is in the delete page")
    public void theUserIsInTheDeletePage() {
        setUp(REQRES_BASE_URL.getValue());
    }

    @When("the user send a Delete request with the {int}")
    public void theUserSendADeleteRequestWithThe(Integer id) {
        String resource = LIST_SUCCESSFUL_RESOURCE.getValue();
        resource = resource.replace("@id", id.toString());
        this.album.setId(id);

        actor.attemptsTo(
                doDelete()
                        .withTheResource(resource)
        );
        System.out.println(SerenityRest.lastResponse().asString());
    }

    @Then("the user see a response with property {string} that was removed")
    public void theUserSeeAResponseWithPropertyThatWasRemoved(String string) {

    }
}
