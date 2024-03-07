package day_1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RegressinqTest {

    @Test
    public void test_1_getSingleRequest(){
        String url = "https://reqres.in/api/users/2";

        Response response = RestAssured.get(url);
        int statusCode = response.statusCode();
        System.out.println("Status code: " + statusCode);
        response.prettyPrint();
    }

    @Test
    public void test_2_getListOfUsers(){
       String url = "https://reqres.in/api/users";

        Map<String, Object> params = new HashMap<>();
        params.put("page", 2);

       Response response = RestAssured.given().params(params).get(url);
        System.out.println("Status code: " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void test3_createUser(){
        String url = "https://reqres.in/api/users";

        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = RestAssured.given().body(requestBody).post(url);
        System.out.println("Status code: " + response.statusCode());

        response.prettyPrint();
    }
}
