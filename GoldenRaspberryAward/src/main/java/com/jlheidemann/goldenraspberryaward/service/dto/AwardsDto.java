package com.jlheidemann.goldenraspberryaward.service.dto;

import java.util.ArrayList;
import java.util.List;

public class AwardsDto {
	private List<AwardIntervalDto> min;
	private List<AwardIntervalDto> max;
	
	
	public AwardsDto() {
		min = new ArrayList<AwardIntervalDto>();
		max = new ArrayList<AwardIntervalDto>();
	}
	public List<AwardIntervalDto> getMin() {
		return min;
	}
	public void setMin(List<AwardIntervalDto> min) {
		this.min = min;
	}
	public List<AwardIntervalDto> getMax() {
		return max;
	}
	public void setMax(List<AwardIntervalDto> max) {
		this.max = max;
	}
	
	public void addMinValue(AwardIntervalDto value) {
		this.min.add(value);
	}
	
	public void addMaxValue(AwardIntervalDto value) {
		this.max.add(value);
	}
	
	
}
