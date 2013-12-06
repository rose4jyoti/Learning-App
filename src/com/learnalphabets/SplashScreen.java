package com.learnalphabets;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity{
	
	public void onCreate (Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splash_screen);
		
		 Handler handler = new Handler();
     handler.postDelayed(new Runnable(){
     @Override
           public void run(){
     			Intent i = new Intent(getApplicationContext(), HomeScreen.class);
			    startActivity(i);
			    finish();
        }
     }, 1000);
     
	}

}
