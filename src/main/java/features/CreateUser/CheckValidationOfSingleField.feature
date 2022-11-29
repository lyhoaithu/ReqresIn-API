@CreateUser
Feature: Check Validation Of Single Field

  @CheckValidationOfSingleField
  Scenario Outline: Check Validation Of Single Field
    Given I want to create an user
     When I send the request with "<fieldName1>" value "<value1>" and "<fieldName2>" with "<value2>"
     Then The status code should be "<statusCode>" and message response should be "<responseMessage>"

    Examples: 
      | fieldName1 | value1   | fieldName2 | value2  | statusCode | responseMessage  |
      | name       | morpheus | job        | leader  |        201 | morpheus, leader |
      | name       | missing  | job        | tester  |        201 | tester           |
      | name       | lisa     | job        | missing |        201 | lisa             |
     
