package com.sofkau.stepdefinitons;

import com.sofkau.models.Response;
import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import com.sofkau.utils.JsonPlaceholder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.REGISTER_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RegisterStepDefinition extends ApiSetUp {
    private User user = new User();
    public static Logger LOGGER = Logger.getLogger(RegisterStepDefinition.class);

    @Given("the user is in the register page")
    public void theUserIsInTheRegisterPage() {
        try{
            setUp(REQRES_BASE_URL.getValue());
        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }

    @When("the user send a registration request with the {string} and the {string}")
    public void theUserSendARegistrationRequestWithTheAndThe(String email, String password) {
        try{
            user.setEmail(email);
            user.setPassword(password);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(REGISTER_SUCCESSFUL_RESOURCE.getValue())
                            .andTheRequestBody(user)
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());
        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }

    @Then("the user see a status {int} response code and an id with a token")
    public void theUserSeeAStatusResponseCodeAndAnIdWithAToken(Integer statusCode) {
        try{
            Response actualResponse= returnRegisterSuccessfulJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
        }catch (AssertionError error){
            LOGGER.warn(error.getMessage());
            Assertions.fail("Respuesta de la petición - inválida");
        }
    }
}