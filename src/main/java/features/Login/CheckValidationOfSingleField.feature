@LogIn
Feature: Check Validation Of Single Field

  @tag2
  Scenario Outline: Check Validation Of Single Field
    Given User wants to log in the system
     When User enters field "<fieldName>" with value "<value>"
     Then The response status code should be "<expectedStatusCode>" and response message should be "<expectedMessage>"

    Examples: 
      | fieldName | value              | expectedStatusCode | expectedMessage           |
      | email     | eve.holt@reqres.in |                200 | QpwL5tke4Pnpja7X4         |
      | email     | missing            |                400 | Missing email or username |
      | email     | null               |                400 | Missing email or username |
      | email     | acb@gmail.com      |                400 | user not found            |
      | password  | cityslicka         |                200 | QpwL5tke4Pnpja7X4         |
      | password  | missing            |                400 | Missing password          |
      | password  | null               |                400 | Missing password          |
      | password  | abc123             |                200 | QpwL5tke4Pnpja7X4         |
