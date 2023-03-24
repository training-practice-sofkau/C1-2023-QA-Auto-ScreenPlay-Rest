package com.sofkau.stepdefinitons;

import com.sofkau.models.Post;
import com.sofkau.models.PutPostRequest;
import com.sofkau.setup.ApiSetUp;
import com.sofkau.tasks.PutPostTask;
import com.sofkau.utils.JsonPlaceholderResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static com.google.common.base.Predicates.equalTo;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

public class UpdatePostStepDefinition extends ApiSetUp {
    private Post originalPost;
    private PutPostRequest putPostRequest;

    @Given("a post already exists in the system")
    public void aPostAlreadyExistsInTheSystem() {
        setUp(JsonPlaceholderResources.BASE_URL.getValue());
        Response response = SerenityRest.when().get(JsonPlaceholderResources.PUT_POST_RESOURCE.getValue());
        originalPost = response.as(Post.class);
    }

    @When("the user updates the post with new information")
    public void theUserUpdatesThePostWithNewInformation() {
        putPostRequest = new PutPostRequest(originalPost);
        putPostRequest.setTitle("New Title");
        putPostRequest.setBody("New Body");
        actor.attemptsTo(
                PutPostTask.putPost()
                        .withResource(JsonPlaceholderResources.PUT_POST_RESOURCE.getValue())
                        .andRequestBody(putPostRequest)
        );
    }

    @Then("the post should be updated successfully")
    public void thePostShouldBeUpdatedSuccessfully() {
        Response response = SerenityRest.lastResponse();
        actor.should(
                seeThat("Response status code is 200",
                        act -> response.getStatusCode(),
                        equalTo(200)),
                seeThat("Response body contains updated post",
                        act -> response.asString(),
                        containsString("New Title")),
                seeThat("Response body contains updated post",
                        act -> response.asString(),
                        containsString("New Body"))
        );
    }
}