package in.reqres.tests;

import in.reqres.config.ConfigReader;
import org.junit.jupiter.api.BeforeEach;

import static in.reqres.spec.Specification.initSpecification;
import static in.reqres.spec.Specification.requestSpecification;

public class TestBase {
    @BeforeEach
    public void setEnvironment(){
        System.setProperty("env","prod");
        initSpecification(requestSpecification(ConfigReader.Instance.read().getMainUrl()));
    }
}
