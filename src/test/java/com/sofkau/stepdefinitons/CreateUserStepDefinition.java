package com.sofkau.stepdefinitons;

import com.sofkau.models.CreateUser;
import com.sofkau.questions.ReturnCreationValid;
import com.sofkau.tasks.DoPostUserCreate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.sofkau.setup.ApiSetUp;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.path.json.JsonPath;
import static com.sofkau.utils.ReqresResources.CREATE_USERS;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.apache.log4j.Logger;



public class CreateUserStepDefinition extends ApiSetUp{
    public static org.apache.log4j.Logger LOGGER = Logger.getLogger(String.valueOf(CreateUserStepDefinition.class));
 CreateUser create = new CreateUser();

    @Given("the manager is in the API")
    public void theManagerIsInTheAPI() {
        try {
            setUp(REQRES_BASE_URL.getValue());
            LOGGER.info("API set up successful");
        } catch (Exception e) {
            LOGGER.error("API set up failed", e);
        }

    }

    @When("the administrator sends a POST request with a {string} and a {string}")
    public void theAdministratorSendsAPOSTRequestWithAAndA(String name, String job) {
        try {
            create.setName(name);
            create.setJob(job);
            actor.attemptsTo(
                    DoPostUserCreate.doPostUserCreate().withTheResource(CREATE_USERS.getValue())
                            .andTheRequestBody(create)
            );
            LOGGER.info("POST request sent successfully");
        } catch (Exception e) {
            LOGGER.error("POST request failed", e);
        }
    }

    @Then("The admin then sees a status response {int} and the JSON structure")
    public void theAdminThenSeesAStatusResponseAndTheJSONStructure(Integer statusCode) {
        try {
            actor.should(
                    seeThat("el codigo de respuesta ", ReturnCreationValid.was(), equalTo(statusCode))
            );
            LOGGER.info("El codigo de respuesta es: " + statusCode);

            String responseBody = SerenityRest.lastResponse().getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            actor.should(
                    seeThat("El campo 'name' esta presente", value -> jsonPath.getString("name"), notNullValue())
                            .orComplainWith(AssertionError.class, "El campo 'name' no esta presente o es nulo"),
                    seeThat("El campo 'job' esta presente", value -> jsonPath.getString("job"), notNullValue())
                            .orComplainWith(AssertionError.class, "El campo 'job' no esta presente o es nulo"),
                    seeThat("El campo 'id' esta presente", value -> jsonPath.getString("id"), notNullValue())
                            .orComplainWith(AssertionError.class, "El campo 'id' no esta presente o es nulo"),
                    seeThat("El campo 'createdAt' esta presente", value -> jsonPath.getString("createdAt"), notNullValue())
                            .orComplainWith(AssertionError.class, "El campo 'createdAt' no esta presente o es nulo")
            );

            LOGGER.info("La asercion se ha completado correctamente. El codigo de respuesta es: " + statusCode);
        } catch (Exception e) {
            LOGGER.error("Assertion failed", e);
        }
}}
