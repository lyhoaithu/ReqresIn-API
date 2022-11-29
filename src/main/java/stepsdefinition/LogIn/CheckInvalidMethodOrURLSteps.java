package stepsdefinition.LogIn;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.json.simple.parser.ParseException;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import io.cucumber.java.en.Then;

public class CheckInvalidMethodOrURLSteps {
	HttpResponse<String> response;
  @Given("I want to log in the system")
  public void givenIWantToLogInTheSystem(){
  }

  @When("I send the request using the url {string} and method {string}")
  public void whenISendTheRequestUsingTheUrlAndMethod(String url, String method) throws IOException, ParseException, InterruptedException {
	  ApiUtils apiUtils= new ApiUtils();
	  JsonUtils jsonUtils = new JsonUtils();
	  if(method.equals("POST")) {
		  String jsonBody= jsonUtils.readJsonFile("D:\\Automation Test\\02Projects\\AutomationPratice\\src\\main\\resources\\LogIn\\LogInRequestBody.json");
		  response=apiUtils.sendPost(url, jsonBody);
	  }
	  else if (method.equals("GET")) {
		  response=apiUtils.sendGet(url);
	  }
  }

  @Then("The response status code should be {string}") 
  public void thenTheStatusCodeShouldBe(String statusCode){
	  int expectedStatusCode= Integer.parseInt(statusCode);
	  int actualStatusCode= response.statusCode();
	  Assert.assertEquals(expectedStatusCode, actualStatusCode);
  }

}
