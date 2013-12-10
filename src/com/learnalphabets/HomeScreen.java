package com.learnalphabets;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends Activity {

	private Button learnAlphabets;
	private Button visualizeAlphabets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		learnAlphabets = (Button)findViewById(R.id.learn_alphabets);
		visualizeAlphabets = (Button)findViewById(R.id.visualize_alphabets);
		
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
		
	}

}
