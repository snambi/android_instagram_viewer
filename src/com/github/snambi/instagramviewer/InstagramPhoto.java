package com.github.snambi.instagramviewer;

public class InstagramPhoto {
	
	private String username;
	private String imageUrl;
	private String userImageUrl;
	private int imageHeight;
	private String caption;
	private int likesCount;
	
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
	
	@Override
	public String toString() {
		return "InstagramPhoto [username=" + username + ", imageUrl="
				+ imageUrl + ", imageHeight=" + imageHeight + ", caption="
				+ caption + ", likesCount=" + likesCount + "]";
	}

}
