package in.reqres.config;

import in.reqres.config.ApiConfig;
import org.aeonbits.owner.ConfigFactory;

public enum ConfigReader {
    Instance;

    private static final ApiConfig apiConfig = ConfigFactory.create(
            ApiConfig.class,
            System.getProperties()
    );

    public ApiConfig read(){return apiConfig;}
}
