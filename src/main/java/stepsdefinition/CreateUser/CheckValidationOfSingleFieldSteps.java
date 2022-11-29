package stepsdefinition.CreateUser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class CheckValidationOfSingleFieldSteps {
	 String requestBodyFinal;
	String url="https://reqres.in/api/users";
	 JSONArray array;
	HttpResponse<String> response;
  @Given("I want to create an user")
  public void givenIWantToCreateAnUser(){
  }

  @When("I send the request with {string} value {string} and {string} with {string}")
  public void whenISendTheRequest(String fieldName1, String value1, String fieldName2,  String value2) throws IOException, ParseException, InterruptedException {
	  JsonUtils jsonUtils = new JsonUtils();
	  ApiUtils apiUtils= new ApiUtils();
	  File rootFile = new File("D:\\Automation Test\\02Projects\\AutomationPratice\\src\\main\\resources\\CreateUser\\CreateUserRequestBody.json");
	  File destinationFile = new File("D:\\Automation Test\\02Projects\\AutomationPratice\\src\\main\\resources\\CreateUser\\CreateUserRequestBodyCopy.json");
	     jsonUtils.copyJsonFile(rootFile, destinationFile);
	     String filePath=destinationFile.getAbsolutePath();
	 	String originalFile = new String (Files.readAllBytes(Paths.get(filePath)));
	 	JSONParser parser = new JSONParser();
	 	JSONObject jsonObj= (JSONObject) parser.parse(originalFile);
	 	
	 	if (value1.equals("missing")){
	 		jsonObj.remove(fieldName1);
	 	   jsonObj.put(fieldName2, value2);
	 	}
	 	
	 	else if (value2.equals("missing")) {
	 		jsonObj.remove(fieldName2);
	 		jsonObj.put(fieldName1, value1);
	 	}
	 	
	 	
	 	else if (value1.equals("null")) {
	 		jsonObj.put(fieldName1, null);
	 	}
	 	
	 	else if (value2.equals("null")) {
	 		jsonObj.put(fieldName2, null);
	 	}
	 	else  {
	 		jsonObj.put(fieldName1, value1);
	 		jsonObj.put(fieldName2, value2);
	 	}
	 	String resultFile=jsonObj.toJSONString();
	 	response=apiUtils.sendPost(url, resultFile);
	 }
	

  

  @Then("The status code should be {string} and message response should be {string}")
  public void thenValidateStatusCodeAndMessageResponse(String expectedstatusCode, String expectedmessageResponse) throws ParseException  {
	 JsonUtils jsonUtils= new JsonUtils();
	 int expectedStatusCodeInt=Integer.parseInt(expectedstatusCode);
	 String responseBody=response.body();
	 JSONParser parser=new JSONParser();
	 JSONObject jsonObj=(JSONObject) parser.parse(responseBody);
	 String responseBodyContent=jsonObj.toJSONString();
	 
	 ArrayList<String> actualResult = new ArrayList<>();
	
	 
	  if (responseBodyContent.contains("job")&&responseBodyContent.contains("name")){
		 actualResult.add(jsonUtils.getValueByKey(responseBodyContent, "name"));
		 actualResult.add(jsonUtils.getValueByKey(responseBodyContent, "job"));
	 }
	  else if (responseBodyContent.contains("job")) {
		  actualResult.add(jsonUtils.getValueByKey(responseBodyContent, "job"));
	  }
	  else if (responseBodyContent.contains("name")) {
		  actualResult.add(jsonUtils.getValueByKey(responseBodyContent, "name"));
	  }
	 String actualResultString= actualResult.toString();
	 String actualMessage= actualResultString.substring(1, (actualResultString.length()-1));
	 Assert.assertEquals(expectedStatusCodeInt, response.statusCode());
	 Assert.assertEquals(expectedmessageResponse, actualMessage);
	 
  }
}
