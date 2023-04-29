package in.reqres.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GroovyExample extends TestBase {
    @Test
    @DisplayName("Get List resources")
    public void getListResourcesWithGroovy() {
        // @formatter:off
        given()
                .spec(requestSpecification)
                .when()
                .get("api/users")
                .then()
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("eve.holt@reqres.in"))
                .body("data.last_name[1]", equalTo("Weaver"))
                .body("data.findAll{it.avatar =~/.*?image.jpg/}.avatar.flatten()",
                        hasItem("https://reqres.in/img/faces/6-image.jpg"))
                .body("data.findAll{it.last_name =~/^\\w{1,10}$/}.last_name.flatten()",
                        hasSize(6));
    }
}
