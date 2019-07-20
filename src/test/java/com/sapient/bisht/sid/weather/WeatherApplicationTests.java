package com.sapient.bisht.sid.weather;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.bisht.sid.weather.dto.OpenWeatherResponseData;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeatherApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	public static final String PATH = "/api/forcast/get?";
	private OpenWeatherResponseData response ;
	ObjectMapper om = new ObjectMapper();

	private URI getURL(String city, String country) {
		URI temp = null;
		try {
			if (StringUtils.isEmpty(city))
				temp = new URI(PATH + "city=Bangalore&countryCode=in");
			else
				temp = new URI(PATH + "city=" + city + "&countryCode="
						+ country);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	private void readFile(String filename) {
		try {
			response = om.readValue(
					this.getClass().getClassLoader()
							.getResourceAsStream(filename),
							OpenWeatherResponseData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void checkRainMessage() {
		readFile("bangalore.json");
		ResponseEntity<OpenWeatherResponseData> responseActual = restTemplate
				.getForEntity(getURL("Bangalore", "in"),
						OpenWeatherResponseData.class);
		Assert.assertEquals(HttpStatus.OK, responseActual.getStatusCode());
		Assert.assertEquals(response, responseActual.getBody());
	}

	@Test
	public void checkZException() {
		ResponseEntity<OpenWeatherResponseData> responseActual = restTemplate
				.getForEntity(getURL("invalid", "in"),
						OpenWeatherResponseData.class);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseActual.getStatusCode());
	}
}
