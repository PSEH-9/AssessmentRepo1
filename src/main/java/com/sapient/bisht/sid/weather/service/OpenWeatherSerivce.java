package com.sapient.bisht.sid.weather.service;

import com.sapient.bisht.sid.weather.dto.openapi.OpenWeatherResponseData;

public interface OpenWeatherSerivce {
	public OpenWeatherResponseData getForcast(String city, String country);
}
