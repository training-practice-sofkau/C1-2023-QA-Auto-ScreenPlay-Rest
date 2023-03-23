package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.PlaceholderResources.*;

public class ConsultaPublicacionesStepDefinition extends ApiSetUp {

   @Given("que el usuario esta en la pagina de registro")
    public void que_el_usuario_esta_en_la_pagina_de_registro() {
        setUp(JSONPLACEHOLDER_BASE_URL.getValue());

    }
    @When("el usuario envia una peticion get con el {string}")
    public void el_usuario_envia_una_peticion_get_con_el(String id) {
        actor.attemptsTo(
                doGet()
                        .withTheResource(GET_RESOURCE.getValue()+id)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());

    }
    @Then("el usuario ve un codigo de respuesta estado {int} y la la informacion de la publicacion")
    public void el_usuario_ve_un_codigo_de_respuesta_estado_y_la_la_informacion_de_la_publicacion(Integer int1) {

    }


}
