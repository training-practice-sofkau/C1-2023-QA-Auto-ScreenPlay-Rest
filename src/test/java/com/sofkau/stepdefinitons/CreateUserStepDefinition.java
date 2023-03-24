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
import static net.bytebuddy.matcher.ElementMatchers.is;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.apache.log4j.Logger;



public class CreateUserStepDefinition extends ApiSetUp{
    public static org.apache.log4j.Logger LOGGER = Logger.getLogger(String.valueOf(CreateUserStepDefinition.class));
 CreateUser create = new CreateUser();

    @Given("the manager is in the API")
    public void theManagerIsInTheAPI() {
        setUp(REQRES_BASE_URL.getValue());

    }

    @When("the administrator sends a POST request with a {string} and a {string}")
    public void theAdministratorSendsAPOSTRequestWithAAndA(String name, String job) {
     create.setName(name);
     create.setJob(job);
     actor.attemptsTo(
             DoPostUserCreate.doPostUserCreate().withTheResource(CREATE_USERS.getValue())
                     .andTheRequestBody(create)
     );
     //System.out.println(SerenityRest.lastResponse().body().asString());

    }

    @Then("The admin then sees a status response {int} and the JSON structure")
    public void theAdminThenSeesAStatusResponseAndTheJSONStructure(Integer statusCode) {
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


}}
