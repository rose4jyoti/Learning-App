package com.learnalphabets;

import java.util.ArrayList;

import com.learnalphabet.database.DataBaseAdapter;
import com.learnalphabets.extras.BeanClass;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
  private String[] alphabets = new String[6]; 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_alphabet);
		
		gridView = (GridView) findViewById(R.id.gridView);
		currentText = (TextView)findViewById(R.id.current_text);
		oneAlphabet = BeanClass.getAlphabetSelected();
		currentText.setText(oneAlphabet);
		
		
		if(!(oneAlphabet.equals("A"))){
			
			Toast.makeText(getApplicationContext(),"Testing Please click on A text", Toast.LENGTH_SHORT).show();
		
			String[] testingAlphabets = new String[]{"test","test","test","test","test","test"};
			
			gridView.setAdapter(new AlphabetAdapter(this, testingAlphabets));
	  	
			
		
		}else{
		
		 /*
		  * Set database
		  */
		DataBaseAdapter mDbHelper = new DataBaseAdapter(this);         
  	mDbHelper.createDatabase();       
  	mDbHelper.open();
  	mDbHelper.getSixAlphabet(oneAlphabet);
  	//Toast.makeText(getApplicationContext(), "Data selected", 1000).show();
  	mDbHelper.close();
  	
  	/*
  	 * get the six alphabet name from database 
  	 */
  	ArrayList arrayList = BeanClass.getArrayList();
		for(int i=0; i<arrayList.size(); i++){
		
			String data = (String) arrayList.get(i);
			alphabets[i] = data;
		}
		gridView.setAdapter(new AlphabetAdapter(this, alphabets));
  	
		}
		
    /*
     * get the name by clicking on gridview
     */
  	
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {
				
				String aName = (String) ((TextView) v.findViewById(R.id.label)).getText();
				
			//	Toast.makeText(getApplicationContext(),aName, Toast.LENGTH_SHORT).show();
				
				Bundle bundle = new Bundle();
        bundle.putString("CategoryName", oneAlphabet);
        bundle.putString("ImageName",aName);
				Intent i = new Intent(getApplicationContext(), ShowCategory.class);
				i.putExtras(bundle);
				startActivity(i);
				finish();
			}
		});
		
		
	}
	@Override
	public void onBackPressed() {
		
		Intent i = new Intent(getApplicationContext(), LearnAlphabets.class);
		startActivity(i);
		finish();
	}
}
