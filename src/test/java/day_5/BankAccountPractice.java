package day_5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.Config;

import static utilities.CashWiseAuthorization.getToken;

public class BankAccountPractice {
    Faker faker = new Faker();
    static String bankID = "";

    @Test
    public void test1_createNewBankAccount() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";

        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name());
        requestBody.setDescription(faker.company().name());
        requestBody.setBalance(faker.number().numberBetween(5000,100000));

        Response response = RestAssured.given().auth()
                .oauth2(getToken()).contentType(ContentType.JSON).body(requestBody).post(url);
        response.prettyPrint();

        System.out.println("===========================");

        ObjectMapper objectMapper = new ObjectMapper();
        CustomResponse customResponse = objectMapper.readValue(response.asString(), CustomResponse.class);
        bankID = customResponse.getId();
        System.out.println("ID: " + bankID);
        System.out.println("Bank Account Name: " + customResponse.getBank_account_name());
        System.out.println("Description: " + customResponse.getDescription());
        System.out.println("Balance: " + customResponse.getBalance());

    }
}
