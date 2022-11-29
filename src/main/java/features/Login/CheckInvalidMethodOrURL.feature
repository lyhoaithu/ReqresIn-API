@Login
Feature: User log in the system

  @CheckInvalidMethodOrURL
  Scenario Outline: Check status code and response when send request with invalid method or using invalid URL
    Given I want to log in the system
     When I send the request using the url "<url>" and method "<method>"
     Then The response status code should be "<statusCode>"

    Examples: 
      | url                           | method | statusCode |
      | https://reqres.in/api/login   | GET    |        200 |
      | https://reqres.in/api/users/2 | POST   |        201 |
