package day_3_xml_fileUpload_recap;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class XML_response_validationTest {

    static String id = "";
    @Test
    public void test1_createTraveller(){
        String url = "http://restapi.adequateshop.com/api/Traveler";


        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        String name = faker.name().firstName();
        String city = faker.address().city();
        String state = faker.address().state();


        String requestBody = "<?xml version=\"1.0\"?>\n" +
                "<Travelerinformation>\n" +
                "    <name>"+name+"</name>\n" +
                "    <email>"+email+"</email>\n" +
                "    <adderes>" + state + ", " + city + "</adderes>\n" +
                "</Travelerinformation>";

        Response response = RestAssured.given()
                .contentType(ContentType.XML)
                .body(requestBody).post(url);
        response.prettyPrint();

        id = response.xmlPath().getString("Travelerinformation.id");
    }

    @Test
    public void test2_getTraveller(){
        String url = "http://restapi.adequateshop.com/api/Traveler/" + id;

        Response response = RestAssured.given().get(url);
        response.prettyPrint();

        System.out.println("========TASK 2 ================");
        String actualName = response.xmlPath().getString("Travelerinformation.name");
        String actualEmail = response.xmlPath().getString("Travelerinformation.email");

        Assert.assertNotNull("Email is null",actualEmail);
        Assert.assertNotNull("Name is null", actualName);
    }

    @Test
    public void test3_getListOfTravellers(){
        String url = "http://restapi.adequateshop.com/api/Traveler";
        Response response = RestAssured.given().get(url);

        int sizeOfList = response.xmlPath().getList("TravelerinformationResponse.travelers.Travelerinformation").size();


        for (int i = 0; i < sizeOfList; i++) {
            System.out.println("======================TEST STARTED==============================");
            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].id"));
            Assert.assertNotNull("ID is null", response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].id"));
            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].name"));
            Assert.assertNotNull("Name is null", response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].name"));
            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].email"));
            Assert.assertNotNull("Email is null", response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].email"));

            System.out.println("=====================TEST PASSED================================");

        }
    }
}
