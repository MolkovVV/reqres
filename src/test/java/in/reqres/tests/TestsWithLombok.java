package in.reqres.tests;

import com.google.gson.Gson;
import in.reqres.endpoints.Endpoints;
import in.reqres.models.request.UserLoginModelRequest;
import in.reqres.models.response.ListResourcesModelResponse;
import in.reqres.models.response.UserLoginModelResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestsWithLombok extends TestBase {
    @Test
    @DisplayName("Success user register")
    public void successUserRegister(){
        Gson gson = new Gson();
        UserLoginModelRequest userLoginModelRequest = new UserLoginModelRequest();
        userLoginModelRequest.setEmail("eve.holt@reqres.in");
        userLoginModelRequest.setPassword("pistol");

        UserLoginModelResponse response = step ("Make request", () ->
                given(requestSpecification)
                .body(userLoginModelRequest)
                .when()
                .post(Endpoints.registerUserPath)
                .then().extract().as(UserLoginModelResponse.class));

        step("verify that token not null in response", () ->
        Assertions.assertTrue(response.getToken() != null,"token is null!"));
        step("verify token value in response", () ->
        Assertions.assertEquals(response.getToken(),"QpwL5tke4Pnpja7X4","Token not valid!"));
        step("verify id value in response", () ->
        Assertions.assertEquals(response.getId(),4,"id not valid"));
        gson.toJson(response);
        step("verify Json-scheme in response", () ->
        assertThat(gson.toJson(response),matchesJsonSchemaInClasspath("schemes/registerUserScheme.json")));
    }

    @Test
    @DisplayName("Get List resources")
    public void getListResources(){
        ListResourcesModelResponse response = step ("Make request", () -> given(requestSpecification)
                .when()
                .get(Endpoints.lisResourcesPath)
                .then()
                .statusCode(200)
                .extract().as(ListResourcesModelResponse.class));
        step ("Verify page number in response", () ->
        Assertions.assertTrue(response.getPage() == 1, "Number page doesn`t match"));
        step ("Verify count sources in list", () ->
        Assertions.assertEquals(response.getData().size(),6,"Missing source!"));
    }
}
