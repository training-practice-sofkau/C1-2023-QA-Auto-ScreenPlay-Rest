package com.sofkau.stepdefinitons;

import com.sofkau.models.Posts;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.JSONPlaceHolder.GET_POSTS;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;


public class PostPostsStepDefinition extends ApiSetUp {

    Posts posts = new Posts();
    @Given("I am on the JSONPlaceholder API")
    public void iAmOnTheJSONPlaceholderAPI() {
        setUp(PLACE_HOLDER_BASE_URL.getValue());
    }

    @When("I create a new post with the {string}, {string} and the {int}")
    public void iCreateANewPostWithTheAndThe(String title, String body, Integer userId)  {

        posts.setTitle(title);
        posts.setBody(body);
        posts.setUserId(userId);

        actor.attemptsTo(

                doPost()
                        .withResource(GET_POSTS.getValue())
                        .andTheRequestBody(posts)
        );


    }
    @Then("the response status code should be displayed as {int}")
    public void theResponseStatusCodeShouldBeDisplayedAs(Integer code) {

    }

    @Then("the response should contain the new post")
    public void theResponseShouldContainTheNewPost() {

    }


}
