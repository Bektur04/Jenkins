package day_7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.CashWiseAuthorization;
import utilities.Config;

public class PaymentPractice {

    @Test
    public void test1() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/payments/207";


        Response response = RestAssured.given().auth().oauth2(CashWiseAuthorization.getToken()).contentType(ContentType.JSON)
                .get(url);


        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println("Invoice title: " + customResponse.getResponse().getInvoice_title());
        System.out.println("Total Pay: " + customResponse.getResponse().getTotal_pay());


    }
}
