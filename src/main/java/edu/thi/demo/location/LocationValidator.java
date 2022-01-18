package edu.thi.demo.location;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;


public class LocationValidator {

	private String location;
	
	public LocationValidator(String location) {
		this.location = location;
	}
	
	public Boolean isValidLocation() {
		String city = this.location;
		String apiKey = "dce1ae7436e4418c85d20e41e0c08090";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						"https://api.geoapify.com/v1/geocode/search?city=" + city + "&format=json&apiKey=" + apiKey))
				.build();
		
		HttpResponse<String> response;
		try {
			response = client.send(request, BodyHandlers.ofString());
			JsonReader reader = Json.createReader(new StringReader(response.body()));

			JsonObject jsonObject = reader.readObject();
			for (String key : jsonObject.keySet()) {
				if(key.equals("results")) {
					JsonArray results = (JsonArray) jsonObject.get(key);
					if(results.size() > 0) {
						return true;
					}
				}
			}
	        return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
