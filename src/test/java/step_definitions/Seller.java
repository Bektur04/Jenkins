package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class Seller {

    @Given("{string} and parameters isArchived {string}, page {int}, size {int};")
    public void and_parameters_false_page_size(String path, String isArchived, Integer page, Integer size) {
        System.out.println(path);
        System.out.println(isArchived);
        System.out.println(page);
        System.out.println(size);

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", isArchived);
        params.put("page", page);
        params.put("size", size);


        APIRunner.runGET(path);
    }
    @Then("user validate phone numbers")
    public void user_validate_phone_numbers() {

        int sizeOfArray = APIRunner.getCustomResponse().getResponses().length;

        for (int i = 0; i < sizeOfArray; i++) {
            System.out.println("Phone number: " + APIRunner.getCustomResponse().getResponses()[i].getPhone_number());
            System.out.println("===========TEST PASSED===============");
        }
    }

}
