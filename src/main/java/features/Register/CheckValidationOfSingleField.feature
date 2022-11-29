@Register
Feature: Check Validation Of Single Field
  I want to use this template for my feature file

  @CheckValidationOfSingleField
  Scenario Outline: User register an account
    Given I want to create an account
     When I fill in field "<fieldName>" with value "<value>"
     Then I clicks enter. The status code should be "<expectedStatusCode>" and response message should be "<expectedMessage>"

    Examples: 
      | fieldName | value              | expectedStatusCode | expectedMessage                               |
      | email     | eve.holt@reqres.in |                200 | {id:4,token:QpwL5tke4Pnpja7X4}       |
      | email     | missing            |                400 | Missing email or username                     |
      | email     | null               |                400 | Missing email or username                     |
      | email     |                  1 |                400 | Note: Only defined users succeed registration |
      | password  | cityslicka         |                200 | {id:4,token:QpwL5tke4Pnpja7X4}         |
      | password  | missing            |                400 | Missing password                              |
      | password  | null               |                400 | Missing password                              |
