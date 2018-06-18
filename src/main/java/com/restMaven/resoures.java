package com.restMaven;

public class resoures {

	public static String PostData() {
		
		String res = "/maps/api/place/add/json";
		return res; 
	}

	public static String DeleteData() {
		String delresource = "/maps/api/place/delete/json";
		return delresource;
	}
	
	public static String PostXMLData() {
		String resxml = "/maps/api/place/add/xml";
		return resxml;
	}
	
	public static String DataGet() {
		
		String getresource = "/maps/api/place/nearbysearch/json";
		return getresource; 
	}

}
