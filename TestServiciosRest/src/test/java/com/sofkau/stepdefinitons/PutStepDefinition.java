package com.sofkau.stepdefinitons;
import com.sofkau.models.Post;
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
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.ReqresResources.JSON_PLACE_HOLDER;
import static com.sofkau.utils.ReqresResources.PUT_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PutStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(PutStepDefinition.class);
    Post post=new Post();
    @Given("the user is in the JSON place holder put page")
    public void theUserIsInTheJSONPlaceHolderPutPage() {
        setUp(JSON_PLACE_HOLDER.getValue());
    }

    @When("the user sends the {int}, {string} and {string} of the post he wants to edit")
    public void theUserSendsTheAndOfThePostHeWantsToEdit(Integer int1, String string, String string2) {
        post.setTitle(string);
        post.setBody(string2);
        actor.attemptsTo(
                doPut().withTheResource(PUT_RESOURCE.getValue()+int1)
                        .andTheRequestBody(post)
        );
    }

    @Then("the user sees a status {int} and the post he wants to edit {string}")
    public void theUserSeesAStatusAndThePostHeWantsToEdit(Integer int1, String string) {
        Response actualResponse= returnRegisterSuccessfulJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(int1)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );
        actor.attemptsTo(
                Ensure.that(actualResponse.getTitle()).isEqualTo(string)
        );
        LOGGER.info("| Esperado | Obtenido | Valor |");
        if (SerenityRest.lastResponse().statusCode()==int1)
            LOGGER.info("| "+int1+" | "+SerenityRest.lastResponse().statusCode()+" | cumple |");
        else
            LOGGER.info("| "+int1+" | "+SerenityRest.lastResponse().statusCode()+" | no cumple |");
        if(actualResponse.getTitle().equalsIgnoreCase(string))
            LOGGER.info("| "+string+" | "+actualResponse.getTitle()+" | cumple |");
        else
            LOGGER.info("| "+string+" | "+actualResponse.getTitle()+" | no cumple |");
    }
}
