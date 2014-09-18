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

		ViewHolder holder=null;
		if( convertView.getTag() == null ){
			ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
			CircularImageView usrPhoto = (CircularImageView) convertView.findViewById(R.id.imgUserPhoto);
			TextView tvCaption = (TextView) convertView.findViewById(R.id.tvUsername);
			TextView tvBottomCaption = (TextView) convertView.findViewById(R.id.tvBottomCaption);
			TextView tvPhotoAge = (TextView) convertView.findViewById(R.id.tvAge);
			TextView tvLoc = (TextView) convertView.findViewById(R.id.tvLocation);
			
			holder = new ViewHolder();
			holder.imgPhoto = imgPhoto;
			holder.tvBottomCaption = tvBottomCaption;
			holder.tvCaption = tvCaption;
			holder.tvLoc = tvLoc;
			holder.tvPhotoAge = tvPhotoAge;
			holder.userPhoto = usrPhoto;
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		
		//imgPhoto.getLayoutParams().height = parent.getLayoutParams().height;
		holder.imgPhoto.getLayoutParams().height = parent.getWidth();
		holder.imgPhoto.setImageResource(0);
		holder.imgPhoto.setAdjustViewBounds(true);
		
		
		Log.i("INFO", photo.getImageUrl());
		Picasso.with( getContext() ).load( photo.getImageUrl() ).fit().centerInside().into(holder.imgPhoto);
		Picasso.with( getContext()).load(photo.getUserImageUrl()).fit().centerInside().into(holder.userPhoto); 
		
		
		String formattedUserName = "<b>"+ photo.getUsername() + "</b>";
		holder.tvCaption.setText( Html.fromHtml(formattedUserName));
		
		String formattedCaption=null;
		if( photo.getLikesCount() >= 0 ){
			formattedCaption =  "<span style=\"color: #FF1493;\"> <b> &#9829; " + photo.getLikesCount() + " likes </b></span> -- ";
		}
		if( photo.getCaption() != null ){
			formattedCaption = formattedCaption + " " + photo.getCaption();
		}
		//String formattedText = "<b>" + photo.getUsername() + "</b> -- " + photo.getCaption();
		holder.tvBottomCaption.setText( Html.fromHtml(formattedCaption));
		
		String formattedAge = "<B>&#8986; " + photo.getRelativeCreateTime() + "</b>";
		holder.tvPhotoAge.setText( Html.fromHtml(formattedAge));
		
		if( photo.getLocationName() != null ){
			String formattedLocation = "<B>" + photo.getLocationName() + "</B>";
			holder.tvLoc.setText(Html.fromHtml(formattedLocation));
		}else{
			holder.tvLoc.setText("");
		}
		
		convertView.setTag(holder);
		
		return convertView;
	}
	
	public static class ViewHolder {
		ImageView imgPhoto;
		CircularImageView userPhoto;
		TextView tvCaption;
		TextView tvBottomCaption;
		TextView tvPhotoAge;
		TextView tvLoc;
	}
}
