package com.learnalphabets;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeScreen extends Activity {

	private Button learnAlphabets, visualizeAlphabets;
	private ImageView setting;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		learnAlphabets = (Button)findViewById(R.id.learn_alphabets);
		visualizeAlphabets = (Button)findViewById(R.id.visualize_alphabets);
		setting = (ImageView)findViewById(R.id.setting);
		
		
		learnAlphabets.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {				
      	
      	Intent i = new Intent(getApplicationContext(), LearnAlphabets.class);
      	startActivity(i);
      	finish();
    	
      }
     });
		visualizeAlphabets.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {				
      	
      	Intent i = new Intent(getApplicationContext(), QuestionCategory.class);
      	startActivity(i);
      	finish();
    	
      }
     });
		setting.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {				
      	
      	Intent i = new Intent(getApplicationContext(), Setting.class);
      	startActivity(i);
      	finish();
    	
      }
     });
		
	}

}
