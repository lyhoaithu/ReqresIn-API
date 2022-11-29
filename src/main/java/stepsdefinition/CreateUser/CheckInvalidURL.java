package stepsdefinition.CreateUser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import common.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class CheckInvalidURL {
	String url, method;
	HttpResponse<String> response;
  @Given("I have the invalid URL and valid method")
  public void givenIHaveTheInvalidURLAndValidMethod(List<Map<String,String>> DataTable) {
	  for(Map<String,String> columnData:DataTable) {
		   url = columnData.get("URL");
		   method =columnData.get("method");
  }
  }
  
  @When("I send out the request")
  public void whenISendTheRequest() throws IOException, InterruptedException {
	  ApiUtils apiUtils=new ApiUtils();
	  response=apiUtils.sendGet(url);
  }

  @Then("The status code should be 200")
  public void thenTheStatusCodeShouldBe200() {
	  int actualStatusCode=response.statusCode();
	  int expectedStatusCode=200;
	  Assert.assertEquals(expectedStatusCode, actualStatusCode);
  }

}
