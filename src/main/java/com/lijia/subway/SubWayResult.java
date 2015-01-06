package com.lijia.subway;

import java.util.List;

public class SubWayResult {
	public SubWayResult(List<String> path, double distance) {
		super();
		this.path = path;
		this.distance = distance;
	}
	List<String> path;
	double distance;
	public List<String> getPath() {
		return path;
	}
	public void setPath(List<String> path) {
		this.path = path;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}
