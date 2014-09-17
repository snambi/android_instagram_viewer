package com.github.snambi.instagramviewer;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


public class PhotosActivity extends Activity {
	
	public static final String INSTAGRAM_CLIENT_ID = "2bd2c98f31cd4a6b94f2e5e6231680e9";
	public static final String INSTAGRAM_POPULAR_PHOTOS_EP = "https://api.instagram.com/v1/media/popular?client_id=" + INSTAGRAM_CLIENT_ID;
	
	private List<InstagramPhoto> photos = null;
	private InstagramArrayAdapter instagramAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        
        // init the photo array
    	photos = new ArrayList<InstagramPhoto>();
    	
        instagramAdapter = new InstagramArrayAdapter(this, photos);
        
        // bind the view to with new adapter
        ListView photosView = (ListView) findViewById(R.id.lvPhotos);
        photosView.setAdapter(instagramAdapter);
        
        // get the photos and notify the adaptor
        fetchPopularPhotos( instagramAdapter);
    }


    private void fetchPopularPhotos( final InstagramArrayAdapter aAdapter) {
		
    	
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
						} catch (JSONException e) {
							//e.printStackTrace();
							Log.e("ERROR", "Response doesn't contain data, please handle the situation");
						}
						
						// clear any preveious data
						photos.clear();
						
						for( int i=0; i < photosJSON.length() ; i++){
							
							JSONObject photoJson = null;
							InstagramPhoto photo = new InstagramPhoto();
							try {
								photoJson = photosJSON.getJSONObject(i);
								photo = JsonParserUtil.convertJsonToPhoto(photoJson);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								Log.e("ERROR", "Unable to convert json to photo, please handle the situation");
								photo.setCaption("error photo");
								photo.setUsername("sorry");
							}
							//Log.i("INFO", photo.toString());
							photos.add(photo);
						}
						
						// notify the adapter about the data change
						aAdapter.notifyDataSetChanged();
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
