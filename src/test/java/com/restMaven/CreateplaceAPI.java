package com.restMaven;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

import com.restMaven.payLoad;
import com.restMaven.resoures;

public class CreateplaceAPI {
	
	Properties prop = new Properties();
	
	
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis = new FileInputStream ("/Users/Dayals/eclipse-workspace/RestDemo/src/files/env.properties");
		prop.load(fis);
		
	}

	
	@Test
	public void PostData()
	{
		//BaseURL or Host
		RestAssured.baseURI =prop.getProperty("HOST");
		
		//given block	
	    given().
	   queryParam("key",prop.getProperty("KEY")).
	   body(payLoad.placePostData()).log().all().
	
	   when().
	   post(resoures.PostData()).
	
	   then().
	   assertThat().statusCode(200).
       and().contentType(ContentType.JSON).
       and().body("status",equalTo("OK"));
	
	   //Create a place and delete that place in one Script
	  //First add a place and extract the place_id from the response and pass it into a variable
	  //Use the place_id from "add place" to delete the place
	
		
	}

}
