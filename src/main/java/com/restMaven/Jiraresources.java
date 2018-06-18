package com.restMaven;

public class Jiraresources {

	public static String PostIssue() {
		
		String res = "rest/api/2/issue";
		return res; 
	}

	public static String Authentication() {
		String authRes = "/rest/auth/1/session";
		return authRes;
	}
 /*
	public static String AddComment() {
		String AddComRes = "/rest/api/2/issue/"+{issueIdOrKey}+"/comment";
		return AddComRes;
	}
	*/
}
