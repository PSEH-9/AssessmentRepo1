package com.sapient.bisht.sid.weather.dto;

import java.util.List;

import com.sapient.bisht.sid.weather.dto.openapi.OpenWeatherResponseData;

import lombok.Data;

@Data
public class WeatherAPIResponse {
	List<ErrorMessage> errorList;
	OpenWeatherResponseData resonse;
}
