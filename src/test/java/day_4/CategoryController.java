package day_4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import pojo.RequestBody;
import static utilities.CashWiseAuthorization.getToken;
import utilities.Config;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryController {

    Faker faker = new Faker();
    static String categoryId = "";

    @Test
    public void test1_createNewCategory(){
        String url = "https://backend.cashwise.us/api/myaccount/categories";

        RequestBody requestBody = new RequestBody();

        requestBody.setCategory_title(faker.commerce().department());
        requestBody.setCategory_description(faker.commerce().productName());
        requestBody.setFlag(false);

        Response response = RestAssured.given().auth()
                .oauth2(getToken())
                .contentType(ContentType.JSON).body(requestBody).post(url);
        response.prettyPrint();

        categoryId = response.jsonPath().getString("category_id");

    }
    @Test
    public void test2_getSingleCategory() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/categories/" + categoryId;
        Response response = RestAssured.given().auth().oauth2(getToken())
                .get(url);

        System.out.println(response.jsonPath().getString("category_id"));
        System.out.println(response.jsonPath().getString("category_title"));
        System.out.println(response.jsonPath().getString("category_description"));


        System.out.println("Status code: "  + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println(customResponse.getCategoryId());
        System.out.println(customResponse.getCategory_title());
        System.out.println(customResponse.getCategory_description());

        System.out.println("=========TEST STARTED==============");
        Assert.assertNotNull(customResponse.getCategoryId());
        Assert.assertNotNull(customResponse.getCategory_title());
        Assert.assertNotNull(customResponse.getCategory_description());
        System.out.println("=========TEST PASSED===============");

    }
}
