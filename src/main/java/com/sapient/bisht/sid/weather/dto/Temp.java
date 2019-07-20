package com.sapient.bisht.sid.weather.dto;

import lombok.Data;

@Data
public class Temp {
	private Double temp;
	private Double temp_min;
	private Double temp_max;
	private Double pressure;
	private Double sea_level;
	private Double grnd_level;
	private Double humidity;
	private Double temp_kf;
	
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	public Double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}
	public Double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
	public Double getSea_level() {
		return sea_level;
	}
	public void setSea_level(Double sea_level) {
		this.sea_level = sea_level;
	}
	public Double getGrnd_level() {
		return grnd_level;
	}
	public void setGrnd_level(Double grnd_level) {
		this.grnd_level = grnd_level;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getTemp_kf() {
		return temp_kf;
	}
	public void setTemp_kf(Double temp_kf) {
		this.temp_kf = temp_kf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Temp other = (Temp) obj;
		if (grnd_level == null) {
			if (other.grnd_level != null)
				return false;
		} else if (!grnd_level.equals(other.grnd_level))
			return false;
		if (humidity == null) {
			if (other.humidity != null)
				return false;
		} else if (!humidity.equals(other.humidity))
			return false;
		if (pressure == null) {
			if (other.pressure != null)
				return false;
		} else if (!pressure.equals(other.pressure))
			return false;
		if (sea_level == null) {
			if (other.sea_level != null)
				return false;
		} else if (!sea_level.equals(other.sea_level))
			return false;
		if (temp == null) {
			if (other.temp != null)
				return false;
		} else if (!temp.equals(other.temp))
			return false;
		if (temp_kf == null) {
			if (other.temp_kf != null)
				return false;
		} else if (!temp_kf.equals(other.temp_kf))
			return false;
		if (temp_max == null) {
			if (other.temp_max != null)
				return false;
		} else if (!temp_max.equals(other.temp_max))
			return false;
		if (temp_min == null) {
			if (other.temp_min != null)
				return false;
		} else if (!temp_min.equals(other.temp_min))
			return false;
		return true;
	}
}
