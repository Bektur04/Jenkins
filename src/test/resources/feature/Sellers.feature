Feature: Test Sellers
  @seller
  Scenario: Verify Get All Sellers and Validate Phone Numbers
    Given "/api/myaccount/sellers" and parameters isArchived "false", page 1, size 10;
    Then user validate phone numbers