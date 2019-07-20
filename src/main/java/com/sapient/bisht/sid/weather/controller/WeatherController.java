package com.sapient.bisht.sid.weather.controller;

import lombok.extern.slf4j.Slf4j;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.bisht.sid.weather.dto.OpenWeatherResponseData;
import com.sapient.bisht.sid.weather.service.OpenWeatherSerivce;

@RestController
@RequestMapping("/api/forcast")
@Slf4j
public class WeatherController {

	@Autowired
	private OpenWeatherSerivce ows;
	
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value="/get")
	public ResponseEntity<OpenWeatherResponseData> getForcast(@RequestParam("city") final String city,
			@RequestParam("countryCode") final String countryCode) {
		log.debug("entering getForcast with city: " + city +", country: " + countryCode);
		
		return new ResponseEntity<OpenWeatherResponseData>(ows.getForcast(city, countryCode), HttpStatus.OK);
	}
}
