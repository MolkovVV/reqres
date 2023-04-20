package in.reqres.testdata;

import com.github.javafaker.Faker;

public class RandomUser {
    Faker faker = new Faker();
    private final String name = faker.name().firstName();
    private final String job = faker.job().position();

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
