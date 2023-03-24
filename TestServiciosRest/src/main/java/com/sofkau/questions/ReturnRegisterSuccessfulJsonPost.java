package com.sofkau.questions;

import com.sofkau.models.ResponsePost;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnRegisterSuccessfulJsonPost implements Question<ResponsePost> {

    @Override
    public ResponsePost answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponsePost.class);

    }

    public static ReturnRegisterSuccessfulJsonPost returnRegisterSuccessfulJsonPost(){
        return new ReturnRegisterSuccessfulJsonPost();
    }


}
