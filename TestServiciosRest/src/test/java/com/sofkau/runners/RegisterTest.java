package com.sofkau.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/rest"},
        glue = {"com.sofkau.stepdefinitons"},
        tags ={"@ConsultarUsuario "}
        //@ConsultarPublicaciones
        //@ConsultarUsuario
        // @RegisterPublicacion
        //@Register

)
public class RegisterTest {
}
