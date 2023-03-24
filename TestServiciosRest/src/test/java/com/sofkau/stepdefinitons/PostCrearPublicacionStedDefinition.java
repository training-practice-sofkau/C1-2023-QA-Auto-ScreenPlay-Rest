package com.sofkau.stepdefinitons;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostCrearPublicacionStedDefinition {


    @Given("que el usuario esta en la pagina de registro de la api")
    public void que_el_usuario_esta_en_la_pagina_de_registro_de_la_api() {

    }


    @When("el usuario envia una peticion post con el titulo {string} cuerpo {string} y el userId {int}")
    public void el_usuario_envia_una_peticion_post_con_el_titulo_cuerpo_y_el_userId(String string, String string2, Integer int1) {

    }

    @Then("el usuario debe ver un codigo {int} de respuesta y el id")
    public void el_usuario_debe_ver_un_codigo_de_respuesta_y_el_id(Integer int1) {

    }

}
