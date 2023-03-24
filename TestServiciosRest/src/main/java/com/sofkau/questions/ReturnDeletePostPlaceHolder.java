package com.sofkau.questions;

import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnDeletePostPlaceHolder implements Question<Response> {
    @Override
    public Response answeredBy(Actor actor) {
        return null;
    }

    @Override
    public String getSubject() {
        return Question.super.getSubject();
    }

    public static ReturnDeletePostPlaceHolder returnDeletePost() {
        return new ReturnDeletePostPlaceHolder();
    }
}
