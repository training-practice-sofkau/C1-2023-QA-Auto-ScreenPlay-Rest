package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateABookStepDef extends ApiSetUp {

    @Given("I have access to Fake REST API server")
    public void iHaveAccessToFakeRESTAPIServer() {
    }

    @When("I try to create a book with id {int}, title {string} and page count {int}")
    public void iTryToCreateABookWithIdTitleAndPageCount(Integer id, String title, Integer pageCount) {
    }

    @Then("I will see the response code {int}")
    public void iWillSeeTheResponseCode(Integer code) {
    }

    @And("I will receive the book info back")
    public void iWillReceiveTheBookInfoBack() {
    }
}