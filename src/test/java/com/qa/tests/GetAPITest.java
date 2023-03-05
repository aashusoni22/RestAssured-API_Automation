package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends BaseClass {

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

	@Test(priority=1)
	public void getAPIWithoutHeadersTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);

		// **************STATUS CODE**************
		// get status response
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
 		System.out.println("Status code--->" + statusCode);
		Assert.assertEquals(statusCode, baseClass.RESPONSE_STATUS_CODE_200);

		// **************JSON STRING**************
		// get body response in String format
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		// get body response in JSON format
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->" + responseJson);
		
		//single value assertion:
		//page
		String pageValue = TestUtil.getValueByJPath(responseJson, "/page");
		System.out.println("value of page is--->" + pageValue);
		Assert.assertEquals(Integer.parseInt(pageValue), 1);

		//per_page
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is--->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		//total
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of total is--->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//total_pages
		String totalPagesValue = TestUtil.getValueByJPath(responseJson, "/total_pages");
		System.out.println("value of total pages is--->" + totalPagesValue);
		Assert.assertEquals(Integer.parseInt(totalPagesValue), 2);
		
		//get the value from JSON Array:	
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String last_name= TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		
		System.out.println(id);
		System.out.println(email);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(avatar);
		
		Assert.assertEquals(Integer.parseInt(id), 1);
		Assert.assertEquals(email, "george.bluth@reqres.in");
		Assert.assertEquals(first_name, "George");
		Assert.assertEquals(last_name, "Bluth");
		Assert.assertEquals(avatar, "https://reqres.in/img/faces/1-image.jpg");
		
		// **************ALL HEADERS**************
		// get headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		// formatting headers into key value pair
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array--->" + allHeaders);
	}
	
	@Test(priority=2)
	public void getAPIWithHeadersTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("username", "test@company.com");
		//headerMap.put("password", "12433ds");
		//headerMap.put("Auth Token", "1234567");
		
		
		closeableHttpResponse = restClient.get(url, headerMap);

		// **************STATUS CODE**************
		// get status response
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code--->" + statusCode);
		Assert.assertEquals(statusCode, baseClass.RESPONSE_STATUS_CODE_200);

		// **************JSON STRING**************
		// get body response in String format
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		// get body response in JSON format
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->" + responseJson);
		
		//single value assertion:
		//page
		String pageValue = TestUtil.getValueByJPath(responseJson, "/page");
		System.out.println("value of page is--->" + pageValue);
		Assert.assertEquals(Integer.parseInt(pageValue), 1);

		//per_page
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is--->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		//total
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of total is--->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//total_pages
		String totalPagesValue = TestUtil.getValueByJPath(responseJson, "/total_pages");
		System.out.println("value of total pages is--->" + totalPagesValue);
		Assert.assertEquals(Integer.parseInt(totalPagesValue), 2);
		
		//get the value from JSON Array:	
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String last_name= TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		
		System.out.println(id);
		System.out.println(email);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(avatar);
		
		Assert.assertEquals(Integer.parseInt(id), 1);
		Assert.assertEquals(email, "george.bluth@reqres.in");
		Assert.assertEquals(first_name, "George");
		Assert.assertEquals(last_name, "Bluth");
		Assert.assertEquals(avatar, "https://reqres.in/img/faces/1-image.jpg");
		
		// **************ALL HEADERS**************
		// get headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		// formatting headers into key value pair
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array--->" + allHeaders);
	}
	

}
