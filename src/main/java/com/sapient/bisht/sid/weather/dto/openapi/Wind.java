package com.sapient.bisht.sid.weather.dto.openapi;

import lombok.Data;

@Data
public class Wind {
	private Double speed;
	private Double deg;
	
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getDeg() {
		return deg;
	}
	public void setDeg(Double deg) {
		this.deg = deg;
	}
}
