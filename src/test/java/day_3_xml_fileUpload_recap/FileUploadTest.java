package day_3_xml_fileUpload_recap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashWiseAuthorization;

import java.io.File;

public class FileUploadTest {
    @Test
    public void fileUpload(){
        String url = "https://backend.cashwise.us/api/myaccount/file/v2";
        String token = CashWiseAuthorization.getToken();

        String filePath = "Data_driven_testing1 - Sheet1.csv";

        File file = new File(filePath);
        Response response = RestAssured.given()
                .auth().oauth2(token)
                .multiPart("file", file,"multiple/form-data")
                .post(url);
        System.out.println("Status code: " + response.statusCode());
        String fileName = response.jsonPath().getString("fileName");
        System.out.println("My file name from response body: " + fileName);
        response.prettyPrint();
    }
}
