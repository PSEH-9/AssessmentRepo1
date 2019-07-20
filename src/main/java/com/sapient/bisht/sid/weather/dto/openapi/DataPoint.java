package com.sapient.bisht.sid.weather.dto.openapi;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class DataPoint {
	@JsonProperty("dt")
	private Long dateLong;
	
	@JsonProperty("dt_txt")
	private String dateText;
	
	@JsonProperty("main")
	private Temp tempData;
	private List<Weather> weather;
	private Clouds clouds;
	private Wind wind;
	private Rain rain;
	private Sys sys;
	private String message;
	
	public Long getDateLong() {
		return dateLong;
	}
	public void setDateLong(Long dateLong) {
		this.dateLong = dateLong;
	}
	public String getDateText() {
		return dateText;
	}
	public void setDateText(String dtText) {
		this.dateText = dtText;
	}
	public Temp getTempData() {
		return tempData;
	}
	public void setTempData(Temp tempData) {
		this.tempData = tempData;
	}
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	public Clouds getClouds() {
		return clouds;
	}
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public Rain getRain() {
		return rain;
	}
	public void setRain(Rain rain) {
		this.rain = rain;
	}
	public Sys getSys() {
		return sys;
	}
	public void setSys(Sys sys) {
		this.sys = sys;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
