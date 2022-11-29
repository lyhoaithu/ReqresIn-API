package stepsdefinition.LogIn;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;

import org.json.simple.parser.ParseException;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import junit.framework.Assert;
import io.cucumber.java.en.Then;

public class CheckValidationOfSingleFieldSteps {
	HttpResponse<String> response;
	String url= "https://reqres.in/api/login";
  @Given("User wants to log in the system")
  public void givenUserWantsToLogInTheSystem(){
  }

  @When("User enters field {string} with value {string}")
  public void whenUserEntersFieldWithValue(String fieldName, String value) throws IOException, ParseException, InterruptedException {
	  ApiUtils apiUtils= new ApiUtils();
	  JsonUtils jsonUtils = new JsonUtils();
	  File sourceFile= new File("D:\\AutomationTest\\02Projects\\AutomationPratice\\src\\main\\resources\\LogIn\\LogInRequestBody.json");
	  File destinationFile = new File("D:\\AutomationTest\\02Projects\\AutomationPratice\\src\\main\\resources\\LogIn\\LogInRequestBodyCopy.json");
	  jsonUtils.copyJsonFile(sourceFile, destinationFile);
	  String jsonBody=jsonUtils.changeFieldValueByFieldName(destinationFile, fieldName, value);
	  System.out.println(jsonBody);
	  response= apiUtils.sendPost(url, jsonBody);
  }

  @Then("The response status code should be {string} and response message should be {string}")
  public void then(String expectedStatusCode, String expectedMessage) throws ParseException{
	  int expectedStatusCodeInt=Integer.parseInt(expectedStatusCode);
	  int actualStatusCode= response.statusCode();
	  JsonUtils jsonUtils = new JsonUtils();
	  String actualMessage=null;
	  if(response.body().contains("token")) {
		  actualMessage=jsonUtils.getValueByKey(response.body(), "token");
	  }
	  else if(response.body().contains("error")) {
		  actualMessage=jsonUtils.getValueByKey(response.body(), "error");
	  }
	  Assert.assertEquals(expectedStatusCodeInt, actualStatusCode);
	  Assert.assertEquals(expectedMessage, actualMessage);
  }

}
