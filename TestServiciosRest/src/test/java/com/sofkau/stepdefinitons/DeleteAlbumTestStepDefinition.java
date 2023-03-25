package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.ReqresResources.BASE_JSON_URL;
import static com.sofkau.utils.ReqresResources.DELETE_ALBUM;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class DeleteAlbumTestStepDefinition extends ApiSetUp {

    private Logger LOGGER = Logger.getLogger(DeleteAlbumTestStepDefinition.class);

    @Given("el usuario esta en la pagina de jsonplaceholder")
    public void elUsuarioEstaEnLaPaginaDeJsonplaceholder() {
        setUp(BASE_JSON_URL.getValue());
    }

    @When("el usuario realiza la peticion para eliminar un album con el id interno {int}")
    public void elUsuarioRealizaLaPeticionParaEliminarUnAlbumConElIdInterno(Integer int1) {
        try {
            actor.attemptsTo(
                    doDelete().conElRecurso(DELETE_ALBUM.getValue())
                            .yConelId(int1 + "")
            );
            LOGGER.info("Album con id " + int1 + " eliminado");
        } catch (Exception e) {
            LOGGER.error("Error eliminando el album: " + e.getMessage());
        }
    }

    @Then("el usuario obtendra un {int} de estado exitoso")
    public void elUsuarioObtendraUnDeEstadoExitoso(Integer int1) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(int1))
            );
            LOGGER.info("Codigo de respuesta: " + int1);
        } catch (Exception e) {
            LOGGER.error("Error verificando el codigo de respuesta: " + e.getMessage());
        }
    }
}



