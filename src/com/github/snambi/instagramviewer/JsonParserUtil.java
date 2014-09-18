package com.github.snambi.instagramviewer;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParserUtil {
	
	public static InstagramPhoto convertJsonToPhoto( JSONObject photoJson) throws JSONException{
		
		InstagramPhoto photo = new InstagramPhoto();
		
		// check json and properly build the object
		if( !photoJson.isNull("user") ){
			if( !photoJson.getJSONObject("user").isNull("username") ){			
				photo.setUsername(photoJson.getJSONObject("user").getString("username"));
			}
			if( !photoJson.getJSONObject("user").isNull("profile_picture") ){			
				photo.setUserImageUrl( photoJson.getJSONObject("user").getString("profile_picture"));
			}
		}
		
		if( !photoJson.isNull("images")  && 
			!photoJson.getJSONObject("images").isNull("standard_resolution")  ){
			
			if( photoJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url") != null){
				photo.setImageUrl( photoJson.getJSONObject("images")
											.getJSONObject("standard_resolution")
											.getString("url"));
			}
			
			photo.setImageHeight( photoJson.getJSONObject("images")
											.getJSONObject("standard_resolution")
											.getInt("height"));

		}
		
		
		if( !photoJson.isNull("caption")  &&
			!photoJson.getJSONObject("caption").isNull("text") ){
			
			photo.setCaption( photoJson.getJSONObject("caption").getString("text"));
		}
		
		if( !photoJson.isNull("likes") ){
			if( !photoJson.getJSONObject("likes").isNull("count")){
				photo.setLikesCount(photoJson.getJSONObject("likes").getInt("count"));
			}
			
		}
		
		if( photoJson.has("created_time")){
			String createdTimeStr = photoJson.getString("created_time");
			// convert the string into long
			long createdTime = Long.parseLong(createdTimeStr);
			photo.setCreatedTime(createdTime);
		}
		
		if( !photoJson.isNull("location")){
			if( !photoJson.getJSONObject("location").isNull("longitude")){
				Double longi = photoJson.getJSONObject("location").getDouble("longitude");
				photo.setLongitude(longi);
			}
		}
		
		if( !photoJson.isNull("location")){
			if( !photoJson.getJSONObject("location").isNull("lattitude")){
				Double latti = photoJson.getJSONObject("location").getDouble("lattitude");
				photo.setLongitude(latti);
			}
		}

		if( !photoJson.isNull("location")){
			if( !photoJson.getJSONObject("location").isNull("name")){
				String name = photoJson.getJSONObject("location").getString("name");
				photo.setLocationName(name);
			}
		}
		
		if( !photoJson.isNull("comments")){
			if( !photoJson.getJSONObject("comments").isNull("count")){
				int count = photoJson.getJSONObject("comments").getInt("count");
				photo.setCommentCount(count);
			}
		}


		return photo;
	}

}
