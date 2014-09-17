package com.github.snambi.instagramviewer;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
		TextView tvCaption = (TextView) convertView.findViewById(R.id.tvUsername);
		TextView tvBottomCaption = (TextView) convertView.findViewById(R.id.tvBottomCaption);
		
		
		imgPhoto.getLayoutParams().height = photo.getImageHeight();
		imgPhoto.setImageResource(0);
		
		Picasso.with( getContext() ).load( photo.getImageUrl() ).fit().centerCrop().into(imgPhoto);
		
		tvCaption.setText( photo.getUsername());
		
		String formattedText = "<b>" + photo.getUsername() + "</b> -- " + photo.getCaption();
		tvBottomCaption.setText( Html.fromHtml(formattedText));
		
		return convertView;
	}
}
