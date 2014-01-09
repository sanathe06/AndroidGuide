package com.androidguide.dynamicmenuitems;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button mButtonOne,mButtonTwo;
	private static final int MENU_ITEM_ID_ONE =1;
	private static final int MENU_ITEM_ID_TWO =2;
	private static final int MENU_ITEM_ID_THREE =3;
	private static final int MENU_ITEM_ID_FOUR =4;
	private static final int MENU_ITEM_ID_FIVE =5;
	private static final int MENU_ITEM_ID_SIX =6;
	private int mMenuSet = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mButtonOne=(Button) findViewById(R.id.buttonSetOne);
		mButtonTwo=(Button) findViewById(R.id.buttonSetTwo);
		
		mButtonOne.setOnClickListener(clickListener);
		mButtonTwo.setOnClickListener(clickListener);
	}
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (v.getId()==R.id.buttonSetOne) {
				mMenuSet=1;
			} else if (v.getId()==R.id.buttonSetTwo){
				mMenuSet=2;
			}
			invalidateOptionsMenu();
			/*
			 * if you are using ActionBarSherlock use this.supportInvalidateOptionsMenu();
			 * if you are using ActionBarCompat use invalidateOptionsMenu (Activity activity) method
			 * */
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		//<script src="https://gist.github.com/sanathe06/8330527.js"></script>
		if(mMenuSet==1){
			menu.add(Menu.NONE, MENU_ITEM_ID_ONE, Menu.NONE,getString(R.string.str_menu_one)).setIcon(R.drawable.ic_action_one).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			menu.add(Menu.NONE, MENU_ITEM_ID_TWO, Menu.NONE,getString(R.string.str_menu_two)).setIcon(R.drawable.ic_action_two).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			menu.add(Menu.NONE, MENU_ITEM_ID_THREE, Menu.NONE,getString(R.string.str_menu_three)).setIcon(R.drawable.ic_action_three);
			
		}else if(mMenuSet==2){
			menu.add(Menu.NONE, MENU_ITEM_ID_FOUR, Menu.NONE,getString(R.string.str_menu_four)).setIcon(R.drawable.ic_action_four).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			menu.add(Menu.NONE, MENU_ITEM_ID_FIVE, Menu.NONE,getString(R.string.str_menu_five)).setIcon(R.drawable.ic_action_five).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			menu.add(Menu.NONE, MENU_ITEM_ID_SIX, Menu.NONE,getString(R.string.str_menu_six)).setIcon(R.drawable.ic_action_six);
		}		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ITEM_ID_ONE:
			Toast.makeText(this, "Click on "+ getString(R.string.str_menu_one), Toast.LENGTH_SHORT).show();
			break;
		case MENU_ITEM_ID_TWO:
			Toast.makeText(this, "Click on "+ getString(R.string.str_menu_two), Toast.LENGTH_SHORT).show();
			break;
		case MENU_ITEM_ID_THREE:
			Toast.makeText(this, "Click on "+ getString(R.string.str_menu_three), Toast.LENGTH_SHORT).show();
			break;
		case MENU_ITEM_ID_FOUR:
			Toast.makeText(this, "Click on "+ getString(R.string.str_menu_four), Toast.LENGTH_SHORT).show();
			break;
		case MENU_ITEM_ID_FIVE:
			Toast.makeText(this, "Click on "+ getString(R.string.str_menu_five), Toast.LENGTH_SHORT).show();
			break;
		case MENU_ITEM_ID_SIX:
			Toast.makeText(this, "Click on "+ getString(R.string.str_menu_six), Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings:
			Toast.makeText(this, "Click on "+ getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
