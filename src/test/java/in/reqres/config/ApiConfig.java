package in.reqres.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties",
        "file:./${env}.properties"
})
public interface ApiConfig extends Config {
    @Key("mainUrl")
    String getMainUrl();
}
