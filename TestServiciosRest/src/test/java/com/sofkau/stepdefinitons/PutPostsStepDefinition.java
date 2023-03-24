package com.sofkau.stepdefinitons;

import com.sofkau.models.Posts;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnPutResponse.returnPutResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.JSONPlaceHolder.GET_POSTS;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PutPostsStepDefinition extends ApiSetUp {

    private Posts posts = new Posts();

    @Given("the JSONPlaceholder API is available to use")
    public void theJSONPlaceholderAPIIsAvailableToUse() {
        setUp(PLACE_HOLDER_BASE_URL.getValue());

    }
    @When("I make a PUT request to update the post with {string}, {string}, {string}, {int}")
    public void iMakeAPUTRequestToUpdateThePostWith(String id, String title, String body, Integer userId) {
        posts.setId(id);
        posts.setTitle(title);
        posts.setBody(body);
        posts.setUserId(userId);

        actor.attemptsTo(

                doPut()
                        .withResource(GET_POSTS.getValue() + id)
                        .andTheRequestBody(posts)
        );
    }

    @Then("the response status code that should be received is {int}")
    public void theResponseStatusCodeThatShouldBeReceivedIs(Integer code) {

        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(code))
        );
    }

    @Then("the response body should contain the updated post details")
    public void theResponseBodyShouldContainTheUpdatedPostDetails() {
        Response actualResponsePut = returnPutResponse().answeredBy(actor);
        actor.should(
                seeThat("Retorna información",
                        act -> actualResponsePut, notNullValue())
        );

    }

}
