package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.BaseClass;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends BaseClass{

	BaseClass baseClass;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		baseClass = new BaseClass();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");

		url = serviceUrl + apiUrl;
	}

	@Test
	public void postAPITest() throws ClientProtocolException, IOException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader"); // expected users object
		
		//object to JSON file conversion:
		mapper.writeValue(new File("C:\\Users\\omson\\eclipse-workspace\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"), users);
	
		//object to JSON in String
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		closeableHttpResponse = restClient.post(url, usersJsonString, headerMap);
		
		//1. STATUS CODE
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);
		
		//2. JSON STRING
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject jsonObject = new JSONObject(responseString);
		System.out.println("Response JSON from API--->" + jsonObject);
		
		//JSON TO JAVA OBJECT
		Users userResObj =  mapper.readValue(responseString, Users.class);
		System.out.println(userResObj);
		
		Assert.assertTrue(users.getName().equals(userResObj.getName()));
		Assert.assertTrue(users.getJob().equals(userResObj.getJob()));
		System.out.println(userResObj.getId());
		System.out.println(userResObj.getCreatedAt());
	}
	
}
