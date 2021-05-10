package com.blog.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusN1EstimateTime {
	@JsonProperty("StopUID")
	String stopUID;
	@JsonProperty("SubRouteUID")
	String subRouteUID;
	@JsonProperty("EstimateTime")
	Integer estimateTime;
	
	public String getStopUID() {
		return stopUID;
	}


	public String getSubRouteUID() {
		return subRouteUID;
	}


	public Integer getEstimateTime() {
		return estimateTime;
	}


}
