package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteAnAlbumStepDef extends ApiSetUp {

    @Given("I have access to JSONPlaceholder API server")
    public void iHaveAccessToJSONPlaceholderAPIServer() {
    }

    @When("I try to delete an album with id {int}")
    public void iTryToDeleteAnAlbumWithId(Integer id) {
    }

    @Then("I will see a response code {int}")
    public void iWillSeeAResponseCode(Integer code) {
    }
}