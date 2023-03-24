package com.sofkau.stepdefinitons;

import com.sofkau.models.Result;
import com.sofkau.questions.GetPokemons;
import com.sofkau.tasks.DoGetPokemon;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import com.sofkau.questions.ResponseCode;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;


public class PokemonStepDefinitions {

    private static final String restApiUrl = "https://pokeapi.co/api";
    Actor actor;

    @Given("que el usuario necesita consultar que pokemons que estan registrados, se hace la peticion")
    public void queElUsuarioNecesitaConsultarQuepokemonsQueEstanRegistradosSeHaceLaPeticion() {
        actor = Actor.named("James")
                .whoCan(CallAnApi.at(restApiUrl));
        actor.attemptsTo(
                DoGetPokemon.fromPage("/pokemon")
        );
    }

    @When("se valida que el codigo de respuesta sea exitoso")
    public void seValidaQueElCodigoDeRespuestaSeaExitoso() {
        actor.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(SC_OK))
        );
    }

    @Then("se validara que en los datos de retorno se encuentre el pokemon")
    public void SeValidaraQueEnLosDatosDeRetornoSeEncuentreElPokemon(String pokemon) {
        Result pokemons = new GetPokemons().answeredBy(actor).getResults().stream()
                .filter(x -> x.getName().equals(pokemon)).findFirst().orElse(null);

        actor.should(
                seeThat("La respuesta ", act -> pokemons, notNullValue())
        );

        actor.should(
                seeThat("Nombre del pokemon", act -> pokemons.getName(), equalTo(pokemon))
        );
    }

    @Then("se validara que en los datos de retorno se encuentre el pokemon {string}")
    public void seValidaraQueEnLosDatosDeRetornoSeEncuentreElPokemon(String pokemon) {
        Result pokemons = new GetPokemons().answeredBy(actor).getResults().stream()
                .filter(x -> x.getName().equals(pokemon)).findFirst().orElse(null);

        actor.should(
                seeThat("La respuesta ", act -> pokemons, notNullValue())
        );

        actor.should(
                seeThat("Nombre del pokemon", act -> pokemons.getName(), equalTo(pokemon))
        );
    }

    @Given("que el usuario realiza una peticion no valida")
    public void QueElUsuarioRealizaUnaPeticionNoValida() {
        actor = Actor.named("James")
                .whoCan(CallAnApi.at(restApiUrl));
        actor.attemptsTo(
                DoGetPokemon.fromPage("/pokemonx")
        );

    }

    @Then("el codigo de respuesta debe ser el de no encontrado")
    public void ElCodigoDeRespuestaDebeSerElDeNoEncontrado() {
        actor.should(
                seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(SC_NOT_FOUND))
        );
    }
}