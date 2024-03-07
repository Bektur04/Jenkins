package day_6;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.APIRunner;

public class SellerApiRunnerTest {

    Faker faker = new Faker();

    @Test
    public void test_1_getListOfSellers(){
        //https://backend.cashwise.us  /api/myaccount/sellers/all
        String path = "/api/myaccount/sellers/all";

        /** TASK
         * use APIRunner and hit GET request
         * Create loop and Assert values:
         *          private int  seller_id;
         *          private String seller_name;
         */

        APIRunner.runGET(  path );
        CustomResponse[] customResponses = APIRunner.getCustomResponseArray();

        for ( int i=0;  i <  customResponses.length; i++  ){

            System.out.println("Seller ID: " + customResponses[i].getSeller_id());
            Assert.assertNotNull(customResponses[i].getSeller_id());

            System.out.println("Seller name" + customResponses[i].getSeller_name());
            Assert.assertNotNull(  customResponses[i].getSeller_name()  );



        }


    }


    @Test
    public void test_2_createSeller(){

        String path =  "/api/myaccount/sellers" ;


        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name( faker.company().name() );
        requestBody.setSeller_name( faker.name().fullName() );
        requestBody.setEmail( faker.internet().emailAddress() );
        requestBody.setPhone_number( faker.phoneNumber().cellPhone() );
        requestBody.setAddress( faker.address().fullAddress() );


        /**TASK
         * Create new Seller with ApiRunner
         * Get seller_id and print out
         * Assert seller_id is not null
         *  Assert seller_name is not null
         *
         */
        APIRunner.runPOST(path,requestBody);
        System.out.println(  APIRunner.getCustomResponse().getSeller_id() );
        System.out.println(  APIRunner.getCustomResponse().getSeller_name() );

        Assert.assertNotNull(  APIRunner.getCustomResponse().getSeller_id() );
        Assert.assertNotNull( APIRunner.getCustomResponse().getSeller_name() );


    }

}
