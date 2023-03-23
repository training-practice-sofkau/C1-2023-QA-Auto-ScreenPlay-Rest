package com.sofkau.stepdefinitons;
import com.sofkau.models.ResponseCreate;
import com.sofkau.models.ResponseVerUsuario;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnVerUsuarioJsonResponse.returnVerUsuarioJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class VerUsuarioStepDefinition extends ApiSetUp {

    private String BASE_URL="https://jsonplaceholder.typicode.com/";
    private String VER_USUARIO="posts/";

    @Given("que estoy apuntando con un endpoint a la api de jsonplaceholder")
    public void queEstoyApuntandoConUnEndpointALaApiDeJsonplaceholder() {
        setUp(BASE_URL);
    }

    @When("envio la peticion get con el {string}")
    public void envioLaPeticionGetConEl(String id) {
        actor.attemptsTo(
                doGet()
                        .withTheResource(VER_USUARIO+id)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("recibo {int} de codigo de respuesta y la informacion del usuario")
    public void reciboDeCodigoDeRespuestaYLaInformacionDelUsuario(Integer codigo) {

        ResponseVerUsuario actualResponseVerUsuario=returnVerUsuarioJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: "+ HttpStatus.SC_OK,
                        responseCreate-> responseCreate.statusCode(codigo)),
                seeThat("Retorna informacion",
                        act-> actualResponseVerUsuario, notNullValue())
        );
    }

}
