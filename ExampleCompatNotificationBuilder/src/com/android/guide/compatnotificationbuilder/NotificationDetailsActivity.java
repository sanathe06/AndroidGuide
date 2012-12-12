package com.android.guide.compatnotificationbuilder;

import android.app.Activity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;

public class NotificationDetailsActivity extends Activity {

	public static final String NOTIFICATION_DATA = "NOTIFICATION_DATA";
	private TextView notificationData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);
		notificationData =(TextView) findViewById(R.id.textView);
		
		if(savedInstanceState==null){
			String data=getIntent().getExtras().getString(NOTIFICATION_DATA);
			notificationData.setText(data);
		}
	}
	
}
