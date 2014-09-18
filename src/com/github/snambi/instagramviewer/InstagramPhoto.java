package com.github.snambi.instagramviewer;

import android.text.format.DateUtils;

public class InstagramPhoto {
	
	private String username;
	private String imageUrl;
	private String userImageUrl;
	private long createdTime;
	private int imageHeight;
	private String caption;
	private int likesCount;
	private int commentCount;
	private double longitude;
	private double lattitude;
	private String locationName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUserImageUrl() {
		return userImageUrl;
	}
	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getRelativeCreateTime(){	
		return DateUtils.getRelativeTimeSpanString(getCreatedTime() * 1000).toString();
	
	}
	
	@Override
	public String toString() {
		return "InstagramPhoto [username=" + username + ", imageUrl="
				+ imageUrl + ", imageHeight=" + imageHeight + ", caption="
				+ caption + ", likesCount=" + likesCount + "]";
	}

}
