Feature: user should be able to create a client
  Scenario: verify client is created
    Given new client is created using API
    Then verify new client is created
    And delete client in database
    Then verify client does not exist using API