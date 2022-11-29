package stepsdefinition.GetUser;

import java.net.http.HttpResponse;

import org.junit.Assert;

import common.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class GetUserStepDefinition {
	String url;
	HttpResponse<String> response;
  @Given("I have the url {string} and method GET")
  public void givenIHaveTheUrlAndMethodGET(String url) throws Throwable {
	  this.url= url;
  }

  @When("I send the request")
  public void whenISendTheRequest() throws Throwable {
	  ApiUtils apiUtil = new ApiUtils();
	  response=apiUtil.sendGet(url);
  }

  @Then("The status code should be {string}")
  public void thenIVerifyTheStatusCode(String expectedStatusCode) throws Throwable {
	  int actualStatusCode= response.statusCode();
	  int expectedStatusCodeInt= Integer.parseInt(expectedStatusCode);
	 Assert.assertEquals(expectedStatusCodeInt, actualStatusCode);
  }

}
