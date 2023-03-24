package com.sofkau.stepdefinitons;

import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.GET_RESOURCE;
import static com.sofkau.utils.ReqresResources.JSON_PLACE_HOLDER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
//import static net.serenitybdd.screenplay.rest.questions.ResponseQuestion.*;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetStepDefinition extends ApiSetUp{
    @Given("the user is in the JSON place holder page")
    public void theUserIsInTheJSONPlaceHolderPage() {
        setUp(JSON_PLACE_HOLDER.getValue());
    }

    @When("the user sends the {int} that he wants to get")
    public void theUserSendsTheThatHeWantsToGet(Integer int1) {
        actor.attemptsTo(
                doGet().withTheResource(GET_RESOURCE.getValue())
                        .andWithTheNumber(int1+"")
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("the user sees a status {int} and the post he wants")
    public void theUserSeesAStatusAndThePostHeWants(Integer int1) {
        //System.out.println(ResponseQuestion.named);

    }
}
