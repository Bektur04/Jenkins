package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {

    //Category-controller
    private CustomResponse[] category_response;
    private int categoryId;
    private String category_title;
    private String category_description;


    //Bank account
    private String id;
    private String bank_account_name;
    private String description;
    private int balance;

    //Sellers Response body var
    private CustomResponse[] responses;
    private int seller_id;
    private String company_name;
    private String seller_name;
    private String phone_number;
    private String email;
    private String address;

    //Product
    private int product_id;
    private String product_title;
    private double product_price;
    private String product_description;

    //Payment Controller
    private CustomResponse response;
    private String invoice_title;
    private double total_pay;

}
