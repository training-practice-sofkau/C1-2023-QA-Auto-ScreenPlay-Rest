package com.sofkau.stepdefinitons;

import com.sofkau.models.GetModelRickAndMorty;
import com.sofkau.models.PostModel;
//import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.Data;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.json.simple.JSONObject;

import static com.sofkau.questions.ReturnGetRetResponseRickAndMorty.returnGetResponse;
import static com.sofkau.questions.ReturnPostResponse.returnPostResponse;
import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.RickAndMortyResources.RICK_AND_MORTY_BASE_URL;
import static com.sofkau.utils.RickAndMortyResources.RICK_AND_MORTY_SELECCION_ID;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
//import static org.apache.log4j.xml.DOMConfigurator.LOGGER;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RickAndMortyStepDefinition extends ApiSetUp {

    @Given("que el usuario esta usando la API de Rick and Morty")
    public void que_el_usuario_esta_usando_la_API_de_Rick_and_Morty() {
        setUp(RICK_AND_MORTY_BASE_URL.getValue());

    }

    @When("el usuario envia una solicitud de tipo GET mandando el  {int}")
    public void elUsuarioEnviaUnaSolicitudDeTipoGETMandandoEl(Integer id) {

        actor.attemptsTo(
                doGet()
                        .withTheResource((RICK_AND_MORTY_SELECCION_ID.getValue()+id))
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("se deberia observar el estatus  {int}  y un  body de respuesta con la informacion del personaje")
    public void se_deberia_observar_el_estatus_y_un_body_de_respuesta_con_la_informacion_del_personaje(Integer statusCode) throws ParseException {
        try {
         //  GetModelRickAndMorty  respuestaActual = (GetModelRickAndMorty) returnGetResponse().answeredBy(actor);
           Response Cualquiercosa = (Response) returnGetResponse().answeredBy(actor);

              actor.should(
                    seeThatResponse("El codigo de respuesta deberia ser: " + statusCode,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna informacion",
                            act -> Cualquiercosa, notNullValue())
            );

           // LOGGER.info("Ingreso exitosamente de la informacion del personaje");
        } catch (Exception e){
           //LOGGER.warn(e.getMessage());
        }
    }
}
