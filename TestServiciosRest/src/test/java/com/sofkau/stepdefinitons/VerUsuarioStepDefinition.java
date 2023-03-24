package com.sofkau.stepdefinitons;
import com.sofkau.models.ResponseCreate;
import com.sofkau.models.ResponseVerUsuario;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.logging.Logger;

import static com.sofkau.questions.ReturnVerUsuarioJsonResponse.returnVerUsuarioJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class VerUsuarioStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(VerUsuarioStepDefinition.class));
    private String BASE_URL="https://jsonplaceholder.typicode.com/";
    private String VER_USUARIO="posts/";
    public static String idUser;

    @Given("que estoy apuntando con un endpoint a la api de jsonplaceholder")
    public void queEstoyApuntandoConUnEndpointALaApiDeJsonplaceholder() {
        setUp(BASE_URL);
    }

    @When("envio la peticion get con el {string}")
    public void envioLaPeticionGetConEl(String id) {
        try{
            idUser=id;
            actor.attemptsTo(
                    doGet()
                            .withTheResource(VER_USUARIO+id)
            );

        }catch (Exception e){
            LOGGER.warning(e.getMessage());
        }

    }

    @Then("recibo {int} de codigo de respuesta y la informacion del usuario")
    public void reciboDeCodigoDeRespuestaYLaInformacionDelUsuario(Integer codigo) {
        try{
            ResponseVerUsuario actualResponseVerUsuario=returnVerUsuarioJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ HttpStatus.SC_OK,
                            responseCreate-> responseCreate.statusCode(codigo)),
                    seeThat("Retorna informacion",
                            act-> actualResponseVerUsuario, notNullValue()),
                    seeThat("ola",
                            id -> idUser, equalTo(actualResponseVerUsuario.getId()))

            );
            LOGGER.info("Se finaliza el step de ver usuario");
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }


    }

}
