package com.learnalphabets;

import java.util.ArrayList;
import java.util.Random;
import com.learnalphabet.database.DataBaseHelper;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ShowCategory extends Activity {
	
	private static final int SWIPE_MIN_DISTANCE       = 120;
  private static final int SWIPE_MAX_OFF_PATH       = 250;
  private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetector gestureDetector           = null;
	private DataBaseHelper myDbHelper                 = null;
	private SQLiteDatabase db                         = null;
	
	private Button btnNext         = null;
	private Button btnPrevious     = null;
	
	private ImageButton btnSetting = null;
	private ImageButton btnHome    = null;
	
	private ImageView img          = null;
	private ImageView imageView    = null;
	private ImageView image        = null;
	private ViewFlipper flipper    = null;
	
	private LinearLayout layout    = null;
	
	private int swipeInt;
	private int itotalImage;
	private int position;
	private int ID;
	private Integer[] intID;	
	private Integer[] ranArrID;
	private static int temp = 1;
	private int musicLength=0;
	private int viewScroll=0;
	
	private Boolean isVoice = true;
	private Boolean isText = true;
	private Boolean isSwipe = false;
	private Boolean isRandomOrder = false;    
	
	  
	private String fImage  		 = "";
	private String strName 		 = "";
	private String lengStrName = "";
	private String Category 	 = "";
	private String Image 	 = "";
	private String textTemp 	 = "";
	private String Swipe    	 = "";
	private int fromFinish  	 = 0;
	
	private String[] imageName; 
  private String[] titleName;
	private String[] soundName; 
	private String[] StringArray;
	private String[] textHeaderArray;
	private String[] soundArray;
	
	
	private Handler mHandler             = new Handler();
	private MediaPlayer mediaPlayer      = null;
	private MediaPlayer actMediaPlayer   = null;
	private Boolean isMediaPlayerActive  = false;
	private Boolean isActualSoundPlaying = false;	
	
	ArrayList<Integer> arrayRecords      = new ArrayList<Integer>();
	ArrayList<Integer> arrayIDRecords    = new ArrayList<Integer>();
	ArrayList<String> arrayImageRecords  = new ArrayList<String>();
	//ArrayList<String>  NextCategory      = new ArrayList<String>();
  final ArrayList<String>  NextFlashCategory= new ArrayList<String>();
	
  
	private Animation inFromRightAnimation() {
		 
		Animation inFromRight = new TranslateAnimation(
		Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
		Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		);
		inFromRight.setDuration(300);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	  }
	
	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
		  Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  -1.0f,
		  Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		);
		outtoLeft.setDuration(300);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}
	
	private Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
		Animation.RELATIVE_TO_PARENT,  -1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
		Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		);
		inFromLeft.setDuration(300);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}
	private Animation outToRightAnimation() {
	    Animation outtoRight = new TranslateAnimation(
	    Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  +1.0f,
		Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		);
		outtoRight.setDuration(300);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}
	
	class MyGestureDetector extends SimpleOnGestureListener {
		

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      	Intent intent = new Intent(ShowCategory.this.getBaseContext(), ShowCategory.class);
    	
        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
            return false;
        }
        
        
        
        // right to left swipe
        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				
				ShowCategory.this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

				   btnPrevious = (Button) findViewById(R.id.btnprevious);
		    	 btnNext     = (Button) findViewById(R.id.btnnext);
		    	 btnPrevious.setVisibility(4);
		    	 btnNext.setVisibility(4);
		    	 final TextView txtHeader = (TextView) findViewById(R.id.headerText);
				  
				  try {
					   mediaPlayer.stop();
					   mediaPlayer.release();
					   actMediaPlayer.stop();
					   actMediaPlayer.release();
						 
  		 	   } catch (Exception e) {
  				  e.fillInStackTrace();
  			   }   
				  
				
					
					
					
				  if(!isRandomOrder){
				    if (itotalImage > temp) {
				    	try{
				    	  flipper = (ViewFlipper) findViewById(R.id.flipper);
						  String strcat = imageName[temp];
						  strcat = strcat.trim(); 
						  strcat = strcat.toLowerCase();	
						  int test = getResources().getIdentifier(strcat, "drawable", getPackageName());
						  imageView = (ImageView)findViewById(R.id.AImage);
						  imageView.setImageResource(test);
						  imageView.setScaleType(ImageView.ScaleType.FIT_XY);
						  imageView.setClickable(true);
				    	 }catch(Exception e){
				    		 Log.i("Error","Error in 1439 String Array"+e);
		    		       }
				    	 final TextView txtHeader2 = (TextView) findViewById(R.id.speakar); 
				   	    	///  add on 03/04/12
								txtHeader2.setOnClickListener(new  View.OnClickListener() {        
							    	 public void onClick(View v) {
							    		 try {
							    			if (isMediaPlayerActive) {
					 				    	 	 mediaPlayer.stop();
					 				    		 mediaPlayer.release();
								    		 }	 
							    		 } catch (Exception e) {
										e.fillInStackTrace();
									 } finally {
										// 
									 }
							    		 	 
							   		 try { 
							   		     String strsnd1 = soundName[temp-1];
					    	    	   strsnd1 = strsnd1.replace(".mp3", "");
					    	    	   strsnd1 = strsnd1.trim();
					    	    	   strsnd1 = strsnd1.replaceAll(" ", "_");
					    	    	   final int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
					    	    	  
					    	    	   mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
					    	    	   if(isVoice) {
					    	    		   mediaPlayer.setVolume(100,100);
					    	    	   } else {
					    	    		   mediaPlayer.setVolume(0,0);
					    	    	   }	    
					    	    	   mediaPlayer.start();
					    	    	   isMediaPlayerActive = true;
					    	    	   } catch (Exception e) {
											e.fillInStackTrace();
										 } finally {
											 //
										 }
							    	 }
						 			});
					
						final TextView txtHeader4 = (TextView) findViewById(R.id.headerText); 
		    		    				   	   // 	///  add on 03/04/12
		    		    txtHeader4.setOnClickListener(new  View.OnClickListener() { 
				    	       public void onClick(View v) {
				    	    	   try {
				    	    	    	 mediaPlayer.stop();
				    	    	    	 mediaPlayer.release();
				    	    	    	 actMediaPlayer.stop();
				    	    	    	 actMediaPlayer.release();	
				    	    	   } catch (Exception e) {
									  e.fillInStackTrace();
								   } finally {
									  Log.i("Error","ERROR in 1422");
								   }
								   
								   try {
					    	    	   int tmp = temp - 1 ;
					    	    	   String strsnd1 = soundName[tmp];
					    	    	   strsnd1 = strsnd1.replace(".mp3", "");
					    	    	   strsnd1 = strsnd1.trim();
					    	    	   strsnd1 = strsnd1.replaceAll(" ", "_");
					    	    	   strsnd1 = strsnd1.toLowerCase();
					    	    	   final int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
					    	    	    
					    	    	   mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
					    	    	   if(isVoice) {
					    	    		   mediaPlayer.setVolume(100,100);
					    	    	   } else {
					    	    		   mediaPlayer.setVolume(0,0);
					    	    	   }	   
					    	    	   
					    	    		   mediaPlayer.start();   
					    	    	   } catch (Exception e) {
										 e.fillInStackTrace();
									   } finally {
										   //
									   }
					    	    	   
				    	       	  }
				    	   	   });
				   //Create Header Text.
					   try{
				     strName = titleName[temp];
					   mHandler.postDelayed(new Runnable() {
							
							public void run() {
								txtHeader.setText(strName); 
							}
						}, 100);
			 		        
		          //Add Image in Flipper. 
				 	  flipper.setInAnimation(inFromRightAnimation());
					  flipper.setOutAnimation(outToLeftAnimation());
					  flipper.showNext();
					   }catch(Exception e){
						   Log.i("Error","Error in 1145 String Array"+e);
	    		       }
				 	 			  
					  try {  
		    	    	   String strsnd1 = soundName[temp];
		    	    	   strsnd1 = strsnd1.replace(".mp3", "");
		    	    	   strsnd1 = strsnd1.trim();
		    	    	   strsnd1 = strsnd1.replaceAll(" ", "_");
		    	    	   strsnd1 = strsnd1.toLowerCase();
		    	    	   final int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
		    	    	    
		    	    	   mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
		    	    	   if(isVoice) {
		    	    		   mediaPlayer.setVolume(100,100);
		    	    	   } else {
		    	    		   mediaPlayer.setVolume(0,0);
		    	    	   }	   
		    	    	  
		    	    		   // mediaPlayer.start();   
		    	    	       isMediaPlayerActive = true;
		    	       } catch (Exception e) {
							 e.fillInStackTrace();
							 Log.i("Error","ERROR in 1426"+e);
				   } finally {
					   Log.i("Error","ERROR in 1429 FINNALLLLLLLLLLLLLY"); 
				   }   
					  int nxtid = intID[temp];  
	    	    	  SQLiteQueryBuilder qbActtwo = new SQLiteQueryBuilder();
	    	    	  qbActtwo.setTables("Alphabets");       
	    	    	  SQLiteDatabase dbActtwo = myDbHelper.getReadableDatabase();
	    	    	  Cursor curActtwo = qbActtwo.query(dbActtwo, null, "Id"+" = "+"'"+nxtid+"'" , null, null, null,null,"1");
	    	    	  //Toast.makeText(ShowCategory.this, Integer.toString(curActtwo.getCount())  + "2", Toast.LENGTH_LONG).show(); 	    	     
	    	    	  String strsnd5;
	    	    	  if(curActtwo.moveToFirst()) {
	    	    		  try{  
	    	    		  
	    		    		 strsnd5 = curActtwo.getString(3);
	    	    		   strsnd5 = strsnd5.replace(".mp3", "");
	    	    	     strsnd5 = strsnd5.trim();
	    			   		 strsnd5 = strsnd5.replaceAll(" ", "_");
	    			   		 strsnd5 = strsnd5.replaceAll("-", "_");
	    			   		 strsnd5 = strsnd5.toLowerCase();
	    			   		 
	    			   		 int idsnd5 = getResources().getIdentifier(strsnd5 , "raw", getPackageName());
	    			   		 
		     	    	     if(idsnd5 != 0) {   
		     	    	    	 	if(isActualSoundPlaying) {
		     	    	    	 	actMediaPlayer.release();	
			     		  	   		} 
		     	    	    	 	actMediaPlayer = MediaPlayer.create(getBaseContext(), idsnd5);
		     			   		    actMediaPlayer.setVolume(100, 100);
		     			   		    mHandler.postDelayed(new Runnable() {
			   						
			   					    public void run() {
			   							try {
			   								actMediaPlayer.start();
			   							    isActualSoundPlaying = true;
			   							} catch (Exception e) {
											e.fillInStackTrace();
											Log.i("Error","Error in 1506"+e);
										} finally {
											Log.i("Error","Finally in 1506");
										}	
			   	 					    }
			   					    },700);   
		     			   		     
		     			   	 	    actMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
									
									public void onCompletion(MediaPlayer actMediaPlayer) {
										 try {
											 actMediaPlayer.release();
											 mediaPlayer.start();
											 isMediaPlayerActive = true;
										 } catch (Exception e) {
											 Log.i("Error","Errorrrrr22>>>" + e);
										 } finally {
											//
										 } 		 	       			 
									}
								});	      	 
		     	    	     } 
		     	    	     
		     	    	     else {    	    	
		     	    	    	 mHandler.postDelayed(new Runnable() {
				   						
				   					    public void run() {
			
				   							try {
				   								 mediaPlayer.start();
				   								isMediaPlayerActive = true;
				   							} catch (Exception e) {
				   								Log.i("Error","Errorrrrr66>>>" + e);
											} finally {
												//
											} 
				   							
				   	 					  }
				   				 },700);    	    
		     	    	     }
	    	    		  }catch(Exception e){
	    	    			  Log.i("Error","Error in 1481");  
	    	    		  }
					} // IF MoveToFirst      
	    	    	curActtwo.close();   
		     	    dbActtwo.close();      
					temp++;	
					
					if(temp == itotalImage) {
		            	btnNext.setVisibility(4); //Hide the next button
		            } else {
		            	btnNext.setVisibility(4); //Show the next button 
		            }    
				 } //IF // edited ajay
				    else{
				    	
				    //	Toast.makeText(getApplicationContext()," To Do ", Toast.LENGTH_SHORT).show();
				    	
			    	   /* final Bundle bundle = new Bundle();
			    		  bundle.putString("isCommingFromLearning", "true");
			    		  bundle.putString("isCommingFromQuestion", "false");
				        bundle.putString("CategoryName", Category);
				        bundle.putStringArrayList("NextCategoryName", NextFlashCategory);
				        bundle.putInt("Position", position);
				        
				        bundle.putInt("musicLength", musicLength);
				        bundle.putInt("viewScroll", viewScroll);*/
					 
				        Intent finishpage = new Intent(ShowCategory.this,ShowAlphabet.class);
				       // finishpage.putExtras(bundle);
		            startActivity(finishpage);
		             
		             finish();
				    	
				    }
			    } 
			  
			// right to left swipe
        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				//startActivity(intent);
        		Log.i("Error","Swithsc to left side");
			  ShowCategory.this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
				PreView();
        }

        return false;
    }
    
    // It is necessary to return true from onDown for the onFling event to register
    @Override
    public boolean onDown(MotionEvent e) {
      	return true;
    }
	}
	
	public void onCreate(Bundle savedInstanceState) throws NullPointerException,OutOfMemoryError{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.show_category);
    
   	Bundle bundle = this.getIntent().getExtras();
    Category      = bundle.getString("CategoryName");
    Image         = bundle.getString("ImageName");
    
    Toast.makeText(getApplicationContext(), Image, 1000).show();
   
    btnSetting    = (ImageButton) findViewById (R.id.setting_btn1);
    btnHome       = (ImageButton) findViewById(R.id.home_btn1);
	      
    btnPrevious   = (Button) findViewById(R.id.btnprevious);
   	btnNext       = (Button) findViewById(R.id.btnnext);
   	
    btnSetting.setOnClickListener(settingsListener);
	  btnHome.setOnClickListener(homeListener);
	   
      myDbHelper = new DataBaseHelper(this);
      
      temp  = 1;
 
      fetchSettingsTable();
      
      fetchTextTableData();
      
      if(isSwipe){
      	
      	setSwipeFunctionality();
      }  
      
      final TextView text = (TextView) findViewById(R.id.headerText);
      
      if(isText){
      	text.setVisibility(View.VISIBLE);
      }else{
      	text.setVisibility(View.GONE);
      }
      
	try {
	    //Fetching total record from Item table
        SQLiteQueryBuilder qbOne = new SQLiteQueryBuilder();
        qbOne.setTables("Alphabets");       
        SQLiteDatabase dbOne     = myDbHelper.getReadableDatabase();
        Cursor cur               = null;
        if("Numbers".equalsIgnoreCase(Category)){ 
            cur = qbOne.query(dbOne, null, "Alphabet"+" = "+"'"+Category+"'" , null, null, null,"Id ASC");
            
        }
        else{
        	cur = qbOne.query(dbOne, null, "Alphabet"+" = "+"'"+Category+"'" , null, null, null,"Id ASC");
        }
        itotalImage = cur.getCount();
        
        dbOne.close();
        
        setImageDetails(cur);
        
        if(!isRandomOrder){
        	
        
      	   final TextView txtHeader = (TextView) findViewById(R.id.headerText);
             String strName = titleName[0];
             
           try { 
          	 
        	    txtHeader.setText(strName); 
        	    
        	    
        	    String str =  imageName[temp];
        	    
        	   
        	    
        	    
        	    
	            str        = str.trim();
	            str        = str.toLowerCase();
	            
	            img        = (ImageView)findViewById(R.id.AImage);
	            int id     = getResources().getIdentifier(str ,"drawable", getPackageName());
	            img.setImageResource(id);
	            img.setScaleType(ImageView.ScaleType.FIT_XY);  
	            
	    	    
        	} catch(Exception n) {
				n.printStackTrace();
				Log.i("Error","Array Error="+n);
			} finally {
				txtHeader.setText(strName); 
	        	String str = imageName[0];
	            str        = str.trim();
	            str        = str.toLowerCase();
	            img        = (ImageView)findViewById(R.id.AImage);
	            int id     = getResources().getIdentifier(str ,"drawable", getPackageName());
	        	  img.setImageResource(id);
	        	
			}
		
		
      }
      
      ArrayList<String> strArySound = new ArrayList<String>();  
     // ArrayList<String> strActSound = new ArrayList<String>();  
      if (cur.moveToFirst()) {
          do {
          	try {
            	String mSoundRaw = cur.getString(3);
            	
            	strArySound.add(mSoundRaw);
            	//String mActSoundRaw = cur.getString(3);
            //	strActSound.add(mActSoundRaw);
          	} catch(Exception n) {
  				n.printStackTrace();
  				Log.i("Error","Array Error="+n);
  			}
          } while (cur.moveToNext());
      }
      
      	soundName = (String[]) strArySound.toArray(new String[strArySound.size()]);
      	
  	
  
 	    	 final TextView txtHeader = (TextView) findViewById(R.id.speakar); 
 	    	///  add on 03/04/12
			  txtHeader.setOnClickListener(new  View.OnClickListener() {        
		    	 public void onClick(View v) {
		    	 	 try { 
			   			if (isMediaPlayerActive) {
				    	 	 mediaPlayer.stop();
				    		 mediaPlayer.release();
			    		 }	 
			   		   String strsnd1 = soundName[temp-1];
			   		   startMediaplayerSpeaker(strsnd1);
			   		   
	    	    	  } catch (Exception e) {
							e.fillInStackTrace();
					  } finally {
						 //
					  }
		    	 }
	 			});
			  
			  
			
			 final TextView txtHeader3 = (TextView) findViewById(R.id.headerText); 
			 txtHeader3.setOnClickListener(new  View.OnClickListener() {
	    	       public void onClick(View v) {
	    	    	   try {
	    	    	  	mediaPlayer.stop();
    	    		    mediaPlayer.release();
    	    		    actMediaPlayer.stop();
    	    		    actMediaPlayer.release();
    	    	   } catch (Exception e) {
					 e.fillInStackTrace();
				   } finally {
					  // 
				   }
				   try{
  	    	    	   String strsnd1 = soundName[0];
  	    	    	   mediaPlayer.release(); 
  	    	    	   startMediaplayer(strsnd1);
  	    	    	   
				   } catch (Exception e) {
						//
				   } finally {
						   
				   }
	    	       	  }
	    	   	   });
			   
			   
	       String strsnd1   = soundName[0];
		 	   strsnd1          = strsnd1.replace(".mp3", "");
		 	   strsnd1          = strsnd1.trim();
		 	   strsnd1          = strsnd1.replaceAll(" ", "_");
		 	   final int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
		 	   mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd1);
		 	   
		 	  // Toast.makeText(getApplicationContext(), strsnd1, 1000).show();
		 	   
		 	   if(isVoice) {
		 		   mediaPlayer.setVolume(100,100);   
		 		   
		 	   } else {
		 		   mediaPlayer.setVolume(0,0);
		 	   } 	
		 	   
		 	   try {
						mediaPlayer.start();
					} catch (Exception e) {
	
						Log.i("Error","Errorrrrr>>>" + e);
					} finally {
						mediaPlayer.start();
						isMediaPlayerActive = true;
					} 
		 	   
      cur.deactivate();
      cur.close();
      dbOne.close();
	} catch (Exception e) {
		e.fillInStackTrace();
	} finally {
		//
	} 
	
	
  
	    
   	/**
   	 * Prevoius click event. wil show previous item.
   	 */
    
    btnPrevious = (Button) findViewById(R.id.btnprevious);
    btnPrevious.setVisibility(4);	
    
    btnPrevious.setOnClickListener(new View.OnClickListener() {
		
	public void onClick(View v) {
		final TextView txtHeader = (TextView) findViewById(R.id.headerText);
		txtHeader.setText(null);	
	try {
				 mediaPlayer.stop();
				 mediaPlayer.release();
	} catch (Exception e) {
		   e.fillInStackTrace();
	}
	
		if(temp >= 2){	
			flipper = (ViewFlipper) findViewById(R.id.flipper);
			temp    = temp - 2 ;
			
			if(temp<0)
				temp=0;
			
	     if(temp != 0){
	    	 
	    	
        	btnPrevious.setVisibility(0);
        	btnNext.setVisibility(0);
        	
        	try{
	        String strsnd   = soundName[temp];
			    strsnd          = strsnd.replace(".mp3", "");
			    strsnd          = strsnd.trim();
			    strsnd          = strsnd.replaceAll(" ", "_");
			    final int idsnd = getResources().getIdentifier(strsnd , "raw", getPackageName());					                    
			    mediaPlayer     = MediaPlayer.create(getBaseContext(), idsnd);
			    
				if(isVoice) {
				  mediaPlayer.setVolume(100,100);
				
				} else {
				  mediaPlayer.setVolume(0,0);	
				}
				
				
	      }catch(Exception e){
	        		Log.i("Error","Errorrrrrrrrr in 935 String Array=>"+"temp"+temp+"ERROR=>"+e);
			   }
	        
        	try {
					   mediaPlayer.start();
					   isMediaPlayerActive = true;
				  }catch(Exception e) {
					  Log.i("Error","Errorrrrr>>>" + e);
				   } finally {
					  //
				   } 
        	
        	
	        	
	        } else {
	        	 
	        		String strsnd   = soundName[0];
			        strsnd          = strsnd.replace(".mp3", "");
			        strsnd          = strsnd.trim();
			        strsnd          = strsnd.replaceAll(" ", "_");
			        final int idsnd = getResources().getIdentifier(strsnd , "raw", getPackageName());					                    
			        try{
				        mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd);
						if(isVoice) {
							mediaPlayer.setVolume(100,100);   //till
							
						} else {
							mediaPlayer.setVolume(0,0);
						}
			        }catch(Exception e){
			        	Log.i("Error","Error in 1079 String Array"+e);
	    		    }
			        
			        try {
							   actMediaPlayer.release();
							   mediaPlayer.start();
							   isMediaPlayerActive = true;
						  }catch(Exception e) {
							  Log.i("Error","Errorrrrr>>>" + e);
						   } finally {
							   //
						   } 	
	
	        	btnPrevious.setVisibility(4);
	        	imageView.setVisibility(0);
	        }
	       	       
	        String strcat = imageName[temp];
	        strcat = strcat.trim(); 
	        strcat = strcat.toLowerCase();	
	        
	    imageView = (ImageView)findViewById(R.id.AImage);
			int test = getResources().getIdentifier(strcat, "drawable", getPackageName());
			imageView.setImageResource(test);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			
			// txt hdr code start
	       
			txtHeader.setOnClickListener(new  View.OnClickListener() {
	    	       public void onClick(View v) {
	    	    	   try {
							 mediaPlayer.stop();
							 mediaPlayer.release();
							 actMediaPlayer.stop();
						   actMediaPlayer.release();
	    	    	   }catch(Exception e) {
						 e.fillInStackTrace();
					   } finally {
						  // 
					   }
	    	    	   int tmp          = temp - 1 ;
	    	    	   String strsnd1   = soundName[tmp];
	    	    	   startMediaplayer(strsnd1);
	    	    	  /* strsnd1          = strsnd1.replace(".mp3", "");
	    	    	   strsnd1          = strsnd1.trim();
	    	    	   strsnd1          = strsnd1.replaceAll(" ", "_");
	    	    	   final int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
	    	    	   mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd1);
	    	    	   if(isVoice) {
	    	    		   mediaPlayer.setVolume(100,100);
	    	    	   } else {
	    	    		   mediaPlayer.setVolume(0,0);
	    	    	   }	   
	    	    	   mediaPlayer.start();
	    	    	   isMediaPlayerActive = true;*/
	    	       	  }
	    	   	   });
			
			
			// txt hdr code end 
	     
	        try{
	      
		        if(temp <= 0){
		        	 strName = titleName[0];
		        }
		        else
		        	strName  = titleName[temp];
	        }catch( Exception a){
	        	Log.i("Error","Errorrrrrrrrr in 635 String Array"+temp);
        	}
        	finally{
        		Log.i("finaly","Erroe in 638 String Array ...FINALLY");
        	}
			 	
	        mHandler.postDelayed(new Runnable() {
				public void run() {
					txtHeader.setText(strName); 
				}
			}, 100);	    
	        	
	        flipper.setInAnimation(inFromLeftAnimation());
	        flipper.setOutAnimation(outToRightAnimation());
	        flipper.showPrevious(); 
	        temp++; 
		   }	
		 
	   }
	});
    


