package in.reqres.tests;

import com.google.gson.Gson;
import in.reqres.endpoints.Endpoints;
import in.reqres.testdata.RandomUser;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class ReqresTests extends TestBase{

    @ParameterizedTest(name="Check GET method /api/users?page={0}")
    @ValueSource(strings = {"2","4"})

    public void getListUsers(String count){
        Response response = given()
                                .param(Endpoints.listUsersQueryParamName,count)
                            .when()
                                .get(Endpoints.listUsersPath)
                            .then()
                                 .extract()
                                 .response();

        Assertions.assertEquals(response.jsonPath().getInt("page"),Integer.parseInt(count),"Doesn`t match");
        response
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("schemes/listUsersScheme.json"));
        List<String> emails = response.jsonPath().getList("data.email");
        List<Integer> id = response.jsonPath().getList("data.id");
        Assertions.assertTrue(emails.stream().allMatch(email->email.endsWith("@reqres.in")),"emails doesn`t end`s with '@reqres.in'");
        Assertions.assertEquals(response.jsonPath().get("total"), Collections.max(id), "total value doesn`t match max id");
    }

    @Test
    @DisplayName("Check GET method /api/users/ -> get User with id == 2")
    public void getSingleUser(){
        String userId = "2";
        when()
            .get(Endpoints.singleUserPath + userId)
        .then()
            .statusCode(200)
            .assertThat().body(matchesJsonSchemaInClasspath("schemes/singleUserScheme.json"))
            .assertThat().body("data.id", equalTo(Integer.parseInt(userId)));
    }

    @Test
    @DisplayName("Check GET method /api/users/ -> User with id == 23 Not Found")
    public void singleUserNotFound(){
        String userId = "23";
        when()
             .get(Endpoints.singleUserPath + userId)
             .then()
             .statusCode(404)
             .assertThat().body(is("{}"));
    }

    @Test
    @DisplayName("Check POST method  /api/users -> Create User")
    public void registerNewUser(){
        RandomUser user = new RandomUser();
        String name = user.getName();
        String job = user.getJob();
        HashMap<String,String> body = new HashMap();
        body.put("name",name);
        body.put("job",job);

        Gson gson = new Gson();

        given().body(gson.toJson(body))
                .when()
                .post(Endpoints.createUserPath)
                .then()
                .statusCode(201)
                .assertThat().body("name", equalTo(name))
                .assertThat().body("job",equalTo(job))
                .body(matchesJsonSchemaInClasspath("schemes/createUserScheme.json"));

    }

    @Test
    @DisplayName("Check PUT method  /api/users ->  User")
    public void updateUser(){
        String id = "2";
        RandomUser user = new RandomUser();
        String name = user.getName();
        String job = user.getJob();
        HashMap<String,String> body = new HashMap();
        body.put("name",name);
        body.put("job",job);

        Gson gson = new Gson();

        given().body(gson.toJson(body))
                .when()
                .put(Endpoints.createUserPath + id)
                .then()
                .statusCode(200)
                .assertThat().body("name", equalTo(name))
                .assertThat().body("job",equalTo(job))
                .body(matchesJsonSchemaInClasspath("schemes/updateUserScheme.json"));
    }
}
