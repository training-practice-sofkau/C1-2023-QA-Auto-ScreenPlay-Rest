package com.sofkau.stepdefinitons;
import com.sofkau.models.Post;
import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.POST_RESOURCE;

public class PostStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(PostStepDefinition.class);
    Post post=new Post();
    @When("the user sends the {string}, {string} and {int} of the post he wants to post")
    public void theUserSendsTheAndOfThePostHeWantsToPost(String string, String string2, Integer int1) {
        post.setTitle(string);
        post.setBody(string2);
        post.setUserId(int1);
        actor.attemptsTo(
                doPost()
                        .withTheResource(POST_RESOURCE.getValue())
                        .andTheRequestBody(post)
        );
        //System.out.println(SerenityRest.lastResponse().asString());
    }

    @Then("the user sees a status {int} and the post he wants to post {string}")
    public void theUserSeesAStatusAndThePostHeWantsToPost(Integer int1, String string) {
    }
}
