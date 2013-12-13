package com.learnalphabets;

import com.learnalphabets.extras.BeanClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;

public class Setting extends Activity{
	
	CheckBox chk1, chk2;
	
	public void onCreate (Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.setting);
		
		chk1 = (CheckBox)findViewById(R.id.check1);
		chk2 = (CheckBox)findViewById(R.id.check2);
		
		
		if(BeanClass.getAlphabetSound()){
			chk1.setChecked(true);
		}else{
			chk1.setChecked(false);
		}
		
		chk1.setOnClickListener(new OnClickListener() {
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				
				//Toast.makeText(Setting.this,  "Bro, try Android :)", Toast.LENGTH_LONG).show();
				BeanClass.setAlphabetSound(true);
				
			}else{
				BeanClass.setAlphabetSound(false);
				
			}
		  }
		});
		chk2.setOnClickListener(new OnClickListener() {
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				
				//Toast.makeText(Setting.this,  "Bro, try Android :)", Toast.LENGTH_LONG).show();
				BeanClass.setBgSound(true);
			}else{
				
				BeanClass.setBgSound(false);
			}
	 
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
