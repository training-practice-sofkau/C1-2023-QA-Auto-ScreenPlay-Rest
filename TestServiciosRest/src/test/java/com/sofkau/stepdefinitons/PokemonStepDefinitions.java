package com.sofkau.stepdefinitons;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sofkau.setup.ApiSetUp;
import net.serenitybdd.screenplay.ensure.Ensure;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static com.sofkau.tasks.DoGetPokemon.doGetPokemon;
import static com.sofkau.utils.ReqresResources.POKEMON;
import static com.sofkau.utils.ReqresResources.POKE_API_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;


public class PokemonStepDefinitions extends ApiSetUp {

    private Logger LOGGER = Logger.getLogger(PokemonStepDefinitions.class);

    @Given("el usuario esta en la PokeApi")
    public void elUsuarioEstaEnLaPokeApi() {
        setUp(POKE_API_URL.getValue());
    }

    @When("el usuario hace la peticion con {string}")
    public void elUsuarioHaceLaPeticionCon(String arg1) {
        try {
            actor.attemptsTo(
                    doGetPokemon().conElRecurso(POKEMON.getValue())
                            .yConElPokemon(arg1)
            );
        } catch (Exception e) {
            LOGGER.error("Error making request: " + e.getMessage());
        }
    }

    @Then("se valida que el {int} de respuesta sea correcto y que el {string} y el {int} sean correcto")
    public void seValidaQueElDeRespuestaSeaCorrectoYQueElYElSeanCorrecto(Integer int1, String arg1, Integer int2) {
        try {
            Gson gson = new Gson();
            JsonObject element = gson.fromJson(SerenityRest.lastResponse().getBody().asString(), JsonObject.class);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(int1)),
                    seeThat("Retorna información",
                            act -> SerenityRest.lastResponse(), notNullValue())
            );
            actor.attemptsTo(
                    Ensure.that(element.get("id").getAsString()).isEqualTo(int2 + "")
            );
            String pokemonId = element.get("id").getAsString();
            String pokemonCode = SerenityRest.lastResponse().getStatusCode() + "";
            String pokemonName = element.get("name").getAsString();
            LOGGER.info("Pokemon ID: " + pokemonId);
            LOGGER.info("Pokemon Code: " + pokemonCode);
            LOGGER.info("Pokemon Name: " + pokemonName);
        } catch (Exception e) {
            LOGGER.error("Error validating response: " + e.getMessage());
        }
    }
}
