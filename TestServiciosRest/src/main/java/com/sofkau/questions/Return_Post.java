package com.sofkau.questions;

import com.sofkau.models.ResponsePosts;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class Return_Post implements Question<ResponsePosts> {

    @Override
    public ResponsePosts answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponsePosts.class);

    }

    public static Return_Post returnRegisterSuccessfulJsonPost(){
        return new Return_Post();
    }


}