/**
 * next click event. wil show next item.
 */
    
    
    btnNext = (Button) findViewById(R.id.btnnext);	
    btnNext.setOnClickListener(new View.OnClickListener() {	
		
		public void onClick(View v) {
			
		   final TextView txtHeader = (TextView) findViewById(R.id.headerText);
		   txtHeader.setText(null);
		     	
		   try {
			   mediaPlayer.stop();
			   mediaPlayer.release();
			   actMediaPlayer.stop();
			   actMediaPlayer.release();
				 
 	   } catch (Exception e) {
		  e.fillInStackTrace();
	   }   
		   
		
		    btnPrevious.setVisibility(0);
		  
		    if (itotalImage > temp) {
		    	try{
		    	flipper       = (ViewFlipper) findViewById(R.id.flipper);
				  String strcat = imageName[temp];
				  strcat        = strcat.trim(); 
				  strcat        = strcat.toLowerCase();	
				  int test      = getResources().getIdentifier(strcat, "drawable", getPackageName());
				  imageView     = (ImageView)findViewById(R.id.AImage);
				  imageView.setImageResource(test);
				  imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				  imageView.setClickable(true);
		    	 }catch(Exception e){
		    		 Log.i("Error","Error in 1439 String Array"+e);
    		     }
		    	txtHeader.setOnClickListener(new  View.OnClickListener() {
		    	       public void onClick(View v) {
		    	    	   try {
		    	    	    	 mediaPlayer.stop();
		    	    	    	 mediaPlayer.release();
		    	    	    	 actMediaPlayer.stop();
		    	    	    	 actMediaPlayer.release();	
		    	    	   } catch (Exception e) {
							 e.fillInStackTrace();
						   } finally {
							   Log.i("Error","ERROR in 1422");
						   }
						   
						   try {
			    	    	   int tmp          = temp - 1 ;
			    	    	   String strsnd1   = soundName[tmp];
			    	    	   strsnd1          = strsnd1.replace(".mp3", "");
			    	    	   strsnd1          = strsnd1.trim();
			    	    	   strsnd1          = strsnd1.replaceAll(" ", "_");
			    	    	   strsnd1          = strsnd1.toLowerCase();
			    	    	   final int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			    	    	    
			    	    	   mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd1);
			    	    	   if(isVoice) {
			    	    		   mediaPlayer.setVolume(100,100);
			    	    		 //  mediaPlayer.setVolume(0,0);
			    	    	   } else {
			    	    		   mediaPlayer.setVolume(0,0);
			    	    	   }	   
			    	    	   
			    	    		   mediaPlayer.start();   
			    	    	   } catch (Exception e) {
								 e.fillInStackTrace();
							   } finally {
								   //
							   }
			    	    	   
		    	       	  }
		    	   	   });
		      //Create Header Text.
			   try{
			       strName = titleName[temp];
			       mHandler.postDelayed(new Runnable() {
					
					public void run() {
						txtHeader.setText(strName); 
					}
				}, 100);
	 		        
			  flipper.setInAnimation(inFromRightAnimation());
			  flipper.setOutAnimation(outToLeftAnimation());
			  flipper.showNext();
			   }catch(Exception e){
				   Log.i("Error","Error in 1145 String Array"+e);
  		       }
		 	 			  
			  try {  
    	    	   String strsnd1   = soundName[temp];
    	    	   startMediaplayer(strsnd1);
    	    	  
    	       } catch (Exception e) {
					 e.fillInStackTrace();
					 Log.i("Error","ERROR in 1426"+e);
				   } finally {
					   Log.i("finally","ERROR in 1429 FINNALLLLLLLLLLLLLY"); 
					  	
				   }   
			      int nxtid = intID[temp];  
  	    	  SQLiteQueryBuilder qbActtwo = new SQLiteQueryBuilder();
  	    	  qbActtwo.setTables("Alphabets");       
  	    	  SQLiteDatabase dbActtwo = myDbHelper.getReadableDatabase();
  	    	  Cursor curActtwo = qbActtwo.query(dbActtwo, null, "Id"+" = "+"'"+nxtid+"'" , null, null, null,null,"1");
  	    	  String strsnd5;
  	    	  if(curActtwo.moveToFirst()) {
  	    		  try{  
  	    		  
  		    		 strsnd5 = curActtwo.getString(3);
  	    		   strsnd5 = strsnd5.replace(".mp3", "");
  	    	     strsnd5 = strsnd5.trim();
  			   		 strsnd5 = strsnd5.replaceAll(" ", "_");
  			   		 strsnd5 = strsnd5.replaceAll("-", "_");
  			   		 strsnd5 = strsnd5.toLowerCase();
  			   		 
  			   		 int idsnd5 = getResources().getIdentifier(strsnd5 , "raw", getPackageName());
  			   		 
     	    	     if(idsnd5 != 0) {   
     	    	    	// setActualPlayer(idsnd5);
     	    	     }else {    	    	
     	    	    	 mHandler.postDelayed(new Runnable() {
		   						
		   					    public void run() {
	
		   							try {
		   							//	 mediaPlayer.start();
		   								isMediaPlayerActive = true;
		   							} catch (Exception e) {
		   								Log.i("Error","Errorrrrr66>>>" + e);
									} finally {
										//
									} 
		   							
		   	 					  }
		   				 },700);    	    
     	    	     }
  	    		  }catch(Exception e){
  	    			  Log.i("Error","Error in 1481");  
  	    		  }
			} // IF MoveToFirst      
  	    	curActtwo.close();   
     	    dbActtwo.close();      
			temp++;	
			
			if(temp == itotalImage) {
            	//btnNext.setVisibility(4); //Hide the next button
            	
            	// change ajay
            	
            	
            	
            } else {
            	btnNext.setVisibility(0); //Show the next button 
            }    
		 }else{
			 
			// change ajay
			 
			   /* final Bundle bundle = new Bundle();
			    bundle.putString("isCommingFromLearning", "true");
				  bundle.putString("isCommingFromQuestion", "false");
	        bundle.putString("CategoryName", Category);
	        bundle.putStringArrayList("NextCategoryName", NextFlashCategory);
	        bundle.putInt("Position", position);
	        bundle.putInt("musicLength", musicLength);
	        bundle.putInt("viewScroll", viewScroll);*/
				 Intent finishpage = new Intent(ShowCategory.this,ShowAlphabet.class);
				 //finishpage.putExtras(bundle);
	       startActivity(finishpage);
	       finish(); 
		 }
	     
	  }	
	}); 
 
 }
	
	private void setActualPlayer(int id){
		if(isActualSoundPlaying) {
	    	actMediaPlayer.release();	
	  	  } 
	 	actMediaPlayer = MediaPlayer.create(getBaseContext(), id);
	    actMediaPlayer.setVolume(100, 100);
	    mHandler.postDelayed(new Runnable() {
	    	public void run() {
				try {
					actMediaPlayer.start();
				    isActualSoundPlaying = true;
				} catch (Exception e) {
					e.fillInStackTrace();
					Log.i("Error","Error in 1506"+e);
				} finally {
					Log.i("finally","Finally in 1506");
				}	
			    }
	    },700);   
	     
 	    actMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
 	    	public void onCompletion(MediaPlayer actMediaPlayer) {
				 try {
					 actMediaPlayer.release();
					 
					 
					 //change ajay
					 
					  mediaPlayer.start();
				      isMediaPlayerActive = true;
					 
					 
				 } catch (Exception e) {
					 Log.i("Error","Errorrrrr22>>>" + e);
				 } finally {
					//
				 } 		 	       			 
			   }
	   });	      	 
	}
	private void fetchSettingsTable(){
		/* try {
			    //Fetch the record from setting table. 
			    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		        qb.setTables("tbl_settings");  
		          
		        db       = myDbHelper.getReadableDatabase();
		        Cursor c = qb.query(db,null,null,null,null, null, null, null);
		        if (c.moveToFirst()) {
		        	isVoice       = c.getInt(1) == 0 ? false:true;
		        	isRandomOrder =	c.getInt(2) == 0 ? false:true;
		        	isSwipe       = c.getInt(4) == 0 ? false:true;
		        } else {
		            Toast.makeText(this, "No Record found", 
		            		Toast.LENGTH_LONG).show();
		        }
		        c.deactivate();
		        c.close();
		        db.close();
	        } catch (Exception e) {
				e.fillInStackTrace();
			} finally {
				//do somtng
			}*/
	}
	
	
	
	private void fetchTextTableData(){
		
	     /*  try {
	       	 SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	            qb.setTables("textDisp");  
	            SQLiteDatabase db = myDbHelper.getReadableDatabase();
	            Cursor c          = qb.query(db,null,null,null,null, null, null, null);
	            
	            if (c.moveToFirst()) {
	            	isText       = c.getInt(1) == 0 ? false:true;
	            } else {
	            	Toast.makeText(this, "No Record in Setting.",
	            			Toast.LENGTH_LONG).show();
	            }
	            c.close();    
	            db.close();
	            myDbHelper.close();
	       } catch (Exception e) {
				e.fillInStackTrace(); 
			} finally {
				//do something
			}				*/
	}
	
	
	private void fetchItemTable(){
	//	hvkd
	}
	
	
	/**
	 * home button click listener will show home page.
	 */
	private OnClickListener homeListener = new OnClickListener() {
	  		public void onClick(View v) {
	  			// do something when the button is clicked
  				//Log.i("home","Clickedddddddddd");
	  			 try {
					   mediaPlayer.stop();
					   actMediaPlayer.stop();
	  			 } catch (Exception e) {
  				  e.fillInStackTrace();
  				Log.i("Show Category","background music stop error");
	  			 }   
	  			 
	  			try{
	  				
	  				final Bundle bundle = new Bundle();
			        bundle.putInt("musicLength", musicLength);
			        bundle.putInt("viewScroll", viewScroll);
	  				
	  				Intent i = new Intent(v.getContext(), HomeScreen.class);
	        		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        		i.putExtras(bundle);
	              	startActivity(i);
	              	
	              	finish();
	         	}catch (Exception e) {
	        	    e.printStackTrace();  //Output goes to System.err.
	        	    e.printStackTrace(System.out);  //Send trace to stdout.
	        	} 
	  		}
	  	};
	  	
	/**
	 * settings button click listener will show settings page.
	 */
  	private OnClickListener settingsListener = new OnClickListener() {
  		public void onClick(View v) {
  			// do something when the button is clicked
  		/*	final Bundle bundleSetting = new Bundle();
  	 		bundleSetting.putString("isCommingFromLearning", "true");
  	 		bundleSetting.putString("isCommingFromQuestion", "false");
  	 		bundleSetting.putString("CategoryName", Category);
  	 		bundleSetting.putInt("position", position);
  	 	
  	 		bundleSetting.putStringArrayList("NextCategoryName", NextFlashCategory);
  	 		bundleSetting.putInt("fromFinish",fromFinish);
  	 		bundleSetting.putInt("musicLength", musicLength);
  	 		bundleSetting.putInt("viewScroll", viewScroll);*/
  	 		
  	 		
  			try {
  				Intent i = new Intent(v.getContext(), Setting.class);
	         //	i.putExtras(bundleSetting);
	         	startActivity(i);
	         	mediaPlayer.stop();
	         	finish();
	    	} catch (Exception e) {
	    	    e.printStackTrace();  //Output goes to System.err.
	    	    e.printStackTrace(System.out);  //Send trace to stdout.
	    	}
  		}
  	};
	private void setImageDetails(Cursor cur){
		  ArrayList<Integer> intArrayId  = new ArrayList<Integer>();
    	ArrayList<String> strArrayList = new ArrayList<String>(); 
    	ArrayList<String> strAryTitle  = new ArrayList<String>();
    	 
        if ( cur.moveToFirst()) {
        	do {          
            	int mImageId = cur.getInt(0);
              intArrayId.add(mImageId);
              String mImageRaw = cur.getString(2);       	
            	strArrayList.add(mImageRaw);
            	String mTitleRaw = cur.getString(2);
            	strAryTitle.add(mTitleRaw);
            	
            	
            	System.out.println("database id ======="+cur.getInt(0));
            	System.out.println("database image======="+cur.getString(2));
            	System.out.println("database title======="+cur.getString(2));
            	
            	
            } while (cur.moveToNext());
        }
        intID     = (Integer[]) intArrayId.toArray(new Integer[intArrayId.size()]);
        imageName = (String[]) strArrayList.toArray(new String[strArrayList.size()]);
        titleName = (String[]) strAryTitle.toArray(new String[strAryTitle.size()]);
        
    }

	
	/**
	 * Start mediaplyer and set volume.
	 * @param id
	 */
	private void startMediaplayer(String data){
		Log.i("startMediaplayer", "startddddddddddd  :"+data);
		String  strsnd1  = data;
		strsnd1          = strsnd1.replace(".mp3", "");
	    strsnd1          = strsnd1.trim();
	    strsnd1          = strsnd1.replaceAll(" ", "_");
	    Log.i("startMediaplayer", "strsnd1 :"+strsnd1);
	    final int id     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
	    
	  //  Toast.makeText(getApplicationContext(), strsnd1, 1000).show();
	    
	    
	    mediaPlayer      = MediaPlayer.create(getBaseContext(), id);
	    
	    Log.i("startMediaplayer", "mediaPlayer create with  id :"+id);
	    if(isVoice) {
		    mediaPlayer.setVolume(100,100);
	    } else {
		    mediaPlayer.setVolume(0,0);
	    }	    
	    
	    // change ajay
	    
	    
	    
	   mediaPlayer.start();
	  //  isMediaPlayerActive = true;
	}

	private void startMediaplayerSpeaker(String data){
		Log.i("startMediaplayer", "startddddddddddd  :"+data);
		String  strsnd1  = data;
		strsnd1          = strsnd1.replace(".mp3", "");
	    strsnd1          = strsnd1.trim();
	    strsnd1          = strsnd1.replaceAll(" ", "_");
	    Log.i("startMediaplayer", "strsnd1 :"+strsnd1);
	    final int id     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
	    mediaPlayer      = MediaPlayer.create(getBaseContext(), id);
	    
	    Log.i("startMediaplayer", "mediaPlayer create with  id :"+id);
	    if(isVoice) {
		    mediaPlayer.setVolume(100,100);
	    } else {
		    mediaPlayer.setVolume(0,0);
	    }	    
	    
	    // change ajay
	    
	   mediaPlayer.start();
	   isMediaPlayerActive = true;
	}
	/**
	 * set swipe Functionality based on settings table data.
	 */
	
	private void setSwipeFunctionality(){
		 
	   	Bundle bundle = this.getIntent().getExtras();
	    Category      = bundle.getString("CategoryName");
	  /*NextCategory  = bundle.getStringArrayList("NextCategoryName");
	    fromFinish    = bundle.getInt("fromFinish");
	    position      = bundle.getInt("Position");
	    swipeInt      = bundle.getInt("Swipe");*/
	    
	  /*  String[] mStringArray = new String[NextCategory.size()];
    	mStringArray = NextCategory.toArray(mStringArray);
    	
    	for(int i = 0; i < mStringArray.length ; i++){
    		NextFlashCategory.add(mStringArray[i]);
    		
    	}
    		if(fromFinish != 0){
    			for(int i = 0; i < mStringArray.length ; i++){
    	    		NextFlashCategory.add(mStringArray[i]);
    			if(Category.compareTo(mStringArray[i])==0){
    			i+=1;
    			Category=mStringArray[i];
    			break;
    		 }
    		}
    		
    		}*/
	    
    		//Toast.makeText(getApplicationContext(), Category, Toast.LENGTH_LONG).show();
		
		 RelativeLayout savelayout = (RelativeLayout)this.findViewById(R.id.save_layout);
		 LinearLayout nextPrev     = (LinearLayout)this.findViewById(R.id.btnStrip);
		   	
		 savelayout.setBackgroundResource(0);
	   //	 nextPrev.setVisibility(LinearLayout.GONE);
	   	 
	   	 btnPrevious.setVisibility(4);
	   	 btnNext.setVisibility(4);
	   	 
	   	 gestureDetector           = new GestureDetector(new MyGestureDetector());
	        View mainview          = (View) findViewById(R.id.AImage);
	       
	       // Set the touch listener for the main view to be our custom gesture listener
	       mainview.setOnTouchListener(new View.OnTouchListener() {
	           public boolean onTouch(View v, MotionEvent event) {
	               if (gestureDetector.onTouchEvent(event)) {
	                   return true;
	               }
	               return false;
	           }
	       });
	}
	
	/**
	 * set random data functionality based on data stored in db.
	 * @param cur
	 */
	private void setRandomData(Cursor cur){
		
		ArrayList<Integer> arrayID = new ArrayList<Integer>(); 	
    	if(cur.moveToFirst()) {
    		do {
    			ID = cur.getInt(0);
    			arrayID.add(ID);
    		} while(cur.moveToNext());
    	}
    	arrayRecords = GenRandomNum(arrayID);	
        ArrayList<String> arrayImage = new ArrayList<String>();
    	ArrayList<String> arraySound = new ArrayList<String>();
    	ArrayList<String> textHeader1=new ArrayList<String>();
    	ArrayList<String> lengTextHeader1=new ArrayList<String>();
    	
	
    	for(int i=0;i<arrayRecords.size();i++) {
    		int id=arrayRecords.get(i);
    		
    		SQLiteQueryBuilder qbOne1 = new SQLiteQueryBuilder();
            qbOne1.setTables("Alphabets");       
            SQLiteDatabase dbOne1 = myDbHelper.getReadableDatabase();
            Cursor resultcur = qbOne1.query(dbOne1, null, "Id"+" = "+"'"+id+"'" , null, null, null,"Id ASC");
    		       		
    			if(resultcur.moveToNext()) {
    				try {
		        			arrayIDRecords.add(resultcur.getInt(0));
		        			arrayImage.add(resultcur.getString(2));
		        			arraySound.add(resultcur.getString(3));
		        			textHeader1.add(resultcur.getString(2));
    				} catch(Exception n) {
	        				n.printStackTrace();
	        				System.out.print("Array Error=219"+n);
    				}
    			} ///remove
     
    	  ranArrID = (Integer[]) arrayIDRecords.toArray(new Integer[arrayIDRecords.size()]);
    		soundArray = (String[]) arraySound.toArray(new String[arraySound.size()]);
    		StringArray = (String[]) arrayImage.toArray(new String[arrayImage.size()]);
    		textHeaderArray = (String[]) textHeader1.toArray(new String[textHeader1.size()]);
    		resultcur.close();
    		dbOne1.close();
	     } //For  

	}
	
	/**
	 * Display title
	 * @param c
	 */
	 public void DisplayTitle(Cursor c) {  
	      /*  Toast.makeText(this, 
	                "id: " + c.getInt(0) + "\n" +
	                "Title: " + c.getString(1) + "\n" +
	                "Category: " + c.getString(2) + "\n" +
	                "Image: " + c.getString(3) + "\n" +
	                "Sound: " + c.getString(4) + "\n" +
	                "ActualSound: " + c.getString(5) + "\n" +
	                "Img:  " + c.getBlob(6),
	                Toast.LENGTH_LONG).show();*/
	        
	 } 
	 
	 /**
	  * Function for creating random number.
	  * @param arrayID2
	  * @return
	  */
	 public ArrayList<Integer> GenRandomNum(ArrayList<Integer> arrayID2) {
		 ArrayList<Integer> arraylist = new ArrayList<Integer>();

		 int length = arrayID2.size();
		 Random random=new Random();
		 int randomNum=random.nextInt(431);

		 for(int i=0;i<length;) {
			 randomNum=random.nextInt(431);
			 if(arrayID2.contains(randomNum)) {
				 if(arraylist.indexOf(randomNum)==(-1)) {
					 arraylist.add(randomNum);
					 i++;
					 continue;
				 }
			 }
		 }
		 return arraylist;
	 }
	 
 ///////////////  Call  Previos...	 
	public void PreView(){

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.save_layout);
		//layout.setVisibility(4);
		 btnPrevious = (Button) findViewById(R.id.btnprevious);
    	 btnNext = (Button) findViewById(R.id.btnnext);
    	 btnPrevious.setVisibility(4);
    	 btnNext.setVisibility(4);
    	 
    	 //Toast.makeText(getApplicationContext(), "msg1 msg1", Toast.LENGTH_LONG).show();
    	 
		final TextView txtHeader = (TextView) findViewById(R.id.headerText);
		txtHeader.setText(null);	
		
		//adView.showAds(adSenseSpec);	
		try {
					 mediaPlayer.stop();
					 mediaPlayer.release();
				     actMediaPlayer.stop();
				     actMediaPlayer.release();
		} catch (Exception e) {
			   e.fillInStackTrace();
		}
		
			
		 if(!isRandomOrder){
			if(temp >= 2){	
				flipper = (ViewFlipper) findViewById(R.id.flipper);
				temp = temp - 2 ;
				
				if(temp<0)
					temp=0;
				
		        if(temp != 0){
		        	btnPrevious.setVisibility(4);
		        	btnNext.setVisibility(4);
		        	
		        	
		        	try{
		        	String strsnd = soundName[temp];
				    strsnd = strsnd.replace(".mp3", "");
				    strsnd = strsnd.trim();
				    strsnd = strsnd.replaceAll(" ", "_");
				    final int idsnd = getResources().getIdentifier(strsnd , "raw", getPackageName());					                    
				    mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd);
				    
					if(isVoice) {
					  mediaPlayer.setVolume(100,100);
					} else {
					  mediaPlayer.setVolume(0,0);	
					}
		        	}catch(Exception e){
				        System.out.println("Errorrrrrrrrr in 935 String Array=>"+"temp"+temp+"ERROR=>"+e);
				     	}
		        	
		        	try{
					    int nxtid = intID[temp];  
		     	    	SQLiteQueryBuilder qbActtwo = new SQLiteQueryBuilder();
		     	    	qbActtwo.setTables("Alphabets");       
		     	    	SQLiteDatabase dbActtwo = myDbHelper.getReadableDatabase();
		     	    	Cursor curActtwo = qbActtwo.query(dbActtwo, null, "Id"+" = "+"'"+nxtid+"'" , null, null, null,null,"1");
		     	    	      
		     	    	if(curActtwo.moveToFirst()) {
		     	    		
		     	    		    String strsnd5; 
		     	    		    strsnd5 = curActtwo.getString(3);
		     	    			  strsnd5 = strsnd5.replace(".mp3", "");
		     			   		  strsnd5 = strsnd5.trim();
		     			   		  strsnd5 = strsnd5.replaceAll(" ", "_");
		     			   		  strsnd5 = strsnd5.replaceAll("-", "_");
		     			   		  
		     			   		  int idsnd5 = getResources().getIdentifier(strsnd5 , "raw", getPackageName());
		     	    	     
	  		    	       
		     			   		 int zero = 0; 
		     			   		 if(idsnd5 != zero) {
		     			   				
		     			   			actMediaPlayer = MediaPlayer.create(getBaseContext(), idsnd5);
		     			   		    actMediaPlayer.setVolume(100, 100);
		     			   		    mHandler.postDelayed(new Runnable() {
		     			   		    	
		     			   		    	public void run() {
			     			   		    	try {
			     			   		    	  actMediaPlayer.start();
			     			   		    }catch(Exception e) {
												  e.fillInStackTrace();
											} finally {
												//
											}    
		     			   		    	
		     			   		    		isActualSoundPlaying = true;
		     			   		    	}
		     			   		    	}, 700);	 
			   			   	        actMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
								    
								    public void onCompletion(MediaPlayer actMediaPlayer) {
									  try {
										   actMediaPlayer.release();
										   mediaPlayer.start();
										   isMediaPlayerActive = true;
									  }catch(Exception e) {
										   System.out.println("Errorrrrr>>>" + e);
									   } finally {
										  //
									   } 		 	       			 
								    }
							       });  			   			 
		     			   		 } else {
		     			   			mHandler.postDelayed(new Runnable() {
				   						   
				   						   public void run() {
				   							   try {  
				   								 mediaPlayer.start(); 
				   								isMediaPlayerActive = true;
				   							 }catch(Exception e) {
				   								 System.out.println("Errorrrrr>>>" + e);
											   } finally {
												 //
											   } 		 	       			 				    	
				   						   }
				   					}, 700);
		     			   		 }	 
		     	    	    }
		     	    	 
		     	    	//startManagingCursor(curActtwo);
		     	    	 curActtwo.close();
		     	    	 dbActtwo.close();	     
			           } catch (NullPointerException e) {
			        	   e.fillInStackTrace();
		     	          System.out.println("Error In 972 ShowCate.=>"+e);
					   }finally {
						  
					   } 
		     	    	
		     	      
		        } else {
		        	 
		        		String strsnd = soundName[0];
				        strsnd = strsnd.replace(".mp3", "");
				        strsnd = strsnd.trim();
				        strsnd = strsnd.replaceAll(" ", "_");
				        final int idsnd = getResources().getIdentifier(strsnd , "raw", getPackageName());					                    
				        try{
				        mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd);
						if(isVoice) {
							mediaPlayer.setVolume(100,100);
						} else {
							mediaPlayer.setVolume(0,0);
						}
				        }catch(Exception e){
				        	Log.i("Error","Error in 1079 String Array"+e);
		    		     }
				        int nxtid = intID[0];  
	     	    	   	SQLiteQueryBuilder qbActtwo = new SQLiteQueryBuilder();
	     	    	   	qbActtwo.setTables("Alphabets");       
	     	    	   	SQLiteDatabase dbActtwo = myDbHelper.getReadableDatabase();
	     	    	   	Cursor curActtwo = qbActtwo.query(dbActtwo, null, "Id"+" = "+"'"+nxtid+"'" , null, null, null,"Id ASC","1");
	     	           try{ 
	     	    	    if(curActtwo.moveToFirst()) {
	     		    		 String strsnd5 = curActtwo.getString(3);
	     		    		 strsnd5 = strsnd5.replace(".mp3", "");
	     			   		 strsnd5 = strsnd5.trim();
	     			   		 strsnd5 = strsnd5.replaceAll(" ", "_");
	     			   		 strsnd5 = strsnd5.replaceAll("-", "_");
	     			   		 int idsnd5 = getResources().getIdentifier(strsnd5 , "raw", getPackageName());
	     			   		
	     			   		 int zero = 0; 
	     			   		 if(idsnd5 != zero) {
	     			   			
	     			   			actMediaPlayer = MediaPlayer.create(getBaseContext(), idsnd5);
	     			   		    actMediaPlayer.setVolume(100, 100);
	     			   		    mHandler.postDelayed(new Runnable() {
	     			   		    	
	     			   		    	public void run() {
	     			   		    	try {
		     			   		    	  actMediaPlayer.start();
	     			   		    }catch(Exception e) {
											  e.fillInStackTrace();
										} finally {
											//
										}   
	     			   		    		isActualSoundPlaying = true;
	     			   		    	}
	     			   		    	}, 700);	 
		   			   	        actMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
							    
							    public void onCompletion(MediaPlayer actMediaPlayer) {
								  try {
									   actMediaPlayer.release();
									   mediaPlayer.start();
									   isMediaPlayerActive = true;
								  }catch(Exception e) {
									  Log.i("Error","Errorrrrr>>>" + e);
								   } finally {
									   //
								   } 		 	       			 
							    }
						       });  		  			   			 
	    			   		 } else {
	    			   			mHandler.postDelayed(new Runnable() {
			   						   
			   						   public void run() {
			   							 try {
			   								 mediaPlayer.start();
			   								isMediaPlayerActive = true;
			   							  }catch (Exception e) {
			   								 Log.i("Error","Errorrrrr>>>" + e);
										 } finally {
											   //
										 } 	
			   						   }
			   					    }, 700);
	    			   		 }	 
	     	    	    }
	     	    	   curActtwo.close();
		     	       dbActtwo.close();	     
	     	          }catch(Exception e){
	     	        	 Log.i("Error","Error in 1145 String Array"+e);
	    		       }
		        	btnPrevious.setVisibility(4);
		        	imageView.setVisibility(0);
		        }
		       	       
		        String strcat = imageName[temp];
		        strcat = strcat.trim(); 
		        strcat = strcat.toLowerCase();	
		        
		        imageView = (ImageView)findViewById(R.id.AImage);
				int test = getResources().getIdentifier(strcat, "drawable", getPackageName());
				imageView.setImageResource(test);
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		       
				 final TextView txtHeader4 = (TextView) findViewById(R.id.headerText); 
			   	   // 	///  add on 03/04/12
				 txtHeader4.setOnClickListener(new  View.OnClickListener() { 	 
					 public void onClick(View v) {
		    	    	   try {
								 mediaPlayer.stop();
								 mediaPlayer.release();
								 actMediaPlayer.stop();
							    actMediaPlayer.release();
		    	    	   }catch(Exception e) {
							 e.fillInStackTrace();
						   } finally {
							  // 
						   }
		    	    	   
		    	    	   //int tmp = temp - 1 ;
		    	    	   String strsnd1 = soundName[temp-1];
		    	    	   startMediaplayer(strsnd1);
		    	    	  
		    	       	  }
		    	   	   });
		        try{
		       
			        if(temp<=0){
			        	 
			        	 strName = titleName[0];
			        	 
			        }
			        else
			        	
			        	strName = titleName[temp];
		        }catch( Exception a){
		        	Log.i("Error","Errorrrrrrrrr in 635 String Array"+temp);
	        	}
	        	finally{
	        		Log.i("finaly","Erroe in 638 String Array ...FINALLY");
	        	}
				 	
		        mHandler.postDelayed(new Runnable() {
					
					public void run() {
						
						txtHeader.setText(strName); 
					}
				}, 100);	    
		        	
		        flipper.setInAnimation(inFromLeftAnimation());
		        flipper.setOutAnimation(outToRightAnimation());
		        flipper.showPrevious(); 
		        temp++; 
			   }	
			 }
	}
	
	 @Override
		public void onBackPressed() {
		 
		Intent i = new Intent(this, ShowAlphabet.class);
		startActivity(i); 
		finish();
	}

	
	
	@Override
	public void onDestroy() {
	        super.onDestroy();
	}
}