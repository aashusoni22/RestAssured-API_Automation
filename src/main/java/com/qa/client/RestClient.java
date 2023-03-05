package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method without Headers:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		// Create client connection
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// Create GET connection with URL
		HttpGet httpget = new HttpGet(url);

		// Hit the API(Send) (this response have everything such as status code,
		// response body, headers, etc.)
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);

		return closeableHttpResponse;
	}

	// 2. GET Method with Headers:
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)throws ClientProtocolException, IOException {
		// Create client connection
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// Create GET connection with URL
		HttpGet httpget = new HttpGet(url);

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		// Hit the API(Send) (this response have everything such as status code,
		// response body, headers, etc.)
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);

		return closeableHttpResponse;
	}
	
	// 3. POST Method:
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		// Create client connection
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//Create POST connection with URL
		HttpPost httpPost = new HttpPost(url);
		//Create pay load
		httpPost.setEntity(new StringEntity(entityString));
		
		//for headers:
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);

		return closeableHttpResponse;
	}

}
