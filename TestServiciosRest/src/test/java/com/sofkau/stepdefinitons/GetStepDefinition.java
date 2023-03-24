package com.sofkau.stepdefinitons;
import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.GET_RESOURCE;
import static com.sofkau.utils.ReqresResources.JSON_PLACE_HOLDER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;
public class GetStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(GetStepDefinition.class);

    @Given("the user is in the JSON place holder page")
    public void theUserIsInTheJSONPlaceHolderPage() {
        setUp(JSON_PLACE_HOLDER.getValue());
    }

    @When("the user sends the {int} that he wants to get")
    public void theUserSendsTheThatHeWantsToGet(Integer int1) {
        actor.attemptsTo(
                doGet().withTheResource(GET_RESOURCE.getValue())
                        .andWithTheNumber(int1 + "")
        );
    }

    @Then("the user sees a status {int} and the post he wants with the id {int}")
    public void theUserSeesAStatusAndThePostHeWantsWithTheId(Integer int1, Integer int2) {
        Response actualResponse = returnRegisterSuccessfulJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(int1)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );
        if (int1 == 200)
            actor.attemptsTo(
                    Ensure.that(actualResponse.getId()).isEqualTo(int2)
            );
        LOGGER.info("| Esperado | Obtenido | Valor |");
        if(SerenityRest.lastResponse().statusCode()==int1)
            LOGGER.info("| "+int1+" | "+SerenityRest.lastResponse().statusCode()+" | cumple |");
        else
            LOGGER.info("| "+int1+" | "+SerenityRest.lastResponse().statusCode()+" | no cumple |");
        if(actualResponse.getId()==int2)
            LOGGER.info("| "+int2+" | "+actualResponse.getId()+" | cumple |");
        else
            LOGGER.info("| "+int2+" | "+actualResponse.getId()+" | cumple |");
    }
}
