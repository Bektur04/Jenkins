package day_7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class SellerIsArchivedTest {

    @Test
    public void test1() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        Response response = RestAssured.given()
                .auth().oauth2(CashWiseAuthorization.getToken())
                .contentType(ContentType.JSON)
                .params(params)
                .get(url);

        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();

        CustomResponse customResponses = mapper.readValue(response.asString(), CustomResponse.class);

        // it is returning to as Array of CustomResponse ( under variable responses we have Array of Sellers)
        int sizOfArray = customResponses.getResponses().length;

        for (int i = 0; i < sizOfArray; i++) {

            System.out.println("Seller id: " + customResponses.getResponses()[i].getSeller_id());
            Assert.assertNotNull(customResponses.getResponses()[i].getSeller_id());

            System.out.println("Seller id: " + customResponses.getResponses()[i].getSeller_name());
            Assert.assertNotNull(customResponses.getResponses()[i].getSeller_name());
        }
    }

    @Test
    public void test_2_gerAllisArchivedSellers_ApiRunner() {
        String path = "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        APIRunner.runGET(path, params);

        // int sizOfArray = APIRunner.getCustomResponse().getResponses().length;

        CustomResponse[] customResponseArrayOfSellers = APIRunner.getCustomResponse().getResponses();
        int sizOfArray = customResponseArrayOfSellers.length;

        for (int i = 0; i < sizOfArray; i++) {

            System.out.println("Seller Id: " + customResponseArrayOfSellers[i].getSeller_id());
            Assert.assertNotNull(customResponseArrayOfSellers[i].getSeller_id());

            System.out.println("Seller Name: " + customResponseArrayOfSellers[i].getSeller_name());
            Assert.assertNotNull(customResponseArrayOfSellers[i].getSeller_name());

        }

    }

    @Test
    public void test3(){
        String path = "api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        APIRunner.runGET(path, params);
            int sizeOfArray = APIRunner.getCustomResponse().getResponses().length;

            for (int i = 0; i < sizeOfArray; i++) {
                System.out.println(  "Seller Id: " +    APIRunner.getCustomResponse().getResponses()[i].getSeller_id()   );
                Assert.assertNotNull(  APIRunner.getCustomResponse().getResponses()[i].getSeller_id() );

                System.out.println(  "Seller Name: " + APIRunner.getCustomResponse().getResponses()[i].getSeller_name() );
                Assert.assertNotNull( APIRunner.getCustomResponse().getResponses()[i].getSeller_name()  );
            }
        }
    }
