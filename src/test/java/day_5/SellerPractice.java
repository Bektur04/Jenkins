package day_5;

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
import utilities.Config;

import static utilities.CashWiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellerPractice {

    Faker faker  = new Faker();
    static String seller_id = "";

    @Test
    public void test1_createSeller() throws JsonProcessingException {
        String  url = Config.getProperty("baseUrl") + "/api/myaccount/sellers";

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
        requestBody.setAddress(faker.address().fullAddress());

        Response response = RestAssured.given().auth()
                .oauth2(getToken()).contentType(ContentType.JSON).body(requestBody).post(url);
        System.out.println("Status code: " + response.statusCode());
        response.prettyPrint();

        Assert.assertEquals("Status code is incorrect",201,response.statusCode());

        System.out.println("=======================");

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        seller_id = String.valueOf(customResponse.getSeller_id());

        System.out.println("ID: " + seller_id);
    }

    @Test
    public void test2_getSellerByID() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/" + seller_id;
        Response response = RestAssured.given()
                .auth().oauth2(getToken()).get(url);

        Assert.assertEquals(200,response.statusCode());
        response.prettyPrint();

        ObjectMapper objectMapper = new ObjectMapper();
        CustomResponse customResponse = objectMapper.readValue(response.asString(), CustomResponse.class);

        Assert.assertNotNull("ID is null", customResponse.getSeller_id());
        Assert.assertNotNull("Name is null", customResponse.getSeller_name());
        Assert.assertNotNull("Email is null", customResponse.getEmail());
        Assert.assertNotNull("Address is null", customResponse.getAddress());


    }

    @Test
    public void test3_getListOfSellers() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/all";

        Response response = RestAssured.given().auth()
                .oauth2(getToken()).get(url);

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse[] customResponse = mapper.readValue(response.asString(), CustomResponse[].class);

        int size = customResponse.length;
//        for(CustomResponse cr :customResponse){
//            System.out.println("Name: " + cr.getSeller_name());
//            System.out.println("Email: " + cr.getEmail());
//        }

        for (int i = 0; i < size; i++) {
            System.out.println("Name: " + customResponse[i].getSeller_name());
            Assert.assertNotNull(customResponse[i].getSeller_name());
            System.out.println("ID: " + customResponse[i].getSeller_id());
            Assert.assertNotNull(customResponse[i].getSeller_id());

        }

    }

    @Test
    public void test4_deleteSeller() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/" + seller_id;
        Response response = RestAssured.given()
                .auth().oauth2(getToken()).delete(url);

        System.out.println("Status code: " + response.statusCode());
    }
}

