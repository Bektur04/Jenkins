package day_4;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashWiseAuthorization.getToken;


public class SellerController {
    Faker faker = new Faker();

    static String sellerID = "";

    @Test
    public void test1(){
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers";
        System.out.println(url);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("company_name", faker.company().name());
        requestBody.put("seller_name", faker.name().fullName());
        requestBody.put("email", faker.internet().emailAddress());
        requestBody.put("phone_number", faker.phoneNumber().cellPhone());
        requestBody.put("address",faker.address().streetAddress());

        Response response = RestAssured.given().auth().oauth2(getToken())
                .contentType(ContentType.JSON).body(requestBody).post(url);
        response.prettyPrint();

        sellerID = response.jsonPath().getString("seller_id");
        System.out.println("Seller ID " + sellerID);


    }

    @Test
    public void test2(){

        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size",10);

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .params(params).get(url);
        response.prettyPrint();
    }
    @Test
    public void test3(){

        String token = CashWiseAuthorization.getToken();
        System.out.println(token);
    }
}
