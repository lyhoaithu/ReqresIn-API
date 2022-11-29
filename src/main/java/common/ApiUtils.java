package common;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ApiUtils {
	
	//SEND GET REQUEST
public HttpResponse<String> sendGet(String url) throws IOException, InterruptedException {
	HttpClient client = HttpClient.newHttpClient();
	HttpResponse<String> response =null;
	HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.GET()
			.header("Content-Type", "application/json")
			.build();
	response=client.send(request, BodyHandlers.ofString());
	return response;
}

//SEND POST REQUEST
public HttpResponse<String> sendPost(String url, String jsonRequestBody) throws IOException, InterruptedException {
	HttpClient client = HttpClient.newHttpClient();
	HttpResponse<String> response=null;
	HttpRequest request= HttpRequest.newBuilder()
			.uri(URI.create(url))
			.header("Content-Type", "application/json")
			.POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody)) //pass request body
			.build();
	response=client.send(request, BodyHandlers.ofString());
	return response;

}

//SEND PUT REQUEST
public HttpResponse<String> sendPut(String url, String jsonRequestBody) throws IOException, InterruptedException {
	HttpClient client = HttpClient.newHttpClient();
	HttpResponse<String> response = null;
    HttpRequest request = HttpRequest.newBuilder()
    		.uri(URI.create(url))
    		.header("Content-Type", "application/json")
    		.PUT(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
    		.build();
    return response;

}

//SEND DELETE REQUEST
public HttpResponse<String> sendDelete(String url) throws IOException, InterruptedException{
	HttpClient client = HttpClient.newHttpClient();
	HttpResponse<String> response = null;
    HttpRequest request = HttpRequest.newBuilder()
    		.uri(URI.create(url))
    		.header("Content-Type", "application/json")
    		.DELETE()
    		.build();
    return response;

}
}
