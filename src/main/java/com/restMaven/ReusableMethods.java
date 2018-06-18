package com.restMaven;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import com.restMaven.JiraPayLoad;
import com.restMaven.Jiraresources;

public class ReusableMethods {
	
	public static XmlPath rawtoXML(Response r) {
		String resp = r.asString();
		XmlPath x = new XmlPath(resp);
		return x;
	}
	public static JsonPath rawtoJson(Response r) 
	{
		String resp = r.asString();
		JsonPath js = new JsonPath(resp);
		return js;
	}
public static String getJiraKey() {
	
	RestAssured.baseURI = "http://localhost:8080";
	
	Response res = given().
	header("Content-Type","application/json").
	body(JiraPayLoad.Login()).
	
	when().post(Jiraresources.Authentication()).
	
	then().
	assertThat().statusCode(200).
	
	extract().response();
	
	JsonPath js = ReusableMethods.rawtoJson(res);
	String sessionid = js.get("session.value");
	return sessionid;
}

public static String getissueid() {
	
		//BaseURL or Host
		RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().
		header("Content-Type","application/json").
		header ("cookie","JSESSIONID="+ ReusableMethods.getJiraKey()).
        body(JiraPayLoad.AddIssue()).
		
		when().post(Jiraresources.PostIssue()).
		
		then().
		assertThat().statusCode(201).
		
		extract().response();
		
		JsonPath js = ReusableMethods.rawtoJson(res);
		String key = js.get("key");
		return key;
}

}
