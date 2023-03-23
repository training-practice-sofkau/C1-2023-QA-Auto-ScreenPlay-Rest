package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SeeTODOStepDef extends ApiSetUp {

    @Given("I have access to the JSONPlaceholder API server")
    public void iHaveAccessToTheJSONPlaceholderAPIServer() {
    }

    @When("I check my to-do tasks with id {int}")
    public void iCheckMyToDoTasksWithId(Integer id) {
    }

    @Then("I will see the completed status {string} of the task")
    public void iWillSeeTheCompletedStatusOfTheTask(String status) {
    }

    @And("I will receive a status code {int}")
    public void iWillReceiveAStatusCode(Integer code) {
    }
}