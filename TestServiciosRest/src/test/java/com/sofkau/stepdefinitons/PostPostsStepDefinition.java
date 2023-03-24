package com.sofkau.stepdefinitons;

import com.sofkau.models.Posts;
import com.sofkau.models.ResponseRegister;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;


import static com.sofkau.questions.ReturnPostResponse.returnPostResponse;
import static com.sofkau.questions.ReturnPutResponse.returnPutResponse;
import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.JSONPlaceHolder.GET_POSTS;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


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

        try {
            actor.attemptsTo(

                    doPost()
                            .withTheResource(GET_POSTS.getValue())
                            .andTheRequestBody(posts)
            );
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Then("the response status code should be displayed as {int}")
    public void theResponseStatusCodeShouldBeDisplayedAs(Integer code) {

       actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(code))
        );

    }

    @Then("the response should contain the new post")
    public void theResponseShouldContainTheNewPost() {
        actor.should(
                seeThatResponse("El post creado se encuentra en la respuesta",
                        response -> response.body("title", equalTo(posts.getTitle()))
                                .body("body", equalTo(posts.getBody()))
                                .body("userId", equalTo(posts.getUserId()))
                )
        );

    }


}
