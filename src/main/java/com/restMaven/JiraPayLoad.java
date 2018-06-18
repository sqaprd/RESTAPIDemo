package com.restMaven;

public class JiraPayLoad {
	
	public static String AddIssue() {
		
		 String body =  "{" +
			    "\"fields\": { "+
			        "\"project\": {"+
			            "\"key\": \"RESTTEST\" },"+
			        "\"summary\": \"Second bug through RESTassured\","+
			        "\"issuetype\": {"+
			            "\"name\": \"Bug\" " +
			        "},"+
			        "\"description\": \"Creating first issue using RESTassured\" "+
			    "}" +
			"}";
		 return body;
	}
	
   public static String Login() {
	    String body = "{ \"username\": \"sqaprd\", \"password\": \"selenium\" }";
	return body;
}
   public static String InsertComment() {
	    String body = "{" + 
	    		"\"body\": \"Adding comment through Automation\"," + 
	    		"\"visibility\":{" + 
	    		"\"type\": \"role\"," + 
	    		"\"value\": \"Administrators\"" + 
	    		" }" + 
	    		"}";
	    return body;
   }
   
   public static String UpdateComment() {
	   String body = "{" + 
	   		" \"body\": \" Updating comment through Automation today\"," + 
	   		" \"visibility\": {" + 
	   		" \"type\": \"role\"," + 
	   		" \"value\": \"Administrators\" " + 
	   		" }" + 
	   		"}";
	   return body;
   }
	    		
   }
   
 