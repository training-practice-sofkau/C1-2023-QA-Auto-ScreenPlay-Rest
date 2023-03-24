package com.sofkau.questions;

import com.sofkau.models.Product;
import com.sofkau.models.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnQuestionProduct implements Question<Product> {


    @Override
    public Product answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(Product.class);
    }

    public static ReturnQuestionProduct returnQuestionProduct() {
        return new ReturnQuestionProduct();
    }

}
