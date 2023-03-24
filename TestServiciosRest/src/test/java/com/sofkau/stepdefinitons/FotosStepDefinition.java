package com.sofkau.stepdefinitons;

import com.sofkau.models.Fotos;
import com.sofkau.setup.ApiPlaceholderSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.sofkau.questions.Return_Patch.returnUpdateSuccessfulJsonResponse;
import static com.sofkau.stepdefinitons.PersonajesStepDefinition.LOGGER;
import static com.sofkau.utils.jsonplaceholdResources.*;
import static com.sofkau.tasks.DoPatch.doPatch;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class FotosStepDefinition extends ApiPlaceholderSetup {
    Fotos fotos = new Fotos();
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();

    @Given("el administrador esta en la pagina para editar informacion de la foto")
    public void elAdministradorEstaEnLaPaginaParaEditarInformacionDeLaFoto() {
        setUp( JSONPLACEHOLD__BASE_URL.getValue());
    }


    @When("cuando envia solicitud con {string} de foto nuevo nombre de albumId {string} titulo y  {string}")
    public void cuandoEnviaSolicitudConDeFotoNuevoNombreDeAlbumIdTituloY(String id, String albumId, String titulo) {
        fotos.setTitle(titulo);
        fotos.setAlbumId(albumId);
        actor.attemptsTo(doPatch().withTheResource( JSONPLACEHOLD_RESOURCE_PHOTOS.getValue() +id )
                .andTheRequestBody(fotos));
        System.out.println(lastResponse().body().asString());


    }
    @Then("la pagina retornara un estatus con codigo {int} y con el nuevo albumId {string} titulo y  {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYConElNuevoAlbumIdTituloY(Integer code,  String albumId,String title) {
        try {
            Response actualResponse = returnUpdateSuccessfulJsonResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar id",
                            iAl -> responseBody.get("albumId").toString(), equalTo(albumId)),
                    seeThat("Comparar id",
                            iTI -> responseBody.get("title").toString(), equalTo(title))
            );
            LOGGER.info("Asserción realiada con exito");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }
}
