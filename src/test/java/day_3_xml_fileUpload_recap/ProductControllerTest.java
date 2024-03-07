package day_3_xml_fileUpload_recap;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class ProductControllerTest {


    String token = CashWiseAuthorization.getToken();

    @Test
    public void test1_createNewProduct(){
        String url = Config.getProperty("baseUrl") + "/api/myaccount/products";

        Faker faker = new Faker();

        String productTitle = faker.commerce().productName();
        double productPrice = faker.number().numberBetween(100,10000);
        int serviceTypeID = 2;
        int categoryID = 808;
        String description = productTitle + " Company";


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("product_title",productTitle);
        requestBody.put("product_price",productPrice);
        requestBody.put("service_type_id",serviceTypeID);
        requestBody.put("category_id",categoryID);
        requestBody.put("product_description",description);

       Response response = RestAssured.given().auth().oauth2(token)
                .contentType(ContentType.JSON).body(requestBody).post(url);
        response.prettyPrint();

        System.out.println("================TEST=============================");


        Integer productId = response.jsonPath().getInt("product_id");
        url =  Config.getProperty("baseUrl") + "/api/myaccount/products/" + 916;

        response = RestAssured.given()
                .auth().oauth2(token)
                .get( url );

        String actualProductTitle = response.jsonPath().getString("product_title");
        String actualProductPrice = response.jsonPath().getString("product_price");
        String actualProductDescription = response.jsonPath().getString("product_description");
        System.out.println(actualProductTitle);
        System.out.println(actualProductPrice);
        System.out.println(actualProductDescription);

        System.out.println("==========TEST STARTED===============");
        Assert.assertEquals("Product Title is not correct", productTitle,actualProductTitle);
        Assert.assertEquals("Product Price is not correct", productPrice+"", actualProductPrice);
        Assert.assertEquals("Product Description is not correct",description,  actualProductDescription);
        System.out.println("==========TEST PASSED!===============");
    }
}
