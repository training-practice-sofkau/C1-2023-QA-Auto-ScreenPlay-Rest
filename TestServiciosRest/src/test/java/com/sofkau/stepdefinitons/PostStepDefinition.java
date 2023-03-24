package com.sofkau.stepdefinitons;
import com.sofkau.models.Post;
import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.JSON_PLACE_HOLDER;
import static com.sofkau.utils.ReqresResources.POST_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PostStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(PostStepDefinition.class);
    Post post=new Post();
    @Given("the user is in the JSON place holder post page")
    public void theUserIsInTheJSONPlaceHolderPostPage() {
        setUp(JSON_PLACE_HOLDER.getValue());
    }
    @When("the user sends the {string}, {string} and {int} of the post he wants to post")
    public void theUserSendsTheAndOfThePostHeWantsToPost(String string, String string2, Integer int1) {
        post.setTitle(string);
        post.setBody(string2);
        post.setUserId(int1);
        actor.attemptsTo(
                doPost()
                        .withTheResource(POST_RESOURCE.getValue())
                        .andTheRequestBody(post)
        );
    }

    @Then("the user sees a status {int} and the post he wants to post {string}")
    public void theUserSeesAStatusAndThePostHeWantsToPost(Integer int1, String string) {
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
