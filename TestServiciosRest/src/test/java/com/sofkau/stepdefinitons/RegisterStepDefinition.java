package com.sofkau.stepdefinitons;

import com.sofkau.models.Response;
import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.REGISTER_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;


public class RegisterStepDefinition extends ApiSetUp {
    private User user = new User();

    String bodyPrueba = "{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"pistol\"\n" +
            "}";

    @Given("the user is in the register page")
    public void theUserIsInTheRegisterPage() {

        setUp(REQRES_BASE_URL.getValue());
    }

    @When("the user send a registration request with the {string} and the {string}")
    public void theUserSendARegistrationRequestWithTheAndThe(String email, String password) {
        user.setEmail(email);
        user.setPassword(password);
        actor.attemptsTo(
                doPost()
                        .withTheResource(REGISTER_SUCCESSFUL_RESOURCE.getValue())
                        .andTheRequestBody(user)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());

    }

    @Then("the user see a status {int} response code and an id with a token")
    public void theUserSeeAStatusResponseCodeAndAnIdWithAToken(Integer statusCode) {
        Response actualResponse= returnRegisterSuccessfulJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(statusCode)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );

    }

}
