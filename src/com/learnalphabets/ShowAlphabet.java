package com.learnalphabets;

import com.learnalphabet.database.DataBaseAdapter;
import com.learnalphabets.extras.BeanClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowAlphabet extends Activity {

	private TextView currentText;
	private GridView gridView;
  private String oneAlphabet;
	 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_alphabet);
		
		gridView = (GridView) findViewById(R.id.gridView);
		currentText = (TextView)findViewById(R.id.current_text);
		oneAlphabet = BeanClass.getAlphabetSelected();
		currentText.setText(oneAlphabet);
		 
		Toast.makeText(getApplicationContext(), oneAlphabet, 1000).show();
		
		 /*
		  * Set database
		  */
		 
		DataBaseAdapter mDbHelper = new DataBaseAdapter(this);         
  	mDbHelper.createDatabase();       
  	mDbHelper.open();
  	mDbHelper.getSixAlphabet(oneAlphabet);
  	Toast.makeText(getApplicationContext(), "Data selected", 1000).show();
  	

		String[] alphabets = new String[] { "Apple", "Aeroplane","Ant", "Arm","Alligator","Arrow" };
		
		gridView.setAdapter(new AlphabetAdapter(this, alphabets));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
						getApplicationContext(),
						((TextView) v.findViewById(R.id.label)).getText(), Toast.LENGTH_SHORT).show();

			}
		});
		
		
		mDbHelper.close();
	}
	
	
	
	
	@Override
	public void onBackPressed() {
		
		Intent i = new Intent(getApplicationContext(), LearnAlphabets.class);
		startActivity(i);
		finish();
	}

}
