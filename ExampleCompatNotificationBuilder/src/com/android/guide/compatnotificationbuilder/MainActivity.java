package com.android.guide.compatnotificationbuilder;


import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	public static final String NOTIFICATION_DATA = "NOTIFICATION_DATA";
	
	
	private Button createEvent;
	private EditText notificationData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createEvent=(Button) findViewById(R.id.button);
		notificationData =(EditText) findViewById(R.id.editText);
		createEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				createNotification(Calendar.getInstance().getTimeInMillis(), notificationData.getText().toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	private void createNotification(long when,String data){
		String notificationContent ="Notification Content Click Here to go more details";
		String notificationTitle ="This is Notification";
		//large icon for notification,normally use App icon
		Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
		int smalIcon =R.drawable.ic_stat_notification;
		String notificationData="This is data : "+data;
		
		/*create intent for show notification details when user clicks notification*/
		Intent intent =new Intent(getApplicationContext(), NotificationDetailsActivity.class);
		intent.putExtra(NOTIFICATION_DATA, notificationData);
		
		/*create unique this intent from  other intent using setData */
		intent.setData(Uri.parse("content://"+when));
		/*create new task for each notification with pending intent so we set Intent.FLAG_ACTIVITY_NEW_TASK */
		PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		
		/*get the system service that manage notification NotificationManager*/
		NotificationManager notificationManager =(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE); 
				
		/*build the notification*/
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
				getApplicationContext())
				.setWhen(when)
				.setContentText(notificationContent)
				.setContentTitle(notificationTitle)
				.setSmallIcon(smalIcon)
				.setAutoCancel(true)
				.setTicker(notificationTitle)
				.setLargeIcon(largeIcon)
				.setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_VIBRATE| Notification.DEFAULT_SOUND)
				.setContentIntent(pendingIntent);
		
		/*Create notification with builder*/
		Notification notification=notificationBuilder.build();
		
		/*sending notification to system.Here we use unique id (when)for making different each notification
		 * if we use same id,then first notification replace by the last notification*/
		notificationManager.notify((int) when, notification);
	}

}
