package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;


import static com.sofkau.questions.ReturnGetResponse.returnGetResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.JSONPlaceHolder.GET_POSTS;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;


public class GetPostsStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(GetPostsStepDefinition.class.getName());

    @Given("the JSONPlaceholder API is available")
    public void theJSONPlaceholderAPIIsAvailable() {
        setUp(PLACE_HOLDER_BASE_URL.getValue());

    }

    @When("I make a GET request by {string}")
    public void iMakeAGETRequestBy(String id) {

        actor.attemptsTo(
                doGet().withTheResource(GET_POSTS.getValue() + id)
        );

        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer code) throws ParseException {

        Response actualResponse = returnGetResponse().answeredBy(actor);

        actor.should(
                seeThatResponse("the response code should be " + code,
                        response -> response.statusCode(code)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );
       // LOGGER.info("Response status code: " + code);

    }

}
