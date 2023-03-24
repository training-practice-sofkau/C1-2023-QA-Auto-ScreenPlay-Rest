package com.sofkau.stepdefinitons;

import com.sofkau.models.PostModel;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnGetRetResponseRickAndMorty.returnGetResponse;
import static com.sofkau.questions.ReturnPostResponse.returnPostResponse;
import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.PlaceHolderApiResources.PLACE_DELETE_POST;
import static com.sofkau.utils.PlaceHolderApiResources.PLACE_HOLDER_BASE_URL;
import static com.sofkau.utils.RickAndMortyResources.RICK_AND_MORTY_SELECCION_ID;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class DeleteStepDefinition extends ApiSetUp {
    @Given("que el usuario ingresa a la API de PlaceHolder en la seccion de Eliminar Post")
    public void que_el_usuario_ingresa_a_la_API_de_PlaceHolder_en_la_seccion_de_Eliminar_Post() {

        try {
            setUp(PLACE_HOLDER_BASE_URL.getValue());

        } catch (Exception e) {

            actor.should(
                    seeThatResponse("lA API funciona",
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
        }


    }

    @When("el usuario ingresa  el {int} del post que desea eliminar")
    public void el_usuario_ingresa_el_del_post_que_desea_eliminar(Integer int1) {


        actor.attemptsTo(
                doDelete()
                        .withTheResource((PLACE_DELETE_POST.getValue() + int1))
        );
     //   System.out.println(SerenityRest.lastResponse().body().asString());

    }

    @Then("el usuario debe un  {int} de la peticion")
    public void el_usuario_debe_un_de_la_peticion(Integer statusCode) {

        try {
            Response respuesta = (Response) returnGetResponse().answeredBy(actor);

            actor.should(
                    seeThatResponse("El codigo de respuesta deberia ser: " + statusCode,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna informacion",
                            act -> respuesta, notNullValue())


            );


        } catch (Exception e) {

        }


    }
}
