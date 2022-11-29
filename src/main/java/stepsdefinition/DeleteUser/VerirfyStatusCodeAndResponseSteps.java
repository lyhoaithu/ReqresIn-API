package stepsdefinition.DeleteUser;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.junit.Assert;

import common.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class VerirfyStatusCodeAndResponseSteps {

	HttpResponse<String> response01;
	String url="https://reqres.in/api/users/2";
  @Given("I want to delete a specific user")
  public void givenIWantToDeleteASpecificUser() {
  }

  @When("I send the request with DELETE method")
  public void whenISendTheRequestWithDeleteMethod() throws IOException, InterruptedException {
	  ApiUtils apiUtils= new ApiUtils();
	  response02=apiUtils.sendDelete(url);
  }

  @Then("The status code should be 204 and the response is null")
  public void thenTheStatusCodeShouldBe204AndTheResponseIsNull(){
	  int expectedStatusCode=204;
	  String responseContent=null;
	  Assert.assertEquals(expectedStatusCode, response02.statusCode());
	  Assert.assertEquals(responseContent, response02.body());
  }


	
	HttpResponse<String> response02;
  @Given("I want to delete an user")
  public void givenIWantToDeleteAnUser() {
  }

  @When("I send the request using url {string} and method {string}")
  public void whenISendTheRequestWithInvalidMethod(String url, String method) throws IOException, InterruptedException {
	  ApiUtils apiUtils= new ApiUtils();
	  if (method.equals("GET")) {
		  response02=apiUtils.sendGet(url);
	  }
	  else if (method.equals("DELETE")) {
		  response02=apiUtils.sendDelete(url);
	  }
	  else if(method.equals("POST")) {
		  response02=apiUtils.sendPost(url, null);
	  }
	  else if(method.equals("PUT")) {
		  response02=apiUtils.sendPut(url, null);
	  }
  }

  @Then("The status code must be {string}")
  public void thenTheStatusCodeShouldBe405(String expectedStatusCode){
	  int expectedStatusCodeInt=Integer.parseInt(expectedStatusCode);
	  Assert.assertEquals(expectedStatusCodeInt, response02.statusCode());
  }
}
