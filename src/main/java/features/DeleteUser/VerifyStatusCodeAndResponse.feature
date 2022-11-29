@DeleteUser
Feature: Delete User
  I want to use this template for my feature file

  @VerifyStatusCodeAndResponse
      Scenario: Verify when send request with valid method and url
    Given I want to delete a specific user 
     When I send the request with DELETE method
     Then The status code should be 204 and the response is null
     
  Scenario Outline: Send request with invalid URL and send request with invalid method
    Given I want to delete an user
     When I send the request using url "<url>" and method "<method>"
     Then The status code must be "<statusCode>"

    Examples: 
      | url                            | method | statusCode |
      | https://reqres.in/api/users/2  | GET    |        405 |
      | https://reqres.in/api/register | DELETE |        404 |
