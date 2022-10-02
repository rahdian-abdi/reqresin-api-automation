package Reqres.API;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.File;

public class PostCreateUserApi {
    public static String URL = "https://reqres.in";
    public static String DIR = System.getProperty("user.dir");
    public static String JSON_FILE = DIR+"/src/test/resources/JSON";
    public static String POST_CREATE_NEW_USER = URL+"/api/users";
    public static String POST_CREATE_NEW_USER_INVALID_PARAMETER = URL+"/api/userrr";

    @Step("Post create new user")
    public void postCreateNewUser(File json) {
        SerenityRest.given().contentType(ContentType.JSON).body(json);
    }
}
