package com.sofkau.stepdefinitons;

import com.sofkau.models.Posts;
import com.sofkau.models.ResponsePost;
import com.sofkau.setup.ApiPlaceholderSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;


import static com.sofkau.questions.ReturnRegisterSuccessfulJsonPost.returnRegisterSuccessfulJsonPost;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.jsonplaceholdResources.JSONPLACEHOLD_RESOURCE;
import static com.sofkau.utils.jsonplaceholdResources.JSONPLACEHOLD__BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PostsStepDefinition extends ApiPlaceholderSetup {
    private Posts posts = new Posts();
    @Given("el usuario esta en la pagina para crear post")
    public void elUsuarioEstaEnLaPaginaParaCrearPost() {
        setUp( JSONPLACEHOLD__BASE_URL.getValue());
    }

    @When("cuando el usuario envia solicitud con userId {string} titulo {string} y post  {string}")
    public void cuandoElUsuarioEnviaSolicitudConUserIdTituloYPost(String userId, String title, String body) {
        posts.setUserId(userId);
        posts.setTitle(title);
        posts.setBody(body);
        actor.attemptsTo(doPost().withTheResource(JSONPLACEHOLD_RESOURCE.getValue())
                .andTheRequestBody(posts));
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("la pagina retornara un estatus con codigo {int} y con userId {string} titulo {string} y post  {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYConUserIdTituloYPost(Integer code, String string, String string2, String string3) {
        ResponsePost actualResponseRegistro = returnRegisterSuccessfulJsonPost().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(code)),
                seeThat("Retorna información",
                        act -> actualResponseRegistro, notNullValue())
        );
    }
}
