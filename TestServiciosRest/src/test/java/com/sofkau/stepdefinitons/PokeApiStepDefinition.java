package com.sofkau.stepdefinitons;

import com.sofkau.models.ResponsePokeApi;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnPokeApiJsonResponse.returnPokeApiJsonResponse;

import static com.sofkau.tasks.DoGet.doGet;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PokeApiStepDefinition extends ApiSetUp {
    private String BASE_URL="https://pokeapi.co/api/v2/";
    private String VER_JUEGO_POKEMON="version/";
    @Given("que estoy apuntando con un endpoint a la pokeapi")
    public void queEstoyApuntandoConUnEndpointALaPokeapi() {
        setUp(BASE_URL);
    }

    @When("envio la peticion get con el {string} del juego")
    public void envioLaPeticionGetConElDelJuego(String id) {
        actor.attemptsTo(
                doGet()
                        .withTheResource(VER_JUEGO_POKEMON+id)
        );
        //System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("recibo {int} de codigo de respuesta y la informacion del juego")
    public void reciboDeCodigoDeRespuestaYLaInformacionDelJuego(Integer codigo) {
        ResponsePokeApi actualResponsePokeApi=returnPokeApiJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: "+ HttpStatus.SC_OK,
                        responseCreate-> responseCreate.statusCode(codigo)),
                seeThat("Retorna informacion",
                        act-> actualResponsePokeApi, notNullValue())
        );
    }
}
