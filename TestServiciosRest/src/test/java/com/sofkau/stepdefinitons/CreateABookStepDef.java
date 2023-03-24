package com.sofkau.stepdefinitons;

import com.sofkau.models.Book;
import com.sofkau.setup.APISetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.Constants.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class CreateABookStepDef extends APISetup {
    private final Logger log = LoggerFactory.getLogger(DeleteAnAlbumStepDef.class);
    private final Book book = new Book();

    @Given("I have access to Fake REST API server")
    public void iHaveAccessToFakeRESTAPIServer() {
        try {
            log.info("Init scenario");
            setUp(FAKE_REST_API_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Wrong Setup provided");
        }
    }

    @When("I try to create a book with id {int}, title {string} and page count {int}")
    public void iTryToCreateABookWithIdTitleAndPageCount(Integer id, String title, Integer pageCount) {
        try {
            log.info("Running selection");
            book.setId(id);
            book.setTitle(title);
            book.setPageCount(pageCount);
            log.info(book.toString());
            actor.attemptsTo(
                    doPut()
                            .toTheResource(String.format(BOOKS_RESOURCE.getValue(), id))
                            .andTheRequestBody(book)
            );
        } catch (Exception e) {
            log.error("Wrong Setup provided");
        }
    }

    @Then("I will see the response code {int}")
    public void iWillSeeTheResponseCode(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Status code of book created should be shown",
                            response -> response.statusCode(code))
            );
            log.info("First assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            Assertions.fail();
        }
    }

    @And("I will receive the book info back")
    public void iWillReceiveTheBookInfoBack() {
        try {
            actor.should(
                    seeThatResponse("Book created should be shown",
                            response -> response
                                    .body("id", equalTo(book.getId()))
                                    .body("title", equalTo(book.getTitle()))
                                    .body("description", equalTo(book.getDescription()))
                                    .body("pageCount", equalTo(book.getPageCount()))
                                    .body("excerpt", equalTo(book.getExcerpt()))
                    )
            );
            log.info("Second assert passed");
        } catch (Exception e) {
            log.error("Test failed");
            Assertions.fail();
        } finally {
            log.error("Test completed");
        }
    }
}