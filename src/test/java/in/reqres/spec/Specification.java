package in.reqres.spec;

import in.reqres.helpers.CustomAllureListener;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static in.reqres.helpers.CustomAllureListener.*;


public class Specification {
    public static RequestSpecification requestSpecification(String baseUrl){
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .log(LogDetail.URI)
                .log(LogDetail.BODY)
                .setContentType(ContentType.JSON)
                .build();
    }


    public static void initSpecification(RequestSpecification request){
        RestAssured.requestSpecification = request;

    }
}
