package com.sapient.bisht.sid.weather.dto.openapi;

import lombok.Data;

@Data
public class Clouds {
	private Long all;

	public Long getAll() {
		return all;
	}

	public void setAll(Long all) {
		this.all = all;
	}
}
