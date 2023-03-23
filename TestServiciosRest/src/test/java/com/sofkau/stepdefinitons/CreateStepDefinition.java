package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateStepDefinition extends ApiSetUp {

    @Given("el usuario esta en la pagina de registro")
    public void elUsuarioEstaEnLaPaginaDeRegistro() {

    }

    @When("cuando el usuario envia solicitud de registro  con {string} y {string}")
    public void cuandoElUsuarioEnviaSolicitudDeRegistroConY(String string, String string2) {

    }

    @Then("la pagina retornara un estatus con codigo {int} y id y fecha de crecion de registro")
    public void laPaginaRetornaraUnEstatusConCodigoYIdYFechaDeCrecionDeRegistro(Integer int1) {

    }
}
