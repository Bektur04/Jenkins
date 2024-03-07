package day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class BankAccountTest {
    @Test
    public void test1_tokenGenerator(){
        String url = "https://backend.cashwise.us/api/myaccount/auth/login";

        //String requestBody = "{\n" +
                //"    \"email\": \"bekturisaev0503@gmail.com\", \n" +
               // "    \"password\" : \"Beka0503\"\n" +
                //"}";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email","bekturisaev0503@gmail.com");
        requestBody.put("password", "Beka0503");

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body(requestBody).post(url);
        System.out.println("Status code: " + response.statusCode());

        response.prettyPrint();

        //jwt_token

        String token = response.jsonPath().getString("jwt_token");
        System.out.println("My token " + token);
    }

    @Test
    public void test2(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZWt0dXJpc2FldjA1MDNAZ21haWwuY29tIiwiZXhwIjoxNzA3MTgyNzQxLCJpYXQiOjE3MDY1Nzc5NDF9.26S0s8AJUUNi1odM7Berf-eg3a_3yreWL4WRNigr9zf1kHVBIanNHna_atnZLFuE942r5ETtf0LXayFQIl5PlQ";
        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type_of_pay", "BANK" );
        requestBody.put("bank_account_name", "Honea Bank" );
        requestBody.put("description", "Financial company");
        requestBody.put("balance", 1000 );

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody).post(url);
        System.out.println("Status code: " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void test3_getListBankAccounts(){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";
        System.out.println(token);
        System.out.println(url);

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);
        int sizeOfBankAccounts = response.jsonPath().getList("").size();
        System.out.println(sizeOfBankAccounts);

        for (int i = 0; i < sizeOfBankAccounts ; i++) {

            System.out.println("=======================================");
            String id = response.jsonPath().getString("[" + i + "].id");
            String bankAccountName = response.jsonPath().getString("[" + i + "].bank_account_name");
            String description = response.jsonPath().getString("[" + i + "].description");
            String typeOfPay = response.jsonPath().getString("[" + i + "].type_of_pay");
            String balance = response.jsonPath().getString("[" + i + "].balance");


            System.out.println("Bank id: " + id);
            System.out.println("Bank account name: " + bankAccountName);
            System.out.println("Bank description: " + description);
            System.out.println("Type of pay: " + typeOfPay);
            System.out.println("Balance: " + balance);


            Assert.assertNotNull("id is null", id);
            Assert.assertNotNull("Bank account is null", bankAccountName);
            Assert.assertNotNull("Description is null", description);
            Assert.assertNotNull("Balance is null",balance);
        }
    }

    @Test
    public void test4_getSingleBankAccount(){
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/931";
        String token = CashWiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(token).get(url);
        response.prettyPrint();

        System.out.println("=========TEST STARTED============");
        String bankAccountName = response.jsonPath().getString("bank_account_name");
        Assert.assertNotNull(bankAccountName);

        System.out.println(bankAccountName);
        System.out.println("==========TEST PASSED=============");
    }
}
