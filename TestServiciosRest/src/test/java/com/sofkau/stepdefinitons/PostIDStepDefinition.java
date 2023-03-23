package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.JsonPlaceHolderResources.JSONPLACE_BASE_URL;
import static com.sofkau.utils.JsonPlaceHolderResources.POSTS_BY_ID_RESOURCE;

public class PostIDStepDefinition extends ApiSetUp {

    public static Logger LOGGER= Logger.getLogger(PostIDStepDefinition.class);

    @Given("El usuario se encuentra en la web de JsonPlaceHolder")
    public void elUsuarioSeEncuentraEnLaWebDeJsonPlaceHolder() {
        setUp(JSONPLACE_BASE_URL.getValue());
    }

    @When("El usuario envia una solicitud con el {string} del post deseado")
    public void elUsuarioEnviaUnaSolicitudConElDelPostDeseado(String idPost) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(POSTS_BY_ID_RESOURCE.getValue() + idPost)
            );
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
    @Then("El usuario debe recibir un respuesta de status {int} y el post solicitado")
    public void elUsuarioDebeRecibirUnRespuestaDeStatusYElPostSolicitado(Integer statusCode) {
        System.out.println(SerenityRest.lastResponse().body().asString());
    }
}
