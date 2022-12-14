package Reqres.StepDefinitions;

import Reqres.API.PostRegisterUserApi;
import Reqres.ReqresResponses;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import java.io.File;
import static org.hamcrest.Matchers.equalTo;

public class PostRegisterUserStepDefinition {
    @Steps
    PostRegisterUserApi postRegister;
    // REGISTER USER

    @Given("Post register user")
    public void postRegisterUser() {
        File json = new File(PostRegisterUserApi.JSON_FILE+"/BodyRequest/PostRegisterUser.json");
        postRegister.postRegisterUser(json);
    }

    @When("Send request register")
    public void sendRequestRegister() {
        SerenityRest.when().post(PostRegisterUserApi.POST_REGISTER);
    }

    @And("Response body should contain token {string}")
    public void responseBodyShouldContainTokenToken(String token) {
        SerenityRest.then().body(ReqresResponses.TOKEN, equalTo(token));
    }
    @And("Post register user json schema")
    public void postRegisterUserJsonSchema() {
        File json = new File(PostRegisterUserApi.JSON_FILE+"/SchemaValidator/PostRegisterUserJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    // REGISTER UNSUCCESSFUL
    @Given("Post register user without password")
    public void postRegisterUserWithoutPassword() {
        File json = new File(PostRegisterUserApi.JSON_FILE+"/BodyRequest/PostRegisterUnsuccessful.json");
        postRegister.postRegisterUser(json);
    }

    @And("Response body should contain message {string}")
    public void responseBodyShouldContainMessage(String message) {
        SerenityRest.then()
                .body(ReqresResponses.ERROR_MESSAGE, equalTo(message));
    }
    @And("Post register user unsuccessful json schema")
    public void postRegisterUserUnsuccessfulJsonSchema() {
        File json = new File(PostRegisterUserApi.JSON_FILE+"/SchemaValidator/PostRegisterUserUnsuccessfulJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }
}
