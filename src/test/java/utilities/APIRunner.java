package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import pojo.CustomResponse;
import pojo.RequestBody;

import java.util.Map;

import static utilities.CashWiseAuthorization.getToken;

public class APIRunner {
    private static CustomResponse customResponse;
    private static CustomResponse[] customResponseArray;


    public static CustomResponse runGET(String path){
        String url = Config.getProperty("baseUrl") + path;

        Response response = RestAssured.given()
                .auth().oauth2(getToken()).get(url);

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            System.out.println("This is a list response");
            try {
                customResponseArray = mapper.readValue(response.asString(),CustomResponse[].class);
            }catch (JsonProcessingException ex){
                throw new RuntimeException(ex);
            }
        }
        return  customResponse;
    }


    public static CustomResponse runPOST(String path , RequestBody requestBody){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .contentType(ContentType.JSON)
                .body( requestBody )
                .post( url );
        response.prettyPrint();

        // step - 3
        ObjectMapper mapper = new ObjectMapper();
        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        System.out.println(  "My status code: "+ response.statusCode() );
        return customResponse;
    }

    public static CustomResponse runGET(String path , Map<String,Object> params){

        String  url =Config.getProperty("baseUrl") + path;

        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .contentType(ContentType.JSON)
                .params( params )
                .get( url );

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        return customResponse;
    }



    public static CustomResponse runDELETE(String path ){
        // step - 1
        String  url =Config.getProperty("baseUrl") + path;
        // step - 2
        Response response = RestAssured.given()
                .auth().oauth2(   getToken()    )
                .delete( url );

        // step - 3
        ObjectMapper mapper = new ObjectMapper();
        // step -4
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class ) ;
        } catch (JsonProcessingException e) {
            // It's nested try-catch; Because we have to handle Array of ==> customResponseArray
            System.out.println( " This is a list response ");
            try {
                customResponseArray = mapper.readValue( response.asString(), CustomResponse[].class );
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }
        System.out.println(  "My status code: "+ response.statusCode() );
        return customResponse;
    }

    public static CustomResponse getCustomResponse(){
        return customResponse;
    }

    public static CustomResponse[] getCustomResponseArray(){
        return customResponseArray;
    }


}
