package com.restMaven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import com.restMaven.ReusableMethods;

public class JiraDelete {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getHost() throws IOException {
		FileInputStream fis = new FileInputStream("/Users/Dayals/eclipse-workspace/RestDemo/src/files/env.properties");
		prop.load(fis);	
	}
	
	@Test
	public void DeleteIssue() {
		//BaseURL or Host
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		
		given().
		header("Content-Type","application/json").
		header ("cookie","JSESSIONID="+ ReusableMethods.getJiraKey()).
		
		when().delete("/rest/api/2/issue/RESTTEST-40").
		
		then().
		assertThat().statusCode(204);
		

	}
	
}
