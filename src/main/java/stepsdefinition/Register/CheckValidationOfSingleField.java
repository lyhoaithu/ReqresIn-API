package stepsdefinition.Register;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import io.cucumber.java.en.Then;

public class CheckValidationOfSingleField<JSONString> {
	String url="https://reqres.in/api/register";
	HttpResponse<String> response;
  @Given("I want to create an account")
  public void givenIWantToCreateAnAccount() {
  }

  @When("I fill in field {string} with value {string}")
  public void whenIFillInTheFieldWithValue(String fieldName, String value) throws IOException, ParseException, InterruptedException {
	  ApiUtils apiUtils = new ApiUtils();
	  JsonUtils jsonUtils = new JsonUtils();
	  File sourceFile= new File("D:\\Automation Test\\02Projects\\AutomationPratice\\src\\main\\resources\\Register\\RegisterRequestBody.json");
	  File destinationFile = new File ("D:\\Automation Test\\02Projects\\AutomationPratice\\src\\main\\resources\\Register\\RegisterRequestBodyCopy.json");
	  jsonUtils.copyJsonFile(sourceFile, destinationFile);
	  String jsonRequestBody=jsonUtils.changeFieldValueByFieldName(destinationFile, fieldName, value);
	  response= apiUtils.sendPost(url, jsonRequestBody);
  }

  @Then("I clicks enter. The status code should be {string} and response message should be {string}")
  public void thenIClicksEnter(String expectedStatusCode, JSONString expectedMessage) throws ParseException  {
	  int expectedStatusCodeInt = Integer.parseInt(expectedStatusCode);
	  int actualStatusCode= response.statusCode();
	  JsonUtils jsonUtils= new JsonUtils();
	  String responseBody=response.body();
	  String actualMessage=null;
	  if(responseBody.contains("error")) {
		  actualMessage=jsonUtils.getValueByKey(responseBody, "error");
	  }
	  else {
		 JSONParser parser = new JSONParser();
		 JSONObject jsonObj= (JSONObject)parser.parse(responseBody);
		 actualMessage= jsonUtils.checkErrorMessage(jsonObj.toJSONString());
		  }
	
	 Assert.assertEquals(expectedStatusCodeInt, actualStatusCode);
	 Assert.assertEquals(expectedMessage, actualMessage);
  }

}
