package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	@Given("Goto to url")
	public void goto_to_url() {
	    // Write code here that turns the phrase above into concrete actions
	   RestAssured.baseURI="https://gorest.co.in/";
	   res=given().header("Content-Type","application/json");			   
	}

	@When("User calls {string} with http get request")
	public void user_calls_with_http_get_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		response =res.when().get("public-api/users").then().extract().response();		
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
		System.out.println("Result - Get users Information ------");
		System.out.println(response.asString());
	}

//	@And("{string} in response body is {string}")
//	public void in_response_body_is(String key, String ExpValue) {
//	    // Write code here that turns the phrase above into concrete actions
//		String resp=response.asString();
//		JsonPath js=new JsonPath(resp);
//		System.out.println("****************");
//		System.out.println(js.get(key).toString());
//		System.out.println(ExpValue);
//		assertEquals(js.get(key).toString(),ExpValue);
//	}
	
	@Given("Goto to url and search {string}")
	public void goto_to_url_and_search(String string) {
	   
		RestAssured.baseURI="https://gorest.co.in/";
		res=given().header("Content-Type","application/json")
				.header("Authorization","Bearer " +"be4a245fdade7154be5e40b9e4f35ca2ea304b4567f0b38e31a5a20137afd0f0").body("{\r\n" + 
						"    \"email\": \"test@test.com\"\r\n" + 
						"}");		
	}

	
	@When("Update {string} with http PUT request")
	public void update_with_http_PUT_request(String user) {
	    // Write code here that turns the phrase above into concrete actions
		response=res.when().put("public-api/users/13").then().extract().response();
		System.out.println("2nd test case result - Get updated user Information ------");
		System.out.println(response.toString());
	    
	}

	@Then("verify the {string}")
	public void verify_the(String ExpValue) {
	    // Write code here that turns the phrase above into concrete actions
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);		
		System.out.println("Actual email : "+js.get("data.email").toString());
		System.out.println("Expected email :"+ExpValue);
		assertEquals(js.get("data.email").toString(),ExpValue);
	}
	
	@When("Filter {string} with http get request")
	public void filter_with_http_get_request(String userStatus) {
	    // Write code here that turns the phrase above into concrete actions
		res.pathParam("user", userStatus);
		response=res.when().get("public-api/users?status={user}").then().extract().response();
	}

	@Then("Get {string} count")
	public void get_user_count(String userStatus) {
	    // Write code here that turns the phrase above into concrete actions
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		System.out.println(userStatus+"users count : "+js.getInt("data.size()"));		
	}


}
