package day_6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.CashWiseAuthorization;
import utilities.Config;
import static utilities.CashWiseAuthorization.getToken;
public class GetSingleData {


    String bankID = "";


    @Test
    public void test_1_getSingleBankAccount() throws JsonProcessingException {
        bankID = "1202";
        System.out.println("====REGULAR method==============================");
        //Step - 1   // baseUrl                            path
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + bankID;

        //Step - 2 Hit GET request
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .get( url );

        //response.prettyPrint();

        //Step - 3 create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //Step - 4 create CustomResponse class and Handle exception
        CustomResponse customResponse = mapper.readValue( response.asString(), CustomResponse.class );

        System.out.println(  "My ID: "+  customResponse.getId()  );
        System.out.println(  "Bank account Name: "+  customResponse.getBank_account_name()  );
        System.out.println(  "Balance: "+  customResponse.getBalance() );


    }


    @Test
    public void test_2_getSingleBankAccount(){
        // https://backend.cashwise.us  /api/myaccount/bankaccount/1202
        System.out.println("===========APIRunner =================================================");
        String path = "/api/myaccount/bankaccount/1202";

        APIRunner.runGET(  path );

        String bankId =  APIRunner.getCustomResponse().getId();
        String bankAccountName = APIRunner.getCustomResponse().getBank_account_name();
        double balance = APIRunner.getCustomResponse().getBalance() ;

        System.out.println(  "My ID: "+  bankId  );
        System.out.println(  "Bank account Name: "+  bankAccountName  );
        System.out.println(  "Balance: "+  balance);


    }

    /** TASK
     *  Get single Seller
     * Create Object mapper
     * Create CustomResponse
     * GET seller_id
     *      seller_name
     *  https://backend.cashwise.us   /api/myaccount/sellers/3465
     */

    @Test
    public void test_3_getSingleSeller() throws JsonProcessingException {
        // https://backend.cashwise.us   /api/myaccount/sellers/3465
        String url  = Config.getProperty("baseUrl")+  "/api/myaccount/sellers/3465" ;

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .get(  url );

        ObjectMapper mapper = new ObjectMapper( );

        CustomResponse customResponse = mapper.readValue(  response.asString(), CustomResponse.class );

        System.out.println( "Seller id: "+  customResponse.getSeller_id()  );
        System.out.println(  "Seller name: "+  customResponse.getSeller_name()  );
    }
    @Test
    public void test_4_getSingleSeller() throws JsonProcessingException {
        String path = "/api/myaccount/sellers/3465";
        APIRunner.runGET(path);
        System.out.println(  "Seller id: " +  APIRunner.getCustomResponse().getSeller_id()   );
        Assert.assertNotNull(   APIRunner.getCustomResponse().getSeller_id()  );
        System.out.println(  "Seller name: " +  APIRunner.getCustomResponse().getSeller_name()   );
        Assert.assertNotNull(    APIRunner.getCustomResponse().getSeller_name()  );

    }

    @Test
    public void test_5_getSingleProduct(){
        // https://backend.cashwise.us    /api/myaccount/products/796
        String  path = "/api/myaccount/products/796";
        APIRunner.runGET(path );
        System.out.println(    APIRunner.getCustomResponse().getProduct_id()   );
        System.out.println(    APIRunner.getCustomResponse().getProduct_title()   );
        System.out.println(    APIRunner.getCustomResponse().getProduct_price()   );

        Assert.assertNotNull(    APIRunner.getCustomResponse().getProduct_id()  );
        Assert.assertNotNull(    APIRunner.getCustomResponse().getProduct_title() );
        Assert.assertNotNull(   APIRunner.getCustomResponse().getProduct_price() );

    }



}
