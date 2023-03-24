package com.sofkau.stepdefinitons;

import com.sofkau.models.Photo;
import com.sofkau.models.ResponseNewPhoto;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnListPokemonResponse.returnListPokemonResponse;
import static com.sofkau.questions.ReturnNewPhotoResponse.returnNewPhotoResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class NewPhotoStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(NewPhotoStepDefinition.class);
    private Photo photo = new Photo();
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = null;
    @Given("que tengo un album con id {string}")
    public void queTengoUnAlbumConId(String album_id) {
        LOGGER.info("Inicio de la Automatizacion");
        setUp(JSONPLACEHOLDER_BASE_URL.getValue());
        photo.setAlbumId(album_id);
    }

    @When("agrego una nueva foto con los siguientes datos {string}, {string},{string}")
    public void agregoUnaNuevaFotoConLosSiguientesDatos(String title, String url, String thumbnailUrl) {
        photo.setTitle(title);
        photo.setUrl(url);
        photo.setThumbnailUrl(thumbnailUrl);
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(PHOTO_POST_RESOURCE.getValue())
                            .andTheRequestBody(photo)
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("la respuesta de la peticion debe tener el codigo de estado {int}")
    public void laRespuestaDeLaPeticionDebeTenerElCodigoDeEstado(Integer statusCode) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }

    @Then("la respuesta debe incluir los datos de la nueva foto:")
    public void laRespuestaDebeIncluirLosDatosDeLaNuevaFoto() {
        try {
            ResponseNewPhoto actualResponse = returnNewPhotoResponse().answeredBy(actor);
            actor.should(
                    seeThat("Retorna info",
                            act -> actualResponse, notNullValue()),
                    seeThat("id auto asignado",
                            ids -> actualResponse.getId(), equalTo("5001")),
                    seeThat("titulo de la foto",
                            name_region ->actualResponse.getTitle(),equalTo(photo.getTitle()))
            );
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
}
