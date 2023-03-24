package com.sofkau.stepdefinitons;

import com.sofkau.setup.ApiSetUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.SWAPIResources.DARTH_VADER__RESOURSE;
import static com.sofkau.utils.SWAPIResources.SWAPI_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeopleStarWarsStepDefinition extends ApiSetUp {
    @Given("that the user has the ID of a Star Wars character")
    public void thatTheUserHasTheIDOfAStarWarsCharacter() {
        setUp(SWAPI_BASE_URL.getValue());
    }

    @When("when the user makes a request with the character id")
    public void whenTheUserMakesARequestWithTheCharacterId() {
        actor.attemptsTo(
                doGet()
                        .withTheResource(DARTH_VADER__RESOURSE.getValue())
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("the user should see a response containing the character's information")
    public void theUserShouldSeeAResponseContainingTheCharacterSInformation() {

        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(200))
        );

        String responseBody = SerenityRest.lastResponse().body().asString();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(responseBody);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String name = (String) jsonObject.get("name");
        assertEquals("Darth Vader", name);

    }
}
