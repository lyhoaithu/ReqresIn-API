@CreateUser
Feature: Check Invalid URL
  I want to use this template for my feature file

  @CheckInvalidURL
  Scenario: Varify status code and response message
    Given I have the invalid URL and valid method
      | URL                         | method |
      | https://reqres.in/api/users | GET    |
     When I send out the request
     Then The status code should be 200
