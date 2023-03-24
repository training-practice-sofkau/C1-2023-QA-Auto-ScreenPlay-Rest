package com.sofkau.stepdefinitons;

import com.sofkau.models.Product;
import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnQuestionProduct.returnQuestionProduct;
import static com.sofkau.tasks.DoPostProducts.doPostProducts;
import static com.sofkau.tasks.DoPutProducts.doPutProducts;
import static com.sofkau.utils.ProductResources.PRODUCT_RESOURCES_BASE_URL;
import static com.sofkau.utils.ProductResources.PRODUCT_SUCCESSFUL_RESOURCES;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class PutProductsStepDefinition extends ApiSetUp {

    private Product producto = new Product();
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;
    private Response response;
    public static Logger LOGGER = Logger.getLogger(GetFreetogameStepDefinition.class);

    @Given("el administrador esta en la pagina FakerProducts seccion actualizar")
    public void elUsuarioEstaEnLaPaginaFakerProducts() {
        setUp(PRODUCT_RESOURCES_BASE_URL.getValue());
    }


    @When("el administrador envia la informacion  {string}, {double}, {string}, {string}, {string}")
    public void cuandoElUsuarioEnviaLaInformacion(String title, Double precio, String descripcion, String imagen, String categoria) {
        producto.setTitle(title);
        producto.setPrice(precio);
        producto.setDescription(descripcion);
        producto.setImage(imagen);
        producto.setCategory(categoria);
        actor.attemptsTo(
                doPutProducts()
                        .withTheResource(PRODUCT_SUCCESSFUL_RESOURCES.getValue())
                        .andTheRequestBody(producto)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("el administrador debe ver un mensaje con informacion actualizada del producto con un estatus {int}")
    public void elUsuarioDebeVerUnMensajeConInformacionActualizadaDelProductoConUnEstatus(Integer code) {
        try {
            // Obtener la respuesta del servidor con Serenity BDD
            Product actualResponse = returnQuestionProduct().answeredBy(actor);

            actor.should(
                    // Validar el código de estado HTTP con Serenity BDD
                    seeThatResponse("El codigo de respuesta es: " + code,
                            response -> response.statusCode(code)),
                    // Validar que la respuesta tenga información con Serenity BDD
                    seeThat("Retorna información",
                            act -> actualResponse.getTitle(), notNullValue())
            );
            responseBody = (JSONObject) parser.parse(lastResponse().asString());
            LOGGER.info(" esta es la respuesta ---> " + actualResponse.getTitle() + actualResponse.getCategory());

            // Validar las propiedades "title" y "category" con JUnit
            String title = (String) responseBody.get("title");
            String category = (String) responseBody.get("category");
            Assertions.assertEquals(title, actualResponse.getTitle());
            Assertions.assertEquals(category, actualResponse.getCategory());
        } catch (AssertionError e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail("La validación de la respuesta del servidor ha fallado.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
