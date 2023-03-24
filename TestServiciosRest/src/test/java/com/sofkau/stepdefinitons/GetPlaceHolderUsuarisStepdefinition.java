package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetPlaceHolderUsuarisStepdefinition extends ApiSetUp {

    @Given("que el usuario esta en la pagina de registro de usuario")
    public void que_el_usuario_esta_en_la_pagina_de_registro_de_usuario() {

    }



    @When("el usuario envia una peticion get con el {string} del usuario")
    public void el_usuario_envia_una_peticion_get_con_el_del_usuario(String string) {

    }


    @Then("el usuario debe observar un codigo de respuesta {int} y la la informacion del usuario")
    public void el_usuario_debe_observar_un_codigo_de_respuesta_y_la_la_informacion_del_usuario(Integer int1) {

    }
}
