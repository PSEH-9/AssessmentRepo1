package com.sapient.bisht.sid.weather.dto;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clouds other = (Clouds) obj;
		if (all == null) {
			if (other.all != null)
				return false;
		} else if (!all.equals(other.all))
			return false;
		return true;
	}
}
