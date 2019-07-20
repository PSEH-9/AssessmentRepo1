package com.sapient.bisht.sid.weather.dto;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataPoint other = (DataPoint) obj;
		if (clouds == null) {
			if (other.clouds != null)
				return false;
		} else if (!clouds.equals(other.clouds))
			return false;
		if (dateLong == null) {
			if (other.dateLong != null)
				return false;
		} else if (!dateLong.equals(other.dateLong))
			return false;
		if (dateText == null) {
			if (other.dateText != null)
				return false;
		} else if (!dateText.equals(other.dateText))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (rain == null) {
			if (other.rain != null)
				return false;
		} else if (!rain.equals(other.rain))
			return false;
		if (tempData == null) {
			if (other.tempData != null)
				return false;
		} else if (!tempData.equals(other.tempData))
			return false;
		if (weather == null) {
			if (other.weather != null)
				return false;
		} else if (!weather.equals(other.weather))
			return false;
		if (wind == null) {
			if (other.wind != null)
				return false;
		} else if (!wind.equals(other.wind))
			return false;
		return true;
	}
}
