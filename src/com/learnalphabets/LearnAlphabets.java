package com.learnalphabets;

import com.learnalphabets.extras.BeanClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class LearnAlphabets extends Activity {
	 
		private GridView gridView;
	 
		private static final String[] numbers = new String[] { 
				"A", "B", "C", "D", "E",
				"F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O",
				"P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z"};
	 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	 
			setContentView(R.layout.learn_alphabets);
	 
			gridView = (GridView) findViewById(R.id.gridView1);
	    
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_alphabet_grid, R.id.text, numbers);
			gridView.setAdapter(adapter);
	 
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
					
					String alphabet = (String) ((TextView) v.findViewById(R.id.text)).getText();
					
					BeanClass.setAlphabetSelected(alphabet);
					
					Intent i = new Intent(getApplicationContext(), ShowAlphabet.class);
					startActivity(i);
					finish();
				   
				}
			});
	 
		}
		@Override
		public void onBackPressed() {
			
			Intent i = new Intent(getApplicationContext(), HomeScreen.class);
			startActivity(i);
			finish();
		}
	 
	}