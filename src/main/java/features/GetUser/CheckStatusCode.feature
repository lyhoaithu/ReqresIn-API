@GetUser
Feature: Get Single User/List User

  @CheckStatusCode
  Scenario Outline: Check Status Code
    Given I have the url "<url>" and method GET
     When I send the request
     Then The status code should be "<statusCode>"

    Examples: 
      | url                                 | statusCode |
      | https://reqres.in/api/users?page=2  |        200 |
      | https://reqres.in/api/users/2       |        200 |
      | https://reqres.in/api/users/23      |        404 |
      | https://reqres.in/api/unknown       |        200 |
      | https://reqres.in/api/unknown/2     |        200 |
      | https://reqres.in/api/unknown/23    |        404 |
      | https://reqres.in/api/users?delay=3 |        200 |
