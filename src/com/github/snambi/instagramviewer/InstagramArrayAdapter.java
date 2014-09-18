package com.github.snambi.instagramviewer;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class InstagramArrayAdapter extends ArrayAdapter<InstagramPhoto> {

	public InstagramArrayAdapter(Context context, List<InstagramPhoto> photos) {
		super(context, android.R.layout.simple_list_item_1, photos);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		InstagramPhoto photo = getItem(position);
		
		if( convertView == null ){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
		}
		
		ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
		CircularImageView usrPhoto = (CircularImageView) convertView.findViewById(R.id.imgUserPhoto);
		TextView tvCaption = (TextView) convertView.findViewById(R.id.tvUsername);
		TextView tvBottomCaption = (TextView) convertView.findViewById(R.id.tvBottomCaption);
		TextView tvPhotoAge = (TextView) convertView.findViewById(R.id.tvAge);
		
		//imgPhoto.getLayoutParams().height = parent.getLayoutParams().height;
		imgPhoto.getLayoutParams().height = parent.getHeight();
		imgPhoto.setImageResource(0);
		imgPhoto.setAdjustViewBounds(true);
		
		// get the dimensions of the view
//		int height = parent.getHeight();
//		int width = parent.getWidth();
//		
//		int imgH = imgPhoto.getHeight();
//		int imgW = imgPhoto.getWidth();
		
		Log.i("INFO", photo.getImageUrl());
		Picasso.with( getContext() ).load( photo.getImageUrl() ).fit().centerInside().into(imgPhoto);
		Picasso.with( getContext()).load(photo.getUserImageUrl()).fit().centerInside().into(usrPhoto); 
		
		
		tvCaption.setText( photo.getUsername());
		
		String formattedText = "<b>" + photo.getUsername() + "</b> -- " + photo.getCaption();
		tvBottomCaption.setText( Html.fromHtml(formattedText));
		
		return convertView;
	}
}
