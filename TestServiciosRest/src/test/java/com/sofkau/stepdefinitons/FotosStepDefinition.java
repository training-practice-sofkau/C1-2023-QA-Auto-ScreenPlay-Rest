package com.sofkau.stepdefinitons;

import com.sofkau.models.Fotos;
import com.sofkau.setup.ApiPlaceholderSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static com.sofkau.utils.jsonplaceholdResources.*;
import static com.sofkau.tasks.DoPatch.doPatch;

public class FotosStepDefinition extends ApiPlaceholderSetup {
    Fotos fotos = new Fotos();
    @Given("el administrador esta en la pagina para editar informacion de la foto")
    public void elAdministradorEstaEnLaPaginaParaEditarInformacionDeLaFoto() {
        setUp( JSONPLACEHOLD__BASE_URL.getValue());
    }


    @When("cuando envia solicitud con {string} de foto nuevo nombre de albumId {string} titulo y  {string}")
    public void cuandoEnviaSolicitudConDeFotoNuevoNombreDeAlbumIdTituloY(String id, String albumId, String titulo) {
    fotos.setAlbumId(albumId);
    fotos.setTitulo(titulo);
        actor.attemptsTo(doPatch().withTheResource( JSONPLACEHOLD_RESOURCE_PHOTOS.getValue() +id )
                .andTheRequestBody(fotos));
        System.out.println(SerenityRest.lastResponse().body().asString());


    }
    @Then("la pagina retornara un estatus con codigo {int} y con el nuevo albumId {string} titulo y  {string}")
    public void laPaginaRetornaraUnEstatusConCodigoYConElNuevoAlbumIdTituloY(Integer int1, String string, String string2) {

    }
}
