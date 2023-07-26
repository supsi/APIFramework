package stepDefinition.datadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.datadriven.GetLocation;
import pojo.datadriven.location;
import io.restassured.path.json.JsonPath;
import resources.datadriven.APIResources;
import resources.datadriven.TestDataBuild;
import resources.datadriven.Utils; 

public class stepDefinition extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec ;
	Response response;
	TestDataBuild tdb = new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address)  throws IOException {
	    // Write code here that turns the phrase above into concrete actions


		 
		 
		 
		
		 res=given().spec(requestSpecification())
		.body(tdb.AddPlaceData(name,language,address));
		

	}
	@When("user calls {string} with {string} htpps request")
	public void user_calls_with_htpps_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
	
		//constructor will be called with value of resource which you will pass 
		APIResources resAPI=APIResources.valueOf(resource);
		System.out.println(resAPI.getResource());
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		response =res.when().post(resAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response =res.when().get(resAPI.getResource());
			
				

				
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response code is {string}")
	public void in_response_code_is(String keyValue, String ExpectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	
	    assertEquals(getJsonPath(response,keyValue ),ExpectedValue);
	}
	
	@Then("verify place_Id  created maps to {string}  using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		place_id=getJsonPath(response,"place_id");
	   res =given().spec(requestSpecification()).queryParam("place_id", place_id);
	   user_calls_with_htpps_request(resource,"GET");
	   String actualname =getJsonPath(response,"name");
	   assertEquals(actualname,expectedname);
	   
	   
		
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    res=given().spec(requestSpecification()).body(tdb.DeletePlacePayload(place_id));
	}

}
