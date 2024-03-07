package pojo;

import lombok.Data;

@Data
public class RequestBody {

    //CashWise Authorization
    private String email;
    private String password;


    //Category controller
    private String category_title;
    private String category_description;
    private boolean flag;

    //Bank Account
    private String type_of_pay;
    private String bank_account_name;
    private String description;
    private double balance;

    //Seller Controller
    private String company_name;
    private String seller_name;
    private String phone_number;
    private String address;

    //Product controller
    private String product_title;
    private double product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;


    /**
     * {
     *     "seller_id": 3457,
     *     "company_name": "Woodman",
     *     "seller_name": "Sach Sci",
     *     "seller_surname": null,
     *     "email": "sach@gmail.com",
     *     "phone_number": "+184784780",
     *     "address": "Chicago, IL",
     *     "created": "2024-01-19",
     *     "income": false,
     *     "number_of_invoices": 0
     * }
     */

//    public String getCategory_title() {
//        return category_title;
//    }
//
//    public void setCategory_title(String category_title) {
//        this.category_title = category_title;
//    }
//
//    public String getCategory_description() {
//        return category_description;
//    }
//
//    public void setCategory_description(String category_description) {
//        this.category_description = category_description;
//    }
//
//    public boolean isFlag() {
//        return flag;
//    }
//
//    public void setFlag(boolean flag) {
//        this.flag = flag;
//    }
//
//
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }




}
