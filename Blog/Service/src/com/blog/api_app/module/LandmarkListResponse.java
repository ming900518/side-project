package com.blog.api_app.module;

public class LandmarkListResponse {
	private int landmarkId;
	private String name;
	private String imagePath;
	
	public int getLandmarkId() {
		return landmarkId;
	}
	public void setLandmarkId(int landmarkId) {
		this.landmarkId = landmarkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
