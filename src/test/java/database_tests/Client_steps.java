package database_tests;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import pojo.RequestBody;

public class Client_steps {
    @Given("new client is created using API")
    public void new_client_is_created_using_api() {
        Faker faker = new Faker();
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());

    }
    @Then("verify new client is created")
    public void verify_new_client_is_created() {

    }

}
