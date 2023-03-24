package com.sofkau.stepdefinitons;

import com.sofkau.models.Posts;
import com.sofkau.setup.ApiPlaceholderSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import static com.sofkau.questions.Return_Patch.returnUpdateSuccessfulJsonResponse;
import static com.sofkau.stepdefinitons.PersonajesStepDefinition.LOGGER;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.jsonplaceholdResources.JSONPLACEHOLD_RESOURCE;
import static com.sofkau.utils.jsonplaceholdResources.JSONPLACEHOLD__BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PostsStepDefinition extends ApiPlaceholderSetup {
    private Posts posts = new Posts();
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    @Given("el usuario esta en la pagina para crear post")
    public void elUsuarioEstaEnLaPaginaParaCrearPost() {
        setUp( JSONPLACEHOLD__BASE_URL.getValue());
        LOGGER.info("API disponibles para realizar la peticion");
    }

    @When("cuando el usuario envia solicitud con userId {string} titulo {string} y post  {string}")
    public void cuandoElUsuarioEnviaSolicitudConUserIdTituloYPost(String userId, String title, String body) {
        posts.setUserId(userId);
        posts.setTitle(title);
        posts.setBody(body);
        actor.attemptsTo(doPost().withTheResource(JSONPLACEHOLD_RESOURCE.getValue())
                .andTheRequestBody(posts));
        System.out.println(SerenityRest.lastResponse().body().asString());
        LOGGER.info("Los datos fueron enviados correctamente");
    }

    @Then("la pagina retornara un estatus con codigo {int} y con userId {string} titulo {string} y post  {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYConUserIdTituloYPost(Integer code, String userId, String title, String body) {
        try {
            Response actualResponse = returnUpdateSuccessfulJsonResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar id",
                            idC -> responseBody.get("userId").toString(), equalTo(userId)),
                    seeThat("Comparar id",
                            idC -> responseBody.get("title").toString(), equalTo(title)),
                    seeThat("Comparar id",
                            idC -> responseBody.get("body").toString(), equalTo(body))
            );
            LOGGER.info("Datos esperados y actuales correctos");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
