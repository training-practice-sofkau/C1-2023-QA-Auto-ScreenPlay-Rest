package com.sofkau.stepdefinitons;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sofkau.models.PostModel;
import com.sofkau.questions.ReturnPostResponse;
import com.sofkau.setup.ApiSetUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnPostResponse.returnPostResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.PlaceHolderApiResources.PLACE_HOLDER_BASE_URL;
import static com.sofkau.utils.PlaceHolderApiResources.PLACE_HOLDER_BASE_URL_POST_PHOTOS;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PlaceHolderPostPhotosStepDefinition extends ApiSetUp {
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;

    PostModel postModel = new PostModel();

    @Given("que el usuario ingresa a la API de PlaceHolder en la seccion de Photos")
    public void que_el_usuario_ingresa_a_la_API_de_PlaceHolder_en_la_seccion_de_Photos() {

        try {
            setUp(PLACE_HOLDER_BASE_URL.getValue());

        } catch (Exception e) {

            actor.should(
                    seeThatResponse("lA API funciona",
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
        }


    }


    @When("el usuario ingresa la informacion de {int}, {int}, {string}, {string} y  {string}")
    public void el_usuario_ingresa_la_informacion_de_y(int AlbumId, int Id, String string, String string2, String string3) {
        postModel.setAlbumId(AlbumId);
        postModel.setId(Id);
        postModel.setTitle(string);
        postModel.setUrl(string2);
        postModel.setThumbnailUrl(string3);


        try {
            actor.attemptsTo(

                    doPost()
                            .withTheResource(PLACE_HOLDER_BASE_URL_POST_PHOTOS.getValue())
                            .andTheRequestBody(postModel)
            );

        } catch (Exception e) {

            e.printStackTrace();
        }


    }


    @Then("el usuario debe ver el registro de la foto con los datos ingreasados y un estatus {int}")
    public void el_usuario_debe_ver_el_registro_de_la_foto_con_los_datos_ingreasados_y_un_estatus(Integer code) throws ParseException {

        try {
            PostModel respuestaActual = returnPostResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("Codigo de respuesta: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),

                    seeThat("Retorna el JSON con los datos ingresados",
                            act -> respuestaActual.getTitle(), notNullValue())


            );

            responseBody = (JSONObject) parser.parse(lastResponse().asString());
            String actualTitle = (String) responseBody.get("title");
            int actualId = (int) responseBody.get("id");
            int actualAlbumId = (int) responseBody.get("albumId");
            String actualUrl = (String) responseBody.get("url");
            String actualThumbnailUrl = (String) responseBody.get("thumbnailUrl");

            Assertions.assertEquals(actualTitle, respuestaActual.getTitle());
            Assertions.assertEquals(actualId, respuestaActual.getId());
            Assertions.assertEquals(actualAlbumId, respuestaActual.getAlbumId());
            Assertions.assertEquals(actualUrl, respuestaActual.getUrl());
            Assertions.assertEquals(actualThumbnailUrl, respuestaActual.getThumbnailUrl());


        } catch (Exception e) {

        }

    }


}
