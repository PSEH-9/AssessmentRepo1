package com.sapient.bisht.sid.weather.dto;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rain other = (Rain) obj;
		if (threeH == null) {
			if (other.threeH != null)
				return false;
		} else if (!threeH.equals(other.threeH))
			return false;
		return true;
	}
}
