package com.github.snambi.instagramviewer;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


public class PhotosActivity extends Activity {
	
	public static final String INSTAGRAM_CLIENT_ID = "2bd2c98f31cd4a6b94f2e5e6231680e9";
	public static final String INSTAGRAM_POPULAR_PHOTOS_EP = "https://api.instagram.com/v1/media/popular?client_id=" + INSTAGRAM_CLIENT_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        
        fetchPopularPhotos();
    }


    private void fetchPopularPhotos() {
		// create async client
    	AsyncHttpClient httpClient = new AsyncHttpClient();
    	
    	// make a network call
    	httpClient.get(INSTAGRAM_POPULAR_PHOTOS_EP, new JsonHttpResponseHandler(){
    		
    		// handle successful responses
    		@Override
    		public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
    			
    			if( response !=null ){
    				JSONArray photosJSON = null;
    				
    				try {
						photosJSON = response.getJSONArray("data");
						
						for( int i=0; i < photosJSON.length() ; i++){
							
							JSONObject photoJson = photosJSON.getJSONObject(i);
							
							InstagramPhoto photo = new InstagramPhoto();
							
							photo.setUsername(photoJson.getJSONObject("user").getString("username"));
							photo.setImageUrl( photoJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
							photo.setImageHeight( photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"));
							photo.setCaption( photoJson.getJSONObject("caption").getString("text"));
							photo.setLikesCount(photoJson.getJSONObject("likes").getInt("count"));
							
							Log.i("INFO", photo.toString());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
    			}
    		}
    		
    		// handle failures
    		@Override
    		public void onFailure(int statusCode, Header[] headers,
    				Throwable throwable, JSONObject errorResponse) {
    			
    			super.onFailure(statusCode, headers, throwable, errorResponse);
    		}
    	});
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
