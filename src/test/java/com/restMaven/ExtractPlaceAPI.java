package com.restMaven;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.restMaven.resoures;
import com.restMaven.ReusableMethods;

public class ExtractPlaceAPI {

	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException 
	{
		FileInputStream fis = new FileInputStream ("/Users/Dayals/eclipse-workspace/RestDemo/src/files/env.properties");
		prop.load(fis);
		
	}
	@Test
	public void ResultName() {
		
		//BaseURL or Host
		RestAssured.baseURI = prop.getProperty("HOST");
		
		//given block
		Response res = given().
		param ("location",prop.getProperty("LOCATION")).
		param("radius",prop.getProperty("RADIUS")).
		param("key",prop.getProperty("KEY")).
		
		when().
		get(resoures.DataGet()).
		
		then().
	       assertThat().statusCode(200).
	       and().contentType(ContentType.JSON).
	       and().header("Server","scaffolding on HTTPServer2").
	       
	    extract().
	      response();
		
		JsonPath js = ReusableMethods.rawtoJson(res);
		int count = js.get("results.size()");
		
		for (int i=0;i<count;i++)
		{
			String s = js.get("results["+i+"].name");
			System.out.println(s);
		}

	}

}
