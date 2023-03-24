package com.sofkau.stepdefinitons;


import com.sofkau.models.Posts;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.JsonPlaceHolderResources.POSTS_BY_ID_RESOURCE;


public class PutPostStepDefinition extends ApiSetUp {

    public static Logger LOGGER= Logger.getLogger(PostIDStepDefinition.class);
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    Posts posts = new Posts();

    @When("El usuario envia una solicitud con el {string} del post deseado para actualizar el {string} el {int} y el {string}")
    public void elUsuarioEnviaUnaSolicitudConElDelPostDeseadoParaActualizarElElYEl(String id,
                                                                                   String title,
                                                                                   Integer userId,
                                                                                   String body) {
        posts.setId(id);
        posts.setTitle(title);
        posts.setBody(body);
        posts.setUserId(userId);
        actor.attemptsTo(
                doPut()
                        .withTheResource(POSTS_BY_ID_RESOURCE.getValue() + id)
                        .andTheRequestBody(posts)
        );

    }

    @Then("El usuario debe recibir un respuesta de status {int} y el post solicitado con su actualizacion")
    public void elUsuarioDebeRecibirUnRespuestaDeStatusYElPostSolicitadoConSuActualizacion(Integer code) {

    }


}
