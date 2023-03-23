package com.sofkau.stepdefinitons;

import com.sofkau.models.Admi;
import com.sofkau.models.ResponseAuthentication;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import static com.sofkau.questions.ReturnUserAuthenticationJsonResponse.returnUserAuthenticationJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.RestfulBookerResources.AUTHENTICATION_SUCCESSFUL_RESOURSE;
import static com.sofkau.utils.RestfulBookerResources.RESTFUL_BOOKER_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationStepDefinition extends ApiSetUp {
    private Admi admi = new Admi();
    @Given("the admin is on the authentication page")
    public void theAdminIsOnTheAuthenticationPage() {
        setUp(RESTFUL_BOOKER_BASE_URL.getValue());
    }

    @When("the administrator sends an authentication request with username {string} and password {string}")
    public void theAdministratorSendsAnAuthenticationRequestWithUsernameAndPassword(String username, String password) {
        admi.setUsername(username);
        admi.setPassword(password);
        actor.attemptsTo(
                doPost()
                        .withTheResource(AUTHENTICATION_SUCCESSFUL_RESOURSE.getValue())
                        .andTheRequestBody(admi)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("the administrator should see a status response code {int} and a token")
    public void theAdministratorShouldSeeAStatusResponseCodeAndAToken(Integer statusCode) {
        ResponseAuthentication actualResponseAuthentication = returnUserAuthenticationJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(statusCode)),
                seeThat("Retorna información",
                        act -> actualResponseAuthentication, notNullValue())
        );

    }

}
