package com.android.guide.exampleintentservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	Button buttonRun;
	TextView textViewResult;
	EditText editTextInput;
	TextCapitalizeResultReceiver capitalizeResultReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG,"onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonRun =(Button) findViewById(R.id.buttonRun);
		textViewResult=(TextView) findViewById(R.id.textViewResult);
		editTextInput=(EditText) findViewById(R.id.editTextInput);
		
		buttonRun.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String inputText =editTextInput.getText().toString().trim();
				if(inputText.length()>0){
					//create new intent 
					Intent textCapitalizeIntent= new Intent(MainActivity.this,ExampleIntentService.class);
					// add necessary  data to intent
					textCapitalizeIntent.putExtra(ExampleIntentService.INPUT_TEXT, inputText);
					// start service
					startService(textCapitalizeIntent);
				}else{
					Toast.makeText(MainActivity.this,"Please input text",Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	/**
	 * registering BroadcastReceiver
	 */
	private void registerReceiver() {
		/*create filter for exact intent what we want from other intent*/
		IntentFilter intentFilter =new IntentFilter(TextCapitalizeResultReceiver.ACTION_TEXT_CAPITALIZED);
		intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
		/* create new broadcast receiver*/
		capitalizeResultReceiver=new TextCapitalizeResultReceiver();
		/* registering our Broadcast receiver to listen action*/
		registerReceiver(capitalizeResultReceiver, intentFilter);
	}
	
	public class TextCapitalizeResultReceiver extends BroadcastReceiver {
		/**
		 * action string for our broadcast receiver to get notified
		 */
		public final static String ACTION_TEXT_CAPITALIZED= "com.android.guide.exampleintentservice.intent.action.ACTION_TEXT_CAPITALIZED";
		@Override
		public void onReceive(Context context, Intent intent) {
			String resultText =intent.getStringExtra(ExampleIntentService.OUTPUT_TEXT);
			textViewResult.setText(resultText);
		}
	};
		
	@Override
	protected void onPause() {
		Log.i(TAG,"onPause()");
		/* we should unregister BroadcastReceiver here*/
		unregisterReceiver(capitalizeResultReceiver);
		super.onPause();
	}

	@Override
	protected void onResume() {
		Log.i(TAG,"onResume()");
		/* we register BroadcastReceiver here*/
		registerReceiver();
		super.onResume();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
