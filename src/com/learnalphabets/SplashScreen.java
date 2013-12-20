package com.learnalphabets;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends Activity{
	
	
	Button splashStart;
	
	public void onCreate (Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splash_screen);
		
		
		
		splashStart = (Button)findViewById(R.id.splash_start);
		
		splashStart.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {	
      	Intent i = new Intent(getApplicationContext(), HomeScreen.class);
		    startActivity(i);
		    finish();
      }
     });
		
		
		
     //   String answer = String.valueOf(viewScroll);
    //	  Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_LONG).show();
		
	}
}
