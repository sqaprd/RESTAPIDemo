package com.restMaven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.restMaven.payLoad;
import com.restMaven.resoures;

public class AddandDelete {
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream ("/Users/Dayals/eclipse-workspace/RestDemo/src/files/env.properties");
		prop.load(fis);	
	}
	
	
    @Test
    public void AddDelete() {
	
	    // Task1 - Add a Place and Grab the response
	    
    	    //BaseURL or Host
      	RestAssured.baseURI = prop.getProperty("HOST");
		
		//given block	
		Response res = given().
		queryParam("key",prop.getProperty("KEY")).
		body(payLoad.placePostData()).
		
		when().
		post(resoures.PostData()).
		
		then().
		assertThat().statusCode(200).
	    and().contentType(ContentType.JSON).
	    and().body("status",equalTo("OK")).
	    extract().response();
		
		
		 //Task2 - Extract placeid from the response. When you extract the response, 
		//it comes in raw format, which we need to convert
		//to string and use this string to convert to JSON format
	    
		String responseString = res.asString();
	    JsonPath js = new JsonPath(responseString);
	    String placeid = js.get("place_id");
	    System.out.println(placeid);
	    
	    
	    
	    //Task3 - Delete the place using the place ID
	    
	 
	    //given block
	   Response res1 = given().
	    queryParam("key",prop.getProperty("KEY")).
	    body("{" + 
	    		"\"place_id\": \" "+placeid+" \" "+  
	    		"}").
	    when().
	    post(resoures.DeleteData()).
	    then().
	    assertThat().statusCode(200).
	    and().contentType(ContentType.JSON).
	    and().body("status",equalTo("OK")).
	    extract().response();
	    String deleteResponse = res1.asString();
	    System.out.println(deleteResponse);
	   
	    
}

}
