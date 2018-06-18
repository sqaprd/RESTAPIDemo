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
//import files.Jiraresources;
import com.restMaven.ReusableMethods;

public class JiraAddComm {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getHost() throws IOException {
		FileInputStream fis = new FileInputStream("/Users/Dayals/eclipse-workspace/RestDemo/src/files/env.properties");
		prop.load(fis);	
	}
	
	@Test
	public void JiraAddComment() {
		//BaseURL or Host
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		
		Response res = given().
		header("Content-Type","application/json").
		header ("cookie","JSESSIONID="+ ReusableMethods.getJiraKey()).
		body(JiraPayLoad.InsertComment()).
		
		when().post("/rest/api/2/issue/"+ReusableMethods.getissueid()+"/comment").
		
		then().
		assertThat().statusCode(201).
		
		extract().response();
		
		JsonPath js = ReusableMethods.rawtoJson(res);
		String commentid = js.get("id");
		System.out.println(commentid);
		

	}

}
