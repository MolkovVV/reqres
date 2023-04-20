package in.reqres.config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class Specification {
    public static RequestSpecification requestSpecification(String baseUrl){
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();
    }


    public static void initSpecification(RequestSpecification request){
        RestAssured.filters(new AllureRestAssured());
        RestAssured.requestSpecification = request;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }
}
