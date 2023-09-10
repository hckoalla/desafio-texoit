package com.desafiotexoit.dto;

import java.util.ArrayList;
import java.util.List;

public class WinnerIntervalDTO {

	private List<WinnerIntervalItemDTO> min;
	private List<WinnerIntervalItemDTO> max;

	public WinnerIntervalDTO() {
		super();
		min = new ArrayList<>();
		max = new ArrayList<>();
	}

	public List<WinnerIntervalItemDTO> getMin() {
		return min;
	}

	public void setMin(List<WinnerIntervalItemDTO> min) {
		this.min = min;
	}

	public List<WinnerIntervalItemDTO> getMax() {
		return max;
	}

	public void setMax(List<WinnerIntervalItemDTO> max) {
		this.max = max;
	}

}
