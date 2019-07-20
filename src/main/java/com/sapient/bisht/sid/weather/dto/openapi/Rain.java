package com.sapient.bisht.sid.weather.dto.openapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Rain {
	@JsonProperty("3h")
	private Double threeH;

	public Double getThreeH() {
		return threeH;
	}

	public void setThreeH(Double threeH) {
		this.threeH = threeH;
	}
}
