package com.sofkau.stepdefinitons;

import com.sofkau.models.Response;
import com.sofkau.models.ResponseCreate;
import com.sofkau.models.Usuario;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

import static com.sofkau.questions.ReturnCreateJsonResponse.returnCreateJsonResponse;
import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateUserStepDefinition extends ApiSetUp {

    private Usuario usuario=new Usuario();
    private String BASE_URL="https://reqres.in/";
    private String CREAR_USUARIO="api/users";

    @Given("que estoy apuntando con un endpoint a la api")
    public void queEstoyApuntandoConUnEndpointALaApi() {
        setUp(BASE_URL);
    }

    @When("envio la peticion get con el {string} y la {string}")
    public void envioLaPeticionGetConElYLa(String nombre, String trabajo) {
        usuario.setName(nombre);
        usuario.setJob(trabajo);
        actor.attemptsTo(
                doPost()
                        .withTheResource(CREAR_USUARIO)
                        .andTheRequestBody(usuario)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("recibo {int} de codigo de respuesta y el id con la fecha de creacion")
    public void reciboDeCodigoDeRespuestaYElIdConLaFechaDeCreacion(Integer codigo) {
        ResponseCreate actualResponseCreate=returnCreateJsonResponse().answeredBy(actor);
        actor.should(
                seeThatResponse("El codigo de respuesta es: "+ HttpStatus.SC_OK,
                        responseCreate-> responseCreate.statusCode(codigo)),
                seeThat("Retorna informacion",
                        act-> actualResponseCreate, notNullValue())
        );
    }
}
