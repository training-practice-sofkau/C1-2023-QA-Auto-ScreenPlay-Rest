package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;

import java.util.HashMap;

import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.utils.ResourceCases.DELETE_ALBUM;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteAlbumTestStepDefinition extends ApiSetUp {
    private final HashMap<String, Object> headers = new HashMap<>();


    @Dado("que el usuario quiere hacer la peticion para eliminar un album")
    public void queElUsuarioQuiereHacerLaPeticionParaEliminarUnAlbum() {
        generalSetUp();
        actor.can(CallAnApi.at(RestAssured.baseURI));
    }

    @Cuando("el usuario realiza la peticion para eliminar un album con el id interno {int}")
    public void elUsuarioRealizaLaPeticionParaEliminarUnAlbumConElIdInterno(int extension) {
        actor.attemptsTo(
                doDelete()
                        .usingTheResource(DELETE_ALBUM.getValue() + extension)
                        .withHeaders(headers)
        );
    }

    @Entonces("el usuario obtendra un codigo de estado exitoso")
    public void elUsuarioObtendraUnCodigoDeEstadoExitoso() {
        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                )
        );
    }

}
