package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class CashWiseAuthorization {

    private static String token;

    public static String getToken(){
        String url = "https://backend.cashwise.us/api/myaccount/auth/login";

        RequestBody requestBody = new RequestBody();
        requestBody.setEmail(Config.getProperty("email"));
        requestBody.setPassword(Config.getProperty("password"));


//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("email",Config.getProperty("email"));
//        requestBody.put("password", Config.getProperty("password"));

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body(requestBody).post(url);
        System.out.println("Status code: " + response.statusCode());

        token = response.jsonPath().getString("jwt_token");

        return token;
    }
}
