package in.reqres.tests;

import org.junit.jupiter.api.BeforeEach;
import static in.reqres.spec.Specification.*;

public class TestBase {
    @BeforeEach
    public void setEnvironment(){
        System.setProperty("env","prod");
        initSpecification(requestSpecification, responseSpecification);
    }
}
