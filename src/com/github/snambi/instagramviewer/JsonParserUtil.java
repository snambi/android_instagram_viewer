package com.github.snambi.instagramviewer;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParserUtil {
	
	public static InstagramPhoto convertJsonToPhoto( JSONObject photoJson) throws JSONException{
		
		InstagramPhoto photo = new InstagramPhoto();
		
		// check json and properly build the object
		if( !photoJson.isNull("user")  && 
			!photoJson.getJSONObject("user").isNull("username") ){
			
			photo.setUsername(photoJson.getJSONObject("user").getString("username"));
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

		return photo;
	}

}
