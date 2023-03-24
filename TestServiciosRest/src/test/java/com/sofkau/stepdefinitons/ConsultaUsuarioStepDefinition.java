package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnConsultaSuccessfulJsonResponse.returnConsultaSuccessfulJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.PlaceholderResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ConsultaUsuarioStepDefinition extends ApiSetUp {

    @Given("que el usuario esta en la pagina de registro de usuario")
    public void que_el_usuario_esta_en_la_pagina_de_registro_de_usuario() {
        setUp(JSONPLACEHOLDER_BASE_URL.getValue());
    }



    @When("el usuario envia una peticion get con el {string} del usuario")
    public void el_usuario_envia_una_peticion_get_con_el_del_usuario(String id) {
        actor.attemptsTo(
                doGet()
                        .withTheResource(GET_RESOURCE_USUARIO.getValue()+id)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }


    @Then("el usuario debe observar un codigo de respuesta {int} y la la informacion del usuario")
    public void el_usuario_debe_observar_un_codigo_de_respuesta_y_la_la_informacion_del_usuario(Integer codigo) {
        Response actualResponse = returnConsultaSuccessfulJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta de la peticion es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(codigo)),
                seeThat("Retorna la siguiente información",
                        act -> actualResponse, notNullValue())
        );
    }
}
