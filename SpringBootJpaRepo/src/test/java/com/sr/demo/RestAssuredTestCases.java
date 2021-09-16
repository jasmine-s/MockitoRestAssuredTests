package com.sr.demo;

import static org.hamcrest.CoreMatchers.equalTo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredTestCases 
{
	//GET
	@Test
	public void test01()
	{
		Response res = RestAssured.get("https://reqres.in/api/users/3");
		
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void test02()
	{
		
		given().get("https://reqres.in/api/users/2")
		.then()
		.statusCode(200);
	}
	

	@Test
	public void test03()
	{
		
	RestAssured.baseURI="https://reqres.in/api/users";
	RequestSpecification httpRequest = RestAssured.given();
	
	Response res = httpRequest.get("/4");
	
	JsonPath jsonObj = res.jsonPath();
	
	System.out.println(jsonObj);
	
	given().get("/4").then().body("data.id", equalTo(4));
	given().get("/4").then().body("data.first_name", equalTo("Eve"));
	given().get("/4").then().body("data.last_name", equalTo("Holt"));
	given().get("/4").then().body("data.email", equalTo("eve.holt@reqres.in"));
	given().get("https://reqres.in/api/users/4").then().contentType(MediaType.APPLICATION_JSON_VALUE);
	
	}


	@Test
	public void test04()
	{
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response res = httpRequest.request(Method.GET,"/posts/10");
		
		String responseBody = res.getBody().asString();
		String contentType = res.getContentType();
		
		Assert.assertEquals(contentType,"application/json; charset=utf-8");
		
		
		 
	}
	//GET
	@Test
	public void getTest()
	{
		
	RestAssured.baseURI="https://reqres.in/api/users";
	RequestSpecification httpRequest = RestAssured.given();
	
	Response res = httpRequest.get("/5");
	
	JsonPath jsonObj = res.jsonPath();
	
	System.out.println(jsonObj);
	
	given().get("/5").then().body("data.id", equalTo(5));
	given().get("/5").then().body("data.first_name", equalTo("Charles"));
	given().get("/5").then().body("data.last_name", equalTo("Morris"));
	given().get("/5").then().body("data.email", equalTo("charles.morris@reqres.in"));
	given().get("https://reqres.in/api/users/5").then()
	.contentType(MediaType.APPLICATION_JSON_VALUE);
	
	}
	//POST
	
	 @Test
	    public void postRequest() throws JSONException 
	 {
		 RequestSpecification req = RestAssured.given();
		 req.header("Content-Type","application/json");
		 JSONObject json=new JSONObject();
	     json.put("id", "550");
	     json.put("first_name", "JASMINE");
	     json.put("last_name", "SHAIK");
	     json.put("email","jas.shaik@gmail.com");
	     req.body(json.toString());
	     Response res = req.post("https://reqres.in/api/posts");
	     int status = res.getStatusCode();
	     Assert.assertEquals(status, 201);
	 }
	 
	//PUT
	 @Test
	    public void putTest() throws JSONException 
	 {
		 RequestSpecification req = RestAssured.given();
		 req.header("Content-Type","application/json");
		 JSONObject json=new JSONObject();
	     json.put("id", "550");
	     json.put("first_name", "JASMINE");
	     json.put("last_name", "SHAIK");
	     json.put("email","jasshaik@gmail.com");
	     req.body(json.toString());
	     Response res = req.put("https://reqres.in/api/posts/550");
	     int status = res.getStatusCode();
	     Assert.assertEquals(status, 200);
	     
	 }
	 
	 //DELETE
	 @Test
	    public void DeleteTest() 
	 {
		 RequestSpecification req = RestAssured.given();
		 Response res = req.delete("https://reqres.in/api/posts/3");
	     int status = res.getStatusCode();
	     Assert.assertEquals(status, 204);
	     
	     
	 }
	
}

