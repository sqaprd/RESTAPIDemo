package com.restMaven;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

//import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;

import com.restMaven.resoures;
import com.restMaven.ReusableMethods;

public class PostDemoxml {
	
	Properties prop = new Properties();
	
	
	@BeforeTest
	public void getData() throws IOException {
	
		FileInputStream fis = new FileInputStream ("/Users/Dayals/eclipse-workspace/RestDemo/src/files/env.properties");
		prop.load(fis);
		
	}

	
	@Test
	public void PostDataXml() throws IOException
	{
		
		String PostData = GenerateStringFromResource ("/Users/Dayals/eclipse-workspace/RestDemo/postdata.xml");
		//BaseURL or Host
		RestAssured.baseURI =prop.getProperty("HOST");
		
		//given block	
	   Response res = given().
	   queryParam("key",prop.getProperty("KEY")).
	   body(PostData).
	
	   when().
	   post(resoures.PostXMLData()).
	
	   then().
	   assertThat().statusCode(200).
       and().contentType(ContentType.XML).
       extract().
       response(); 
       XmlPath x = ReusableMethods.rawtoXML(res);
       String s = x.get("PlaceAddResponse.place_id");
       System.out.println(s);
     
     	
	}
    public static String GenerateStringFromResource (String path) throws IOException {
    	        
    	         return new String (Files.readAllBytes(Paths.get(path)));
    	       
    }

}
