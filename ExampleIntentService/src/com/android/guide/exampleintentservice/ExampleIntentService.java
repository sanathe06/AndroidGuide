package com.android.guide.exampleintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

import com.android.guide.exampleintentservice.MainActivity.TextCapitalizeResultReceiver;

public class ExampleIntentService extends IntentService {
	private static final String TAG =ExampleIntentService.class.getSimpleName();

	public static final String INPUT_TEXT="INPUT_TEXT";
	public static final String OUTPUT_TEXT="OUTPUT_TEXT";
	
	/**
	 *  initiate service in background thread with service name
	 */
	public ExampleIntentService() {
		super(ExampleIntentService.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG,"onHandleIntent()");
		// get inputs from intent
		String data =intent.getStringExtra(INPUT_TEXT);
		Log.d(TAG,data);
		/*
		 * capitalize data and wait some time(this is represent data processing this is example in your case this 
		 * might be very time consuming process like image uploading/downloading )
		 * 
		 * */
		data=data.toUpperCase();
		SystemClock.sleep(4*1000);
		data =data+" \n processed at : "+DateFormat.format("hh:mm sss",System.currentTimeMillis());
		
		/*create new intent to broadcast our processed data to our activity*/
		Intent resultBroadCastIntent =new Intent();
		/*set action here*/
		resultBroadCastIntent.setAction(TextCapitalizeResultReceiver.ACTION_TEXT_CAPITALIZED);
		/*set intent category as default*/
		resultBroadCastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		
		/*add data to intent*/
		resultBroadCastIntent.putExtra(OUTPUT_TEXT, data);
		/*send broadcast */
		sendBroadcast(resultBroadCastIntent);
	}

}
