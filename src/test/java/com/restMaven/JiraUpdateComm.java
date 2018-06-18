package com.restMaven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import com.restMaven.JiraPayLoad;
import com.restMaven.ReusableMethods;

public class JiraUpdateComm {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getHost() throws IOException {
		FileInputStream fis = new FileInputStream("/Users/Dayals/eclipse-workspace/RestDemo/src/files/env.properties");
		prop.load(fis);	
	}
	
	@Test
	public void JiraUpdate() {
		//BaseURL or Host
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		
		Response res = given().
		header("Content-Type","application/json").
		header ("cookie","JSESSIONID="+ ReusableMethods.getJiraKey()).
		pathParams("commentid","10106").
		body(JiraPayLoad.UpdateComment()).
		
		when().put("/rest/api/2/issue/RESTTEST-43/comment/{commentid}").
		
		then().
		assertThat().statusCode(200).
		
		extract().response();
		
		JsonPath js = ReusableMethods.rawtoJson(res);
		String id = js.get("id");
		System.out.println(id);
		

	}

}
