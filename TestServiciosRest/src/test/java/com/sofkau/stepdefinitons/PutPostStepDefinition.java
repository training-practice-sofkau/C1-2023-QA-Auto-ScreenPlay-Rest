package com.sofkau.stepdefinitons;


import com.sofkau.models.Posts;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.sofkau.questions.ReturnGetResponse.returnGetResponse;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.JsonPlaceHolderResources.JSONPLACE_BASE_URL;
import static com.sofkau.utils.JsonPlaceHolderResources.POSTS_BY_ID_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


public class PutPostStepDefinition extends ApiSetUp {

    public static Logger LOGGER= Logger.getLogger(PostIDStepDefinition.class);
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    Posts posts = new Posts();

    private String idPost;

    @Given("El usuario se encuentra en la web de JsonPlaceHolder para actualizar los post")
    public void elUsuarioSeEncuentraEnLaWebDeJsonPlaceHolderParaActualizarLosPost() {
        try {
            setUp(JSONPLACE_BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @When("El usuario envia una solicitud con el {string} del post deseado para actualizar el {string} el {int} y el {string}")
    public void elUsuarioEnviaUnaSolicitudConElDelPostDeseadoParaActualizarElElYEl(String id,
                                                                                   String title,
                                                                                   Integer userId,
                                                                                   String body) {
        try {
            idPost = id;
            posts.setId(id);
            posts.setTitle(title);
            posts.setBody(body);
            posts.setUserId(userId);
            actor.attemptsTo(
                    doPut()
                            .withTheResource(POSTS_BY_ID_RESOURCE.getValue() + id)
                            .andTheRequestBody(posts)
            );
            LOGGER.info("Peticion realizada");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }

    @Then("El usuario debe recibir un respuesta de status {int} y el post solicitado con su actualizacion")
    public void elUsuarioDebeRecibirUnRespuestaDeStatusYElPostSolicitadoConSuActualizacion(Integer code) {
        try {
            Response actualResponse = returnGetResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue()),
                    seeThat("Comparar id",
                            idC -> responseBody.get("id").toString(), equalTo(idPost))
            );
            LOGGER.info("Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
