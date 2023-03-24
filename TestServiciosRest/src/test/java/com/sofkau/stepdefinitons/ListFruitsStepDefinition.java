package com.sofkau.stepdefinitons;

import com.sofkau.models.Fruit;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.junit.annotations.TestData;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.sofkau.questions.ReturnInformationFruit.returnInformationFruit;
import static com.sofkau.questions.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.DoGetListFruit.doGetListFruit;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ListFruitsResources.FRUITYVICE_BASE_URL;
import static com.sofkau.utils.ListFruitsResources.LIST_FRUITS_RESOURCES;
import static com.sofkau.utils.ReqresResources.REGISTER_SUCCESSFUL_RESOURCE;
import static net.serenitybdd.assertions.assertj.WebElementStateAssert.assertThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.*;

public class ListFruitsStepDefinition extends ApiSetUp {

    Response response;
    Object fruit;
    JSONObject responseBody2;
    JSONParser parser = new JSONParser();

    public static Logger LOGGER = Logger.getLogger(ListFruitsStepDefinition.class);
    @Given("the user is in API fruityvice")
    public void theUserIsInAPIFruityvice() {
        setUp(FRUITYVICE_BASE_URL.getValue());
    }

    @When("the user send request GET with {string} name")
    public void theUserSendRequestGETWithName(String fruitName) {
        fruit = fruitName;
        actor.attemptsTo(
                 doGetListFruit().withTheResource(fruitName)
            );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("the user see the information of the fruit and a {int}")
    public void theUserSeeTheInformationOfTheFruitAndA(Integer status) {
        Fruit actualResponse= returnInformationFruit().answeredBy(actor);
        try{
            actor.should(
                    seeThatResponse("all the expected users should be returned",
                            response -> response.body("name", hasItems(fruit))
                    )
            );
            LOGGER.info("El codigo de respuesta es: " + HttpStatus.SC_OK);
            LOGGER.info("El nombre de la fruta es: ");

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

}
