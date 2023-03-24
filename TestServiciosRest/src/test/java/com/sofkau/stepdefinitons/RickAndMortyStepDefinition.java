package com.sofkau.stepdefinitons;

import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Data;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static com.sofkau.utils.RickAndMortyResources.RICK_AND_MORTY_BASE_URL;
import static com.sofkau.utils.RickAndMortyResources.RICK_AND_MORTY_SELECCION_ID;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RickAndMortyStepDefinition extends ApiSetUp {

    @Given("que el usuario esta usando la API de Rick and Morty")
    public void que_el_usuario_esta_usando_la_API_de_Rick_and_Morty() {
        setUp(RICK_AND_MORTY_BASE_URL.getValue());

    }

    @When("el usuario envia una solicitud de tipo GET mandando el  {string}")
    public void el_usuario_envia_una_solicitud_de_tipo_GET_mandando_el(String id) {

        String idPersonaje = id;
        actor.attemptsTo(
                doGet()
                        .withTheResource(String.format(idPersonaje, RICK_AND_MORTY_SELECCION_ID))


        );
    }

    @Then("se deberia observar el estatus  {int}  y un  body de respuesta con la informacion del personaje")
    public void se_deberia_observar_el_estatus_y_un_body_de_respuesta_con_la_informacion_del_personaje(Integer statusCode) {
        Response actualResponse= returnRegisterSuccessfulJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(statusCode)),
                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );
    }
}
