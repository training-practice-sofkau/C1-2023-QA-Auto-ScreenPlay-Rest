package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;


import static com.sofkau.questions.ReturnConsultaSuccessfulJsonResponse.returnConsultaSuccessfulJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.PlaceholderResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


import static org.hamcrest.CoreMatchers.notNullValue;

public class ConsultaPublicacionesStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(ConsultaPublicacionesStepDefinition.class.getName());

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
    public void el_usuario_ve_un_codigo_de_respuesta_estado_y_la_la_informacion_de_la_publicacion(Integer codigo) {
        try{
            Response actuallResponse = returnConsultaSuccessfulJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + codigo,
                            response -> response.statusCode(codigo)),
                    seeThat("Retorna información",
                            act -> actuallResponse, notNullValue())
            );
           LOGGER.info("Respuesta status code: " + codigo);
        }catch (Exception e){
            LOGGER.error("Error: ", e);
            throw e;
        }

    }

}
