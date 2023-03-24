package com.sofkau.stepdefinitons;

import com.sofkau.models.PostPublicacion;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnCrearpublicacionSuccessfulJsonResponse.returnCrearpublicacionSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.PlaceholderResources.JSONPLACEHOLDER_BASE_URL;
import static com.sofkau.utils.PlaceholderResources.POST_RESOURCE;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PostCrearPublicacionStedDefinition extends ApiSetUp {
    private PostPublicacion postPublicacion = new PostPublicacion();

    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;

    PostPublicacion postPublicacion1 = new PostPublicacion();


    @Given("que el usuario esta en la pagina de registro de la api")
    public void que_el_usuario_esta_en_la_pagina_de_registro_de_la_api() {
        setUp(JSONPLACEHOLDER_BASE_URL.getValue());


    }


    @When("el usuario envia una peticion post con el titulo {string} cuerpo {string} y el userId {int}")
    public void el_usuario_envia_una_peticion_post_con_el_titulo_cuerpo_y_el_userId(String titulo, String cuerpo, Integer userId) {
        postPublicacion.setTitulo(titulo);
        postPublicacion.setCuerpo(cuerpo);
        postPublicacion.setUserId(userId);
        actor.attemptsTo(
                doPost()
                        .withTheResource(POST_RESOURCE.getValue())
                        .andTheRequestBody(postPublicacion)
        );
        // System.out.println(lastResponse().body().asString());
    }

    @Then("el usuario debe ver un codigo {int} de respuesta y el id")
    public void el_usuario_debe_ver_un_codigo_de_respuesta_y_el_id(Integer codigo) {
        try {
            PostPublicacion postPublicacion = returnCrearpublicacionSuccessfulJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(codigo)),
                    seeThat("Retorna información",
                            act -> postPublicacion, notNullValue())
            );
            responseBody = (JSONObject) parser.parse(lastResponse().asString());


            String actualidaTitulo = (String) responseBody.get("titulo");
            String actualidaUserId = (String) responseBody.get("userId");
            Assertions.assertEquals(actualidaTitulo, postPublicacion.getTitulo());
            Assertions.assertEquals(actualidaUserId, postPublicacion.getUserId());


        } catch (Exception e) {

        }


    }

}
