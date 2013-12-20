package com.learnalphabets;


import java.util.ArrayList;
import java.util.Random;
import com.learnalphabet.database.DataBaseHelper;
import com.learnalphabets.R.raw;
import com.learnalphabets.extras.BeanClass;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class QuestionCategory extends Activity  {
	 
		ArrayList<String> textHeader         = new ArrayList<String>(); 
		ArrayList<String> tempPrevArray      = new ArrayList<String>(); 
		ArrayList<String> InCorrtSoundArray = new ArrayList<String>();
		ArrayList<String> InCorrectSound    = new ArrayList<String>(); 
		ArrayList<String> CorrtSoundArray    = new ArrayList<String>();
		ArrayList<String> CorrectSound       = new ArrayList<String>(); 
		ArrayList<String> Result             = new ArrayList<String>(); 
		ArrayList<String> arraySound         = new ArrayList<String>(); 
		ArrayList<String> arrayImage         = new ArrayList<String>(); 
		ArrayList<String> arrayImage3        = new ArrayList<String>(); 
  
   ArrayList<Integer> ResultImage       = new ArrayList<Integer>();
   ArrayList<Integer> CorrectSndIndex   = new ArrayList<Integer>();
   ArrayList<Integer> arrayResult       = new ArrayList<Integer>(); 
   ArrayList<Integer> arrayFirst        = new ArrayList<Integer>(); 
   ArrayList<Integer> arrayResult3      = new ArrayList<Integer>();
   ArrayList<Integer> arrayResult1      = new ArrayList<Integer>();
   ArrayList<Integer> arrayResult2      = new ArrayList<Integer>();
   ArrayList<Integer> arrayResult4      = new ArrayList<Integer>();
   ArrayList<Integer> arrayRandomImage  = new ArrayList<Integer>(); 
   ArrayList<Integer> arraySetImage     = new ArrayList<Integer>(); 
   ArrayList<Integer> arrayID           = new ArrayList<Integer>();
   ArrayList<Integer> randomImage       = new ArrayList<Integer>();
   ArrayList<Integer> randomImage1      = new ArrayList<Integer>();
   
   ArrayList<String>  NextCategory= new ArrayList<String>();
   final ArrayList<String>  NextFlashCategory= new ArrayList<String>();
   
   private String fImage1  = "";
   private String fImage2  = "";
   private String fImage3  = "";
   private String fImage   = "";
   private String OImage1  = "";
   private String OImage2  = "";
   private String OImage3  = "";
   private String OImage   = "";
   private String Category = "";
   
   private String[] StringInCorrSound;
   private String[] StringCorrSound;    
   private String[] StringSound;
   private String[] StringArray;
   private String[] textHeaderArray;
   private String[] StringArray3;
   
     private int id1;
     private int fimage3;
     private int fimage2;
     private int fimage;
	 private int fimage1;
	 private int Oimage3;
	 private int Oimage2;
	 private int Oimage;
	 private int Oimage1;
     private int InCorrNum;
     private int CorrNum;
     private int itotalImage;
	 private int TotalImage;
	 public  int temp       = 1;
	 private int CorrsndLit = 0;
	 public  int index      = 1; 
	 private int position;	
	 private int musicLength=0;
	 private int viewScroll=0;
	
   private Boolean isVoice              = true;
		private Boolean isImageClick        = true;
		private Boolean isMediaPlayerActive = false;
		private Boolean istextSound         = true;
	
	
	private MediaPlayer mediaPlayer     = null;
	private MediaPlayer mediaPlayer1     = null;
	
	private MediaPlayer clickONPlayer   = null;
	
	private Handler mHandler            = new Handler();
	
	private TextView text               = null;
	
	private Button btnPrevious          = null;
	private Button btnNext              = null;
	
	private int fromFinish  	 = 0;
	
	private ImageButton image_1         = null;
	private ImageButton image_2         = null;
	private ImageButton image_3         = null;
	private ImageButton image_4         = null;
	private ImageButton cross_Image1    = null;  
	private ImageButton cross_Image2    = null;
	private ImageButton cross_Image3    = null;
	private ImageButton cross_Image4    = null; 
	private ImageButton right_Image1    = null;  
	private ImageButton right_Image2    = null;
	private ImageButton right_Image3    = null;
	private ImageButton right_Image4    = null;
	
	//private static int nexttextTemp;
	private ViewFlipper flipper         = null;
	
	private DataBaseHelper myDbHelper   = null;
	
	Random random                       = new Random();
	
	private Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
		Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
		Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}
	
	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
		  Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  -1.0f,
		  Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}
	

	public void onCreate(Bundle savedInstanceState)throws NullPointerException,OutOfMemoryError {
    super.onCreate(savedInstanceState);
    
    
   
    setContentView(R.layout.questioncategory);
    
    image_1      = (ImageButton) findViewById(R.id.image1);
    image_2      = (ImageButton) findViewById(R.id.image2);
    image_3      = (ImageButton) findViewById(R.id.image3);
    image_4      = (ImageButton) findViewById(R.id.image4);
    cross_Image1 = (ImageButton) findViewById(R.id.crossImage1);  
    cross_Image2 = (ImageButton) findViewById(R.id.crossImage2);
    cross_Image3 = (ImageButton) findViewById(R.id.crossImage3);
    cross_Image4 = (ImageButton) findViewById(R.id.crossImage4);
    
    right_Image1 = (ImageButton) findViewById(R.id.rightImage1);  
    right_Image2 = (ImageButton) findViewById(R.id.rightImage2);
    right_Image3 = (ImageButton) findViewById(R.id.rightImage3);
    right_Image4 = (ImageButton) findViewById(R.id.rightImage4);
   
    flipper      = (ViewFlipper) findViewById(R.id.flipper);
    
    
 /*   BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeResource(getResources(), R.id.image1, options);
      int imageHeight = options.outHeight;
      int imageWidth = options.outWidth;
      String imageType = options.outMimeType;*/
      
     // Toast.makeText(getApplicationContext(), imageHeight, Toast.LENGTH_LONG).show();
   
    
    cross_Image1.setAlpha(250);
    cross_Image2.setAlpha(250);
    cross_Image3.setAlpha(250);
    cross_Image4.setAlpha(250);
    
    right_Image1.setAlpha(250);
    right_Image2.setAlpha(250);
    right_Image3.setAlpha(250);
    right_Image4.setAlpha(250);
    
   
      
     /* Bundle bundle = this.getIntent().getExtras();
    Category      = bundle.getString("CategoryName");
    position      = bundle.getInt("Position");
    NextCategory  = bundle.getStringArrayList("NextCategoryName");
    fromFinish    =bundle.getInt("fromFinish");
    musicLength   = bundle.getInt("musicLength");
    viewScroll   = bundle.getInt("viewScroll");
    
   */
    
   
   /* try {
	 //fetching record from setting table.
	   SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	   qb.setTables("tbl_settings");
	   SQLiteDatabase db     = myDbHelper.getReadableDatabase();
	   db                    = myDbHelper.getReadableDatabase();
	   Cursor c              = qb.query(db,null,null,null,null, null, null );
	   if (c.moveToFirst()) {
	    	isVoice = c.getInt(1) == 0 ? false : true;
	   } else {
	    	Toast.makeText(this, "No Record found",Toast.LENGTH_LONG).show();        
	   }
	   c.close();
	   db.close();   
    } catch (Exception e) {
	   e.fillInStackTrace();
    } finally {
	   //
    }   
   */
    text  = (TextView) findViewById(R.id.headerText);
    final ImageButton btnSetting = (ImageButton) findViewById(R.id.setting_btn1);
    btnSetting.cancelLongPress();
    btnSetting.setOnClickListener(settingsListener);
  
    btnNext     = (Button) findViewById(R.id.btnnext);
    btnNext.setVisibility(4);
    btnPrevious = (Button) findViewById(R.id.btnprevious);
    btnPrevious.setVisibility(4);
    
    final ImageButton btnHome = (ImageButton) findViewById(R.id.home_btn);
    btnHome.cancelLongPress();
    btnHome.getAnimation();
    btnHome.setOnClickListener(homeListener);
       
    /**
     * Add correct Sound to Array List
     */
   	populatesoundArray();

   	
   	
   	
   	
   	System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
   	
   	
   	
    myDbHelper = new DataBaseHelper(this);
   	
    //Fetching the record from items
    SQLiteQueryBuilder qbOne = new SQLiteQueryBuilder();
    qbOne.setTables("Alphabets");
    SQLiteDatabase dbOne = myDbHelper.getReadableDatabase();
    Cursor cur           = qbOne.query(dbOne,null,null,null,null, null, "Id ASC" );
    itotalImage          = cur.getCount();
    String answer = String.valueOf(itotalImage);
  	 // Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_LONG).show();
    int ID;
    if(cur.moveToFirst()) {
  		do { 
  			 ID = cur.getInt(0);
  			 arrayID.add(ID);
	 	} while(cur.moveToNext());
    } 	    	 
    cur.close();
    dbOne.close();
    
    
    
    
  	System.out.println("llllllllllllllllllllllllllllllllllllllllllllllll");
    // Array result for 1 image
    arrayResult  = GenRandomNum(arrayID);  
    // Array result for 3 image
    arrayResult3 = GenRandomNum(arrayID);
    	    
    for(int i = 0;i < arrayResult.size();i++) {
         id1 = arrayResult.get(i);
        
         SQLiteQueryBuilder qbOne1 = new SQLiteQueryBuilder();
           qbOne1.setTables("Alphabets");       
           SQLiteDatabase dbOne1     = myDbHelper.getReadableDatabase();
           Cursor resultcur          = qbOne1.query(dbOne1, null, "Id"+" = "+"'"+id1+"'" , null, null, null,null);
          
	     if(resultcur.moveToFirst()) {	    	 
	      	 arrayImage.add(resultcur.getString(2));
	    	   arraySound.add(resultcur.getString(3));         
	         textHeader.add(resultcur.getString(2));
	     }
	     StringArray     = (String[]) arrayImage.toArray(new String[arrayImage.size()]);
	     StringSound     = (String[]) arraySound.toArray(new String[arraySound.size()]);
	     textHeaderArray = (String[]) textHeader.toArray(new String[textHeader.size()]);
	     resultcur.close();
	     dbOne1.close();     
	}
    
	//////////////Stored 3 images////////
 	 	 
	for(int i = 0;i < arrayResult3.size();i++) {
         id1 = arrayResult3.get(i);
         SQLiteQueryBuilder qbOne2 = new SQLiteQueryBuilder();
           qbOne2.setTables("Alphabets");       
           SQLiteDatabase dbOne2     = myDbHelper.getReadableDatabase();
	     Cursor resultcur3         = qbOne2.query(dbOne2,null,"Id"+" = "+"'"+id1+"'",null,null,null,null);
	     TotalImage                = resultcur3.getCount();
	     if(resultcur3.moveToFirst()) {
	    	 arrayImage3.add(resultcur3.getString(2)); 
	    	 StringArray3 = (String[]) arrayImage3.toArray(new String[arrayImage3.size()]); 
	     }
	     resultcur3.close();
	     dbOne2.close();
    }// For End 
 	
   
	/////////////////Display 1 image//////////
	
 	temp   = 1;
	fImage = StringArray[0];
	fImage = fImage.trim(); 
	fImage = fImage.toLowerCase();
   	fimage = getResources().getIdentifier(fImage , "drawable", getPackageName());
   	  
   	
   	/////////////////Display 3 images//////////
   	
   	Result.clear();
   	ResultImage.clear();
   	int count = 0;
   	int n     = arrayResult.get(0);
   		 
   	    for(int i = 0;i < 4;i++){
   	    	 if(count > 3) {
      	    	  break; 
   	    	 }else {
   	    		String Temp = StringArray3[i];
   	    		int n1      = arrayResult3.get(i);
   	    		if((n1 != n)&&(ResultImage.indexOf(n) == (-1)) && (Result.indexOf(fImage) == (-1))) {
   	    		    ResultImage.add(n1);
   	    		    Result.add(Temp);
   	    		    count++;
   			    } 
   	        } 
   	    }
   	
   	ResultImage.clear();
   	fImage1 = Result.get(0);
	fImage1 = fImage1.trim(); 
	fImage1 = fImage1.toLowerCase();
  	fimage1 = getResources().getIdentifier(fImage1 , "drawable", getPackageName());
    
  	fImage2 = Result.get(1);
	fImage2 = fImage2.trim(); 
	fImage2 = fImage2.toLowerCase();
   	fimage2 = getResources().getIdentifier(fImage2 , "drawable", getPackageName());
   	 
   	fImage3 = Result.get(2);
	fImage3 = fImage3.trim(); 
	fImage3 = fImage3.toLowerCase();
  	fimage3 = getResources().getIdentifier(fImage3 , "drawable", getPackageName());
   	
  	Result.clear();
  	randomImage  = GenRandomImage(4);
  	randomImage1 = GenRandomImage(11);
  	CorrNum      = randomImage1.get(0);
  	int Num      = randomImage.get(0);
  	InCorrNum    = Num;
  	Log.i("CallOnRight", "********** Num   : "+Num);
  	Log.i("CallOnRight", "********** InCorrNum   : "+InCorrNum);
    switch(Num) {
         case 0:   
        	 Log.i("CallOnRight", "********** case 0 strtdddd ");
        	 image_1.setImageResource(fimage);	
        	 mHandler.postDelayed(new Runnable() {
					public void run() {
						/// text.setText(textHeaderArray[0]);
					}
				}, 100);
	 		 
        	 ///////////// Clickon Sound//////////////////
     		 String sndclickon     = Integer.toString(R.raw.clickon);
     		 sndclickon            = sndclickon.replace(".mp3", "");
     		 sndclickon            = sndclickon.trim();
     		 sndclickon            = sndclickon.replaceAll(" ", "_");
    	     final int sndclickon0 = getResources().getIdentifier(sndclickon , "raw", getPackageName());
    	     clickONPlayer         = MediaPlayer.create(getBaseContext(), sndclickon0);
    	     clickONPlayer.setVolume(100, 100);
	   	     
	   	     /////////////////image Sound////////////////////////
		 	 String strsnd1   = StringSound[0];
		 	 strsnd1          = strsnd1.replace(".mp3", "");
	 	     strsnd1          = strsnd1.trim();
	 	     strsnd1          = strsnd1.replaceAll(" ", "_");
	 	     strsnd1          = strsnd1.toLowerCase();
	 	     final int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
	 	     mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd1);
	 	     mediaPlayer.setVolume(100, 100);
	 	     
    	     try { 	 
    	            mHandler.postDelayed(new Runnable() {
	    		    	public void run() {
	    		    		try {
	    		    			clickONPlayer.start();	
		    	    	     } catch (Exception e) {
							    e.fillInStackTrace();
						     } finally {
							  // 
						     }
	    		    		
	    		    	    isMediaPlayerActive = true;
	    		    	    clickONPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
	    						public void onCompletion(MediaPlayer actMediaPlayer) {
	    							 try {	
	    								 clickONPlayer.reset();	
	    								 mediaPlayer.start();
	    								 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
											public void onCompletion(MediaPlayer mp) {
												mediaPlayer.reset();		
											}
										 });
	    								 isMediaPlayerActive = true;
	    							 } catch (Exception e) {
	    								 Log.i("Error","Errorrrrr>>>" + e);
	    							 } finally {
	    								// 
	    							 } 		 	       			 
	    						  }
	    					  });
	    		    	     }
    		           }, 1000);
    	    
    	    } catch(Exception e) {
    	    	Log.i("Error","Exception"+e);
    	    } finally {
    	    	//
    	    }
    	     
    	    
    	     image_2.setImageResource(fimage1);
    	     image_3.setImageResource(fimage2);
    	     image_4.setImageResource(fimage3);
	 		 
	 		 
	 		//Image 1 Click Listener
	         image_1.setOnClickListener(new  View.OnClickListener() {
		     public void onClick(View v) {
		    	
		    	 if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
		         //////////Correct image Sound////////////
			     while(CorrsndLit < 1 ) {
			    	
			    		
			    	 right_Image1.setVisibility(0);
			    	 
			    	 //correctSound();
			    	 
			    	 isImageClick = false; 
			    	 try {
			    		 
			    		// Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_LONG).show();
			    		 
				    	 String corrsnd1 = StringCorrSound[CorrNum];
				    	 corrsnd1        = corrsnd1.replace(".mp3", "");
				    	 corrsnd1        = corrsnd1.trim();
				    	 corrsnd1        = corrsnd1.replaceAll(" ", "_");
				    	 int corrsnd     = getResources().getIdentifier(corrsnd1 , "raw", getPackageName());
				    	  
 	 		    		 mediaPlayer     = MediaPlayer.create(getBaseContext(), corrsnd);
 		 	    		 mediaPlayer.setVolume(100, 100);
 		 	    		 mediaPlayer.start();	
 		 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
							public void onCompletion(MediaPlayer mp) {
								mediaPlayer.reset();		
							}
							
						});
				    	 CorrsndLit++;

			    	 } catch(Exception e) {
			    		  //
			    	 }
			    	 }
			     } 			    
			       
			     	
		    	    cross_Image2.setVisibility(0);
			     	  cross_Image3.setVisibility(0);
			     	  cross_Image4.setVisibility(0);
			     	
			     	waitForOpen();
			     	
			     	 		     	
		       }
		     });
	   
	         if(isImageClick == false) {
			    	text.setEnabled(false);  
			    	 
			    }
	         else{ 
	             text.setOnClickListener(new  View.OnClickListener() {        
			    	 public void onClick(View v) {
			    		 try {
			    			if (isMediaPlayerActive) {
	 				    		clickONPlayer.stop();
	 				    		clickONPlayer.reset();
	 				    		mediaPlayer.stop();
	 				    		mediaPlayer.reset();
 				    		 }	 
			    		 } catch (Exception e) {
							e.fillInStackTrace();
						 } finally {
							// 
						 }
			     		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
	 		    		mediaPlayer.setVolume(100, 100);
 		 	    	 	mediaPlayer.start();
 	    	         }
		 		 });
		       }
	        
	        //Image 2 Click Listener
	        image_2.setOnClickListener(new  View.OnClickListener() {
	  			public void onClick(View v) {
	  				if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
	  					
	  					cross_Image2.setVisibility(0);
	  					
	  					//rongSound();
	  				 
	  				 
	  			     String strsnd1  = StringInCorrSound[InCorrNum];
				 	 strsnd1         = strsnd1.replace(".mp3", "");
				 	 strsnd1         = strsnd1.trim();
				 	 strsnd1         = strsnd1.replaceAll(" ", "_");
				 	 int idsnd1      = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
				 	try{
 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
 	 		    		 mediaPlayer.reset();		
			 	    	 mediaPlayer.setVolume(100, 100);
			 	    	 mediaPlayer.start();		
		  			    
		  			    // image_2.setVisibility(4);
				 	 }catch(Exception e) {
				 		Log.i("Error","Error in 584 String Array"+e);
	    		      }
				 	
	  				}
	  			}
	  			
	   		});
	 		    
	        //Image 3 Click Listener 
	   		image_3.setOnClickListener(new  View.OnClickListener() {
  			    public void onClick(View v) {
  			    	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
  			    	
  			    	cross_Image3.setVisibility(0); 
  			    	//rongSound();
  			        String strsnd1  = StringInCorrSound[InCorrNum+1];
			 	    strsnd1         = strsnd1.replace(".mp3", "");
			 	    strsnd1         = strsnd1.trim();
			 	    strsnd1         = strsnd1.replaceAll(" ", "_");
			 	    int idsnd1      = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			 	    try{
	 		    		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
	 		    		mediaPlayer.setVolume(100, 100);
		 	    		mediaPlayer.start();		
	  			        
	  			       // image_3.setVisibility(4); 
			 	    }catch(Exception e){
			 		 Log.i("Error","Error in 604 String Array"+e);
    		        }
  			    	}
  			      }
	   		});
	 		     
	   	   //Image 4 Click Listener 
	   		image_4.setOnClickListener(new  View.OnClickListener() {
  			     public void onClick(View v) {
  			    	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
  			   
  			    cross_Image4.setVisibility(0); 
  				//rongSound();
  			      	String strsnd1   = StringInCorrSound[InCorrNum+2];
			 	   	strsnd1          = strsnd1.replace(".mp3", "");
			 	   	strsnd1          = strsnd1.trim();
			 	   	strsnd1          = strsnd1.replaceAll(" ", "_");
			 	  	int idsnd1       = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			 	  	try{
	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
	 		    		 mediaPlayer.setVolume(100, 100);
		 	    		 mediaPlayer.start();		   
				 	     
				 	    // image_4.setVisibility(4); 
			 	   }catch(Exception e){
			 		  Log.i("Error","Error in 624 String Array"+e);
    		       }
  			    	}
  			      }
	   		 });
	   		 break;
         case 1:
        	 
        	 image_1.setImageResource(fimage1);
        	 image_2.setImageResource(fimage);
        	 mHandler.postDelayed(new Runnable() {
				public void run() {
					// text.setText(textHeaderArray[0]);
				}
			 }, 100);
               ///////////// Clickon Sound////////////////// 
     		String sndclickon1     = Integer.toString(R.raw.clickon);
     		sndclickon1            = sndclickon1.replace(".mp3", "");
     		sndclickon1            = sndclickon1.trim();
     		sndclickon1            = sndclickon1.replaceAll(" ", "_");
    	    final int sndclickon11 = getResources().getIdentifier(sndclickon1 , "raw", getPackageName());
    	    clickONPlayer          = MediaPlayer.create(getBaseContext(), sndclickon11);
    	    clickONPlayer.setVolume(100, 100);
    	    
    	    /////////////////image Sound////////////////////////
        	  
        	String strsnd2   = StringSound[0];
        	strsnd2          = strsnd2.replace(".mp3", "");
	 	    strsnd2          = strsnd2.trim();
	 	    strsnd2          = strsnd2.replaceAll(" ", "_");
	 	    final int idsnd2 = getResources().getIdentifier(strsnd2 , "raw", getPackageName());
	 	    mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd2); 
 	    	mediaPlayer.setVolume(100, 100);
	 	    
    	    try { 	 
    	           mHandler.postDelayed(new Runnable() {
    	        	   public void run() {    	
	    		     			    		     		
	    		     		try {
	    		    			clickONPlayer.start();	
		    	    	     } catch (Exception e) {
							    e.fillInStackTrace();
						     } finally {
							  // 
						     }
	    		   	        clickONPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
	    						
	    						public void onCompletion(MediaPlayer actMediaPlayer) {
	    							 try {
	    								 clickONPlayer.reset();	
	    								 mediaPlayer.start();
	    								 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
											public void onCompletion(MediaPlayer mp) {
												mediaPlayer.reset();		
											}
										});
	    								 isMediaPlayerActive = true;
	    							 } catch (Exception e) {
	    								 Log.i("Error","Errorrrrr>>>" + e);
	    							 } finally {
	    								// 
	    							 } 		 	       			 
	    						}
	    					});
	    		    	}
    		     }, 1000);
    	    
    	    } catch(Exception e) {
    	    	Log.i("Error","Exception"+e);
    	    }
	 	    
 		     image_3.setImageResource(fimage2);
 		     image_4.setImageResource(fimage3);
 		   
 		    /////////////////Correct image Sound////////////////////////
 		     
 		     if(image_2.isClickable()) {
	 		    image_2.setOnClickListener(new  View.OnClickListener() {
		          public void onClick(View v) {
		        	  if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
		             isImageClick = false;
		             text.setEnabled(false);  
		        	 while(CorrsndLit<1){ 
		        		
		        		 // correctSound();
		        		   right_Image2.setVisibility(0);
						  
					 	   String corrsnd1 = StringCorrSound[CorrNum];
					 	   corrsnd1        = corrsnd1.replace(".mp3", "");
					 	   corrsnd1        = corrsnd1.trim();
					 	   corrsnd1        = corrsnd1.replaceAll(" ", "_");
					 	   int corrsnd     = getResources().getIdentifier(corrsnd1 , "raw", getPackageName());
					 	   try{ 
			 		    		 mediaPlayer   = MediaPlayer.create(getBaseContext(), corrsnd);
			 		    		 mediaPlayer.setVolume(100, 100);
				 	    		 mediaPlayer.start();
				 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
									}
								});
				 	    		isMediaPlayerActive = true;
						 	    CorrsndLit++;
					 	  }catch(Exception e) {
					 		 Log.i("Error","Error in 727 String Array"+e);
		    		      }
					 	
					 } 
		        	   cross_Image1.setVisibility(0);
			           cross_Image3.setVisibility(0);
			           cross_Image4.setVisibility(0);
			           waitForOpen();
		          }
		          }
		        });
	 	     }
 		        
 		    if(isImageClick == false){
		    	text.setEnabled(false);  
		    	 
		    }      
 		    else { 
	             text.setOnClickListener(new  View.OnClickListener() {        
			    	 public void onClick(View v) {
			    		 try {
			    			if (isMediaPlayerActive) {
	 				    		clickONPlayer.stop();
	 				    		clickONPlayer.reset();
	 				    		mediaPlayer.stop();
	 				    		mediaPlayer.reset();
 				    		 }	 
			    		 } catch (Exception e) {
							e.fillInStackTrace();
						 } finally {
							// 
						 }
			     		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd2);
	 		    		mediaPlayer.setVolume(100, 100);
 		 	    	 	mediaPlayer.start();
 	    	         }
		 		});
		     }
    
	 	if(image_1.isClickable()) {
	   		        image_1.setOnClickListener(new  View.OnClickListener() {
	  			        public void onClick(View v) {
	  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
	  			        cross_Image1.setVisibility(0);
	  			        
	  			      String strsnd1  = StringInCorrSound[1];
				 	     	strsnd1         = strsnd1.replace(".mp3", "");
				 	     	strsnd1         = strsnd1.trim();
				 	     	strsnd1         = strsnd1.replaceAll(" ", "_");
				 	    	int idsnd1      = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
				 	    	try{
			 		    		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
		 		 	    		mediaPlayer.setVolume(100, 100);
				 	    		mediaPlayer.start();
				 	    		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
									}
								});
				 	    		isMediaPlayerActive = true;
					 	      
					 	    //   image_1.setVisibility(4); 
				 	    	 }catch(Exception e){
				 	    		 Log.i("Error","Error in 822 String Array"+e);
			    		      }
	  			        }
	  			        }
	   		      });
	 	}
	 		
	 	if(image_3.isClickable()) {
	   		       image_3.setOnClickListener(new  View.OnClickListener() {
	  			        public void onClick(View v) {
	  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
	  			        		cross_Image3.setVisibility(0); 
	  			            String strsnd1  = StringInCorrSound[2];
				 	     	strsnd1         = strsnd1.replace(".mp3", "");
				 	     	strsnd1         = strsnd1.trim();
				 	     	strsnd1         = strsnd1.replaceAll(" ", "_");
				 	    	int idsnd1      = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
				 	    	try{
			 		    		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
		 		 	    		mediaPlayer.setVolume(100, 100);
				 	    		mediaPlayer.start();
				 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
									}
								});
				 	    		isMediaPlayerActive = true;
					 	        
					 	      // image_3.setVisibility(4); 
				 	    	 }catch(Exception e){
				 	    		Log.i("Error","Error in 850 String Array"+e);
			    		      }
	  			       } 
	  			        }
	   		       });
	 		} 
	 		if(image_4.isClickable()) {
	   		        image_4.setOnClickListener(new  View.OnClickListener() {
  			        public void onClick(View v) {
  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
  			        		cross_Image4.setVisibility(0);
				 	    	//image_4.setVisibility(4); 
  			        	String strsnd1   = StringInCorrSound[0];
			 	     	strsnd1          = strsnd1.replace(".mp3", "");
			 	     	strsnd1          = strsnd1.trim();
			 	     	strsnd1          = strsnd1.replaceAll(" ", "_");
			 	    	int idsnd1       = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			 	    	try{
		 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
	 		 	    		 mediaPlayer.setVolume(100, 100);
			 	    		 mediaPlayer.start();
			 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
								public void onCompletion(MediaPlayer mp) {
									mediaPlayer.reset();		
								}
							});
			 	    		isMediaPlayerActive = true;
				 	    	
			 	    	 }catch(Exception e){
			 	    		Log.i("Error","Error in 848 String Array"+e);
		    		     }
  			      }
  			        }
	   	        });
	 		}   
 		    break;
	 		 
         case 2:
        	
        	image_1.setImageResource(fimage1);
 		    image_2.setImageResource(fimage2);
 		    image_3.setImageResource(fimage);
 		    mHandler.postDelayed(new Runnable() {
				
				public void run() {
					// text.setText(textHeaderArray[0]);
				}
			}, 100);
             ///////////// Clickon Sound//////////////////
     		 
     		 String sndclickon2     = Integer.toString(R.raw.clickon);
     		 sndclickon2            = sndclickon2.replace(".mp3", "");
     		 sndclickon2            = sndclickon2.trim();
     		 sndclickon2            = sndclickon2.replaceAll(" ", "_");
    	     final int sndclickon20 = getResources().getIdentifier(sndclickon2 , "raw", getPackageName());
    	     clickONPlayer          = MediaPlayer.create(getBaseContext(), sndclickon20);
   	         clickONPlayer.setVolume(100, 100);
    	     
    	     /////////////////  image Sound//////////////////////// 
        	 String strsnd3   = StringSound[0];
	 	     strsnd3          = strsnd3.replace(".mp3", "");
	 	     strsnd3          = strsnd3.trim();
	 	     strsnd3          = strsnd3.replaceAll(" ", "_");
	 	     final int idsnd3 = getResources().getIdentifier(strsnd3 , "raw", getPackageName());
	 	     mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd3);
	 	     mediaPlayer.setVolume(100, 100);
	 	    
    	     try { 	 
    	            mHandler.postDelayed(new Runnable() {
	    		    	public void run() {    			
	    		    		try {
	    		    			clickONPlayer.start();	
		    	    	     } catch (Exception e) {
							    e.fillInStackTrace();
						     } finally {
							  // 
						     }     
	    		   	        clickONPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
	    						
	    						public void onCompletion(MediaPlayer actMediaPlayer) {
	    							 try {
	    								 clickONPlayer.reset();	
	    								 mediaPlayer.start();
	    								 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
											public void onCompletion(MediaPlayer mp) {
												mediaPlayer.reset();		
											}
										});
	    								isMediaPlayerActive = true;
	    							 } catch (Exception e) {
	    								 Log.i("Error","Errorrrrr>>>" + e);
	    							 } finally {
	    								// 
	    							 } 		 	       			 
	    						}
	    					});
	    		    	}
    		      }, 1000);
    	    
    	    } catch(Exception e) {
    	    	Log.i("Error","Exception"+e);
    	    }
        	 image_4.setImageResource(fimage3);
 		     
 		    /////////////////Correct image Sound////////////////////////   
 		    if(image_3.isClickable()) {
	 		   image_3.setOnClickListener(new  View.OnClickListener() {
	 			   
			       public void onClick(View v) {
			    	   if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			    	   isImageClick = false;
			    	   istextSound  = false;
			    	   text.setEnabled(false);  
			        	 while(CorrsndLit<1){
			        		
			        		 //correctSound();
			        		  right_Image3.setVisibility(0);
						 	  String corrsnd1 = StringCorrSound[CorrNum];
						 	  corrsnd1        = corrsnd1.replace(".mp3", "");
						 	  corrsnd1        = corrsnd1.trim();
						 	  corrsnd1        = corrsnd1.replaceAll(" ", "_");
						 	  int corrsnd     = getResources().getIdentifier(corrsnd1 , "raw", getPackageName());
		 	 		    	try{
							 	    mediaPlayer = MediaPlayer.create(getBaseContext(), corrsnd);
			 		 	        mediaPlayer.setVolume(100, 100);
			 		 	    	  mediaPlayer.start();
			 		 	    	  mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
									}
								  });
			 		 	    	  isMediaPlayerActive = true;
							 	  CorrsndLit++;
		 	 		    	 }catch(Exception e){
		 	 		    		Log.i("Error","Error in 985 String Array"+e);
			    		     }
		 	 		    	
						 } 
			        	
						cross_Image1.setVisibility(0);
						cross_Image2.setVisibility(0);
						cross_Image4.setVisibility(0);
						waitForOpen();
			       }
			       }
		     });
	 	   }
	        
 	
             text.setOnClickListener(new  View.OnClickListener() {        
		    	 public void onClick(View v) {
		    		 try {
		    			if (isMediaPlayerActive) {
 				    		clickONPlayer.stop();
 				    		clickONPlayer.reset();
 				    		mediaPlayer.stop();
 				    		mediaPlayer.reset();
			    		 }	 
		    		 } catch (Exception e) {
						e.fillInStackTrace();
					 } finally {
						// 
					 }
		     		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd3);
 		    		mediaPlayer.setVolume(100, 100);
		 	    	 	mediaPlayer.start();
    	          }
	 		});
	       if(image_1.isClickable()) {
	   		        image_1.setOnClickListener(new  View.OnClickListener() {
	  			        public void onClick(View v) {
	  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
	  			      	
	  			      cross_Image1.setVisibility(0); 
	  			    //rongSound();
			 	      // image_1.setVisibility(4); 
	  			        	String strsnd1   = StringInCorrSound[InCorrNum];
				 	     	strsnd1          = strsnd1.replace(".mp3", "");
				 	     	strsnd1          = strsnd1.trim();
				 	     	strsnd1          = strsnd1.replaceAll(" ", "_");
				 	    	int idsnd1       = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
				 	    	try{
		 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
		 		 	    		 mediaPlayer.setVolume(100, 100);
		 		 	    	   mediaPlayer.start();
		 		 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									 public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
									}
								});
		 		 	    		isMediaPlayerActive = true;
					 	        
				 	    	 }catch(Exception e) {
				 	    		Log.i("Error","Error in 1145 String Array"+e);
			    		      }
	  			        	}
	  			        }
	   		        });
	 	    } 
	 		if(image_2.isClickable()) {
	   		        image_2.setOnClickListener(new  View.OnClickListener() {
  			        public void onClick(View v) {
  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
  			      	
  			      		cross_Image2.setVisibility(0);
  			      	//rongSound();
  			      		//image_2.setVisibility(4);
  			        	String strsnd1   = StringInCorrSound[InCorrNum-1];
			 	     	strsnd1          = strsnd1.replace(".mp3", "");
			 	     	strsnd1          = strsnd1.trim();
			 	     	strsnd1          = strsnd1.replaceAll(" ", "_");
			 	    	int idsnd1       = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			 	    	try{
	 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
	 		 	    		 mediaPlayer.setVolume(100, 100);
	 		 	    		 mediaPlayer.start();
	 		 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
								public void onCompletion(MediaPlayer mp) {
									mediaPlayer.reset();		
								}
							});
	 		 	    		 isMediaPlayerActive = true;
				 	    	  
			 	    	 }catch(Exception e){
			 	    		Log.i("Error","Error in 1102 String Array"+e);
		    		     }
  			        	}
  			        }
	   		   });
	 		 } 
	 		 if(image_4.isClickable()) {
	   		        image_4.setOnClickListener(new  View.OnClickListener() {
	  			        public void onClick(View v) {
	  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
	  			      	
	  			      	cross_Image4.setVisibility(0);
	  			      //rongSound();
			 	    	//image_4.setVisibility(4); 
	  			        	String strsnd1   = StringInCorrSound[InCorrNum+1];
				 	     	strsnd1          = strsnd1.replace(".mp3", "");
				 	     	strsnd1          = strsnd1.trim();
				 	     	strsnd1          = strsnd1.replaceAll(" ", "_");
				 	    	int idsnd1       = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
				 	    	try{
		 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
		 	 		    		 mediaPlayer.setVolume(100, 100);
		 		 	    		 mediaPlayer.start();
		 		 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
									}
								 });
		 		 	    		isMediaPlayerActive = true;
					 	    	
				 	    	 }catch(Exception e){
				 	    		Log.i("Error","Error in 1131 String Array"+e);
			    		     }
	  			        	}
	  			      }
	   		   });
	 		 }   
 		     break;
         case 3:
         image_1.setImageResource(fimage1);
 		     image_2.setImageResource(fimage2);
 		     image_3.setImageResource(fimage3);
 		     image_4.setImageResource(fimage);
 		     mHandler.postDelayed(new Runnable() {
				public void run() {
					 //text.setText(textHeaderArray[0]);
				}
			}, 100);
               ///////////// Clickon Sound//////////////////
     		 
     		 String sndclickon3     = Integer.toString(R.raw.clickon);
     		 sndclickon3            = sndclickon3.replace(".mp3", "");
     		 sndclickon3            = sndclickon3.trim();
     		 sndclickon3            = sndclickon3.replaceAll(" ", "_");
    	     final int sndclickon30 = getResources().getIdentifier(sndclickon3 , "raw", getPackageName());
    	     clickONPlayer          = MediaPlayer.create(getBaseContext(), sndclickon30);
   	         clickONPlayer.setVolume(100, 100);
    	     ///////////////// image Sound////////////////////////	 
        	 String strsnd4         = StringSound[0];
	 	     strsnd4                = strsnd4.replace(".mp3", "");
	 	     strsnd4                = strsnd4.trim();
	 	     strsnd4                = strsnd4.replaceAll(" ", "_");
	 	     final int idsnd4       = getResources().getIdentifier(strsnd4 , "raw", getPackageName());	
	 	     mediaPlayer            = MediaPlayer.create(getBaseContext(), idsnd4);
	   	     mediaPlayer.setVolume(100, 100);
    	     try { 	 
    	            mHandler.postDelayed(new Runnable() {
    	            	public void run() {    			
	    		    		try {
	    		    			clickONPlayer.start();	
		    	    	     } catch (Exception e) {
							    e.fillInStackTrace();
						     } finally {
							  // 
						     }       
	    		    		clickONPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
	    						public void onCompletion(MediaPlayer actMediaPlayer) {
	    							 try {
	    								 clickONPlayer.reset(); 								
	    								 mediaPlayer.start();
	    								 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
											public void onCompletion(MediaPlayer mp) {
													mediaPlayer.reset();		
											 }
										  });
	    								 isMediaPlayerActive = true;
	    							 } catch (Exception e) {
	    								 Log.i("Error","Errorrrrr>>>" + e);
	    							 } finally {
	    								// 
	    							 } 		 	       			 
	    						}
	    					});
	    		    	}
    		     }, 1000);
    	    
    	    } catch(Exception e) {
    	    	Log.i("Error","Exception"+e);
    	    }
    
    	    ///////////////Correct image Sound////////////////////////	 
	     	if(image_4.isClickable()) {
		     		image_4.setOnClickListener(new  View.OnClickListener() {
		   			public void onClick(View v) {
		   				if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
		   				isImageClick = false;
		   			 text.setEnabled(false);  
				     while(CorrsndLit<1){ 
				    	
				    	 // correctSound();
				    	  right_Image4.setVisibility(0);
			 	        String corrsnd1  = StringCorrSound[CorrNum];
			 	        corrsnd1         = corrsnd1.replace(".mp3", "");
			 	        corrsnd1         = corrsnd1.trim();
			 	        corrsnd1         = corrsnd1.replaceAll(" ", "_");
			 	        int corrsnd      = getResources().getIdentifier(corrsnd1 , "raw", getPackageName());
			 	        try{
		 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), corrsnd);
	 		 	    		 mediaPlayer.setVolume(100, 100);
			 	    		 mediaPlayer.start();
			 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
								public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
								 }
							});
			 	    		isMediaPlayerActive = true;
			 	    		CorrsndLit++;
			 	       }catch(Exception e){
			 	    	 Log.i("Error","Error in 1231 String Array"+e);
	    		       }
			 	     
				      }	    	
				      
					     cross_Image1.setVisibility(0);
				       cross_Image3.setVisibility(0);
				       cross_Image2.setVisibility(0);
				       waitForOpen();  
				     }
			         }
			      });
		 	 }
	    
	     	if(isImageClick) { 
	             text.setOnClickListener(new  View.OnClickListener() {        
			    	 public void onClick(View v) {
			    		 try {
			    			if (isMediaPlayerActive) {
	 				    		clickONPlayer.stop();
	 				    		clickONPlayer.reset();
	 				    		 mediaPlayer.stop();
	 				    		 mediaPlayer.reset();
 				    		 }	 
			    		 } catch (Exception e) {
							e.fillInStackTrace();
						 } finally {
							// 
						 }
			     		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd4);
	 		    		mediaPlayer.setVolume(100, 100);
 		 	    	 	mediaPlayer.start();
 	    	           }
		 		 });
		     }
	         
		 	 if(image_1.isClickable()) {
		   		        image_1.setOnClickListener(new  View.OnClickListener() {
		  			        public void onClick(View v) {
		  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
		  			        	
		  			        	 cross_Image1.setVisibility(0); 
		  			        	//rongSound();
		  			        	String strsnd1   = StringInCorrSound[InCorrNum-1];
					 	     	strsnd1          = strsnd1.replace(".mp3", "");
					 	     	strsnd1          = strsnd1.trim();
					 	     	strsnd1          = strsnd1.replaceAll(" ", "_");
					 	    	int idsnd1       = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
					 	    	try{
				 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 		 	    		 mediaPlayer.setVolume(100, 100);
					 	    		 mediaPlayer.start();
					 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
											public void onCompletion(MediaPlayer mp) {
												mediaPlayer.reset();		
											}
										});
					 	    		 isMediaPlayerActive = true;
						 	    	 image_1.setAlpha(100);
			  				        
			  				         //image_1.setVisibility(4); 
					 	    	 }catch(Exception e){
					 	    		Log.i("Error","Error in 1322 String Array"+e);
				    		      }
		  			        	}
		  			         }
		   		      });
		 	 } 
		 	 if(image_3.isClickable()) {
		   		        image_3.setOnClickListener(new  View.OnClickListener() {
		  			        public void onClick(View v) {
		  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
		  			      
		  			       cross_Image3.setVisibility(0);
		  			 //	rongSound();
	  				      //image_3.setVisibility(4); 
		  			        	String strsnd1 = StringInCorrSound[InCorrNum];
					 	     	strsnd1        = strsnd1.replace(".mp3", "");
					 	     	strsnd1        = strsnd1.trim();
					 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
					 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
					 	    	try{
				 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
				 		    		 mediaPlayer.setVolume(100, 100);
					 	    		 mediaPlayer.start();
					 	    		 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
											public void onCompletion(MediaPlayer mp) {
												mediaPlayer.reset();		
											}
										});
					 	    		isMediaPlayerActive = true;
			  				       
					 	    	 }catch(Exception e){
					 	    		Log.i("Error","Error in 1348 String Array"+e);
				    		      }
		  			        	}
		  			        }
		   	      });
		 	  } 
		 	    if(image_2.isClickable()) {
		   		        image_2.setOnClickListener(new  View.OnClickListener() {
	  			        public void onClick(View v) {
	  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
	  			        	
	  			        	cross_Image2.setVisibility(0);
	  			        	//rongSound();
	  			        	String strsnd1  = StringInCorrSound[InCorrNum-2];
				 	     	strsnd1         = strsnd1.replace(".mp3", "");
				 	     	strsnd1         = strsnd1.trim();
				 	     	strsnd1         = strsnd1.replaceAll(" ", "_");
				 	    	int idsnd1      = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
				 	    	try{
			 		    		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 		    		mediaPlayer.setVolume(100, 100);
				 	    		mediaPlayer.start();
				 	    		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
									}
								});
				 	    		isMediaPlayerActive = true;
					 	    	
				 	    	 }catch(Exception e){
				 	    		Log.i("Error","Error in 1378 String Array"+e);
			    		     }
	  			        	}
	  			        }
		   	       });
		 	 }   
 		     break;
        }
	    	right_Image1.setVisibility(4); 
	    	right_Image2.setVisibility(4); 
	    	right_Image3.setVisibility(4);
	    	right_Image4.setVisibility(4);
	     	cross_Image1.setVisibility(4); 
 	  	 	cross_Image2.setVisibility(4); 
 	  	 	cross_Image3.setVisibility(4);
 	  	 	cross_Image4.setVisibility(4);
 	  	 	cur.close();	
 	  	 	dbOne.close();
}
	
	
	public void CallOnRight(){

  	Log.i("CallOnRight", "********** starteddddddddddd");
    btnNext = (Button) findViewById(R.id.btnnext);
    btnNext.setVisibility(4);
    btnPrevious = (Button) findViewById(R.id.btnprevious);
    btnPrevious.setVisibility(4);
	
    right_Image1.setVisibility(4); 
  	right_Image2.setVisibility(4); 
  	right_Image3.setVisibility(4);
  	right_Image4.setVisibility(4);
    cross_Image1.setVisibility(4);
    cross_Image2.setVisibility(4);
    cross_Image3.setVisibility(4);
    cross_Image4.setVisibility(4);
    image_1.setVisibility(0);
    image_2.setVisibility(0);
    image_3.setVisibility(0);
    image_4.setVisibility(0);
 	    try {
     	   clickONPlayer.stop();
 	       clickONPlayer.reset();  
		   mediaPlayer.stop();
		   mediaPlayer.reset();
 			 
 	   } catch (Exception e) {
		  e.fillInStackTrace();
	   }   
	   if(temp==0) 
	       temp=1; 
 	    
 	    btnNext.setClickable(false);
 //	adView.showAds(adSenseSpec);    	        	
  	text.setText(null);
  	isImageClick = true;
  	istextSound = true;   
  	if (temp < itotalImage ) { 
      	
      	try {	
      		text.setEnabled(true);
      		right_Image1.setVisibility(4); 
  	    	right_Image2.setVisibility(4); 
  	    	right_Image3.setVisibility(4);
  	    	right_Image4.setVisibility(4);
        	cross_Image1.setVisibility(4); 
        	cross_Image2.setVisibility(4); 
        	cross_Image3.setVisibility(4); 
        	cross_Image4.setVisibility(4); 
        	try {
        		OImage = StringArray[temp];   // Correct Image Array.
 	        	OImage = OImage.trim(); 
 	        	OImage = OImage.toLowerCase();
 	        	Oimage = getResources().getIdentifier(OImage , "drawable", getPackageName());		 	        	
 	        	ResultImage.clear(); 
 	        	Result.clear();
 	      	     
 	       	 	
        	} catch (ArrayIndexOutOfBoundsException e) {
	    	      System.out.println("index>>>"+index+"itotalImage>>>>"+itotalImage);
	    	      System.out.println("temp>>>"+temp);
    	    	  Log.e("Error in Bound ****", "Yup, an ArrayIndexOutOfBoundsException was thrown. Details: " + e.toString());
    	   	} catch (Exception e) {
	     	          System.out.println("index>>>"+index+"itotalImage>>>>"+itotalImage);
	     	         System.out.println("temp>>>"+temp);
    	    	      Log.e("Error in Exception ****", "A general Exception was caught. Details: " + e.toString());
    	   	} finally {
					//
			}
  	  	    	
  	   int count = 0;
     	   if(index <= itotalImage ) {
     		  
     	    while( count<3 ) {     	
   	    	
     	    	if(index >= itotalImage)  {
      			    index = 1;
     	    }
       	    	String Temp = StringArray3[index];  //Incorrect Image Array
     	    	int n1      = arrayResult3.get(index);  //Incorrect Image Id.
     	    	try {
     	    		int n   = arrayResult.get(temp);  // Correct Image ID.
     	    		if((n1 != n)&&(ResultImage.indexOf(n)==(-1))&&(Result.indexOf(OImage)==(-1))) {	 
     	    			ResultImage.add(n1);
		     	    	Result.add(Temp);  //Add Incorrect Image in tem Array.	
		     	    	index++;
		     	    	count++; 	
		     	   } else {
	       	    		index++;
	       	    	}	
     	    	} catch (ArrayIndexOutOfBoundsException e) {
     	    		  Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
     	    	      Log.e("Error in Bound ****", "Yup, an ArrayIndexOutOfBoundsException was thrown. Details: " + e.toString());
     	    	} catch (Exception e) {
     	    		  Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
     	    	      Log.e("Error in Exception ****", "A general Exception was caught. Details: " + e.toString());
     	    	} finally {
					//
				}   	
	         }//while  	   
     		  
     	   }
     	    
           ResultImage.clear();
           OImage1 = Result.get(0);
           OImage1 = OImage1.trim(); 
           OImage1 = OImage1.toLowerCase();
      	   Oimage1 = getResources().getIdentifier(OImage1 , "drawable", getPackageName());
        
      	 OImage2 = Result.get(1);
      	 OImage2 = OImage2.trim(); 
      	 OImage2 = OImage2.toLowerCase();
       	 Oimage2 = getResources().getIdentifier(OImage2 , "drawable", getPackageName());
       	 
       	 OImage3 = Result.get(2);
       	 OImage3 = OImage3.trim(); 
       	 OImage3 = OImage3.toLowerCase();
      	 Oimage3 = getResources().getIdentifier(OImage3 , "drawable", getPackageName());

      	 Result.clear();
         	    	
      	 CorrsndLit = 0;   	    	 
    	 int Num    = random.nextInt(4);
    	 InCorrNum  = Num;
    	 CorrNum    = random.nextInt(11);
    	 switch(Num) {
		    case 0:  	  
		           
		    		image_1.setImageResource(Oimage);
		    		if((temp < itotalImage) && (temp > 0) ) { 
		   			 try {
	  			    	  mHandler.postDelayed(new Runnable() {
	  	 					  public void run() {
	  	 						 int tmp = temp - 1 ; 
	  	 						// text.setText(textHeaderArray[tmp]);
	  	 					}
	  	 				  }, 1500);	
		  			    } catch (ArrayIndexOutOfBoundsException e) {
		  			    	Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
		  			    	Log.i("temp","temp>>>"+temp);
		  	    	        Log.e("Error in Bound ****", "Yup, an ArrayIndexOutOfBoundsException was thrown. Details: " + e.toString());
		  	    	   } catch (Exception e) {
		  	    		    Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
		  	    	        Log.e("Error in Exception ****", "A general Exception was caught. Details: " + e.toString());
		  	    	   } finally {
		  					//
		  			   }   
		   			}   
		                ///////////// Clickon Sound//////////////////
	        	     
			          String sndclickon     = Integer.toString(raw.clickon);
			        	sndclickon            = sndclickon.replace(".mp3", "");
			        	sndclickon            = sndclickon.trim();
			      		sndclickon            = sndclickon.replaceAll(" ", "_");
			     	    final int sndclickon1 = getResources().getIdentifier(sndclickon , "raw", getPackageName());
			     	    clickONPlayer         = MediaPlayer.create(getBaseContext(), sndclickon1);
			 	        clickONPlayer.setVolume(100, 100);
			 	           
			     	   //////////// image Sound///////////////		
			    		 String strsnd1   = StringSound[temp];
				 		 strsnd1          = strsnd1.replace(".mp3", "");
			 	     	 strsnd1          = strsnd1.trim();
			 	     	 strsnd1          = strsnd1.replaceAll(" ", "_");
			 	     	 strsnd1          = strsnd1.toLowerCase();
			 	    	 final int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			 	    	 mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd1);
			    		 mediaPlayer.setVolume(100, 100);
		 	    		  
			     	    try {	 
			     		     mHandler.postDelayed(new Runnable() {
			     			    	
			     			    	public void run() {	
			     			    		try {
				    		    			clickONPlayer.start();	
					    	    	     } catch (Exception e) {
										    e.fillInStackTrace();
									     } finally {
										  // 
									     }
			     			    		clickONPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
			    						
			    						public void onCompletion(MediaPlayer actMediaPlayer) {
			    							 try {
			    								 clickONPlayer.reset();
			    								 mediaPlayer.start();
			    								 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
													public void onCompletion(MediaPlayer mp) {
														mediaPlayer.reset();		
													}
												});
			    								 isMediaPlayerActive = true;
			    							 } catch (Exception e) {
			    								 Log.i("Error","Errorrrrr-2>>>" + e);
			    							 } finally {
			    								clickONPlayer.reset();
			    								 
			    							 } 		 	       			 
			    						}
			    					});
			     			   		}
			     			      }, 1000);
			     		    
			     		} catch(Exception e) {
			     			Log.i("Error","Exception"+e);
			     		}
		           		    		
				 	image_2.setImageResource(Oimage3);
				 	image_3.setImageResource(Oimage1);
				 	image_4.setImageResource(Oimage2);
				  
	    	 		
		            //////////Correct image Sound/////////////////	 		     
		      image_1.setOnClickListener(new  View.OnClickListener() {
			        public void onClick(View v) {
			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			        	
			        	isImageClick = false;
			        	 text.setEnabled(false);  
				     	 while(CorrsndLit<1) { 
				     		
				     			
				     		// correctSound();
				     		    right_Image1.setVisibility(0); 
						 	    String corrsnd1 =StringCorrSound[CorrNum];
						 	    corrsnd1 = corrsnd1.replace(".mp3", "");
						 	    corrsnd1 = corrsnd1.trim();
						 	    corrsnd1 = corrsnd1.replaceAll(" ", "_");
						 	    int corrsnd = getResources().getIdentifier(corrsnd1 , "raw", getPackageName());
						 	    try{
						 	     mediaPlayer.reset();
		 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), corrsnd);
		 		 	    		 mediaPlayer.setVolume(100, 100);
		 	 		     	     mediaPlayer.start(); 
		 	 		     	     mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
									public void onCompletion(MediaPlayer mp) {
										mediaPlayer.reset();		
									}
								});
		 	 		     	     CorrsndLit++;
						 	  }catch(Exception e){
						 		  Log.i("Error","Error in 2720 String Array"+e);
			    		       }
				     	 }
						   }	
				     	 
				     	  
				    	
				     	 	cross_Image2.setVisibility(0);   
				     	 	cross_Image3.setVisibility(0);
				     	 	cross_Image4.setVisibility(0);
				     	
				     	 	
				     	 	if(temp == itotalImage) {
						    	 mHandler.postDelayed(new Runnable() {
									public void run() {
									/*	final Bundle bundle = new Bundle();
										bundle.putString("isCommingFromLearning", "false");
				 						bundle.putString("isCommingFromQuestion", "true");
								        bundle.putString("CategoryName", Category);
								        bundle.putStringArrayList("NextCategoryName", NextFlashCategory);   
								        bundle.putInt("Position", position);
								        bundle.putInt("musicLength", musicLength);
								        bundle.putInt("viewScroll", viewScroll);
										*/
										Intent i = new Intent(QuestionCategory.this,HomeScreen.class);
										//i.putExtras(bundle);
							            startActivity(i);
							            finish();
									}
								 }, 1000);
						    	
							}else{
						 	     waitForOpen();
						 	 }
				       }
			    });
		     if(isImageClick) { 
	             text.setOnClickListener(new  View.OnClickListener() {        
			    	 public void onClick(View v) {
			    		 try {
			    			if (isMediaPlayerActive) {
	 				    		 clickONPlayer.stop();
	 				    		 clickONPlayer.reset();
	 				    		 mediaPlayer.stop();
	 				    		 mediaPlayer.reset();
					    	}	 
			    		 } catch (Exception e) {
							e.fillInStackTrace();
						 } finally {
							// 
						 }
				     		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 		    	mediaPlayer.setVolume(100, 100);
		 		 	    	mediaPlayer.start();
		    	        }
		 			});
		          }
	        
				image_2.setOnClickListener(new  View.OnClickListener() {
				       public void onClick(View v) {
				    	   if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
				    	  
				    	   cross_Image2.setVisibility(0); 
				    	  // rongSound();
				    	    String strsnd1 = StringInCorrSound[InCorrNum+2];
				 	     	strsnd1        = strsnd1.replace(".mp3", "");
				 	     	strsnd1        = strsnd1.trim();
				 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
				 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
				 	    	mediaPlayer.reset();
	 	 		    		mediaPlayer   = MediaPlayer.create(getBaseContext(), idsnd1);
	 	 		    		mediaPlayer.setVolume(100, 100);
	 		 	    		mediaPlayer.start();
			                
			              
				  		}
				       }
				  });	
				    
		          image_3.setOnClickListener(new  View.OnClickListener() {
				       public void onClick(View v) {
				    	   if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
				    	   
				    	   cross_Image3.setVisibility(0); 
				    	  // rongSound();
				    	   String strsnd1 = StringInCorrSound[InCorrNum+1];
				 	     	strsnd1       = strsnd1.replace(".mp3", "");
				 	     	strsnd1       = strsnd1.trim();
				 	     	strsnd1       = strsnd1.replaceAll(" ", "_");
				 	    	int idsnd1    = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
				 	    	try{
					 	    	 mediaPlayer.reset();
		 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
		 		 	    		 mediaPlayer.setVolume(100, 100);
		 	 		     	     mediaPlayer.start();
					 	    	 
					 	     }catch(Exception e){
				 	    		Log.i("Error","Error in 2815 String Array"+e);
			    		     }
				    	   }
				        }
				  });
					 		     
				  image_4.setOnClickListener(new  View.OnClickListener() {
				         public void onClick(View v) {
				        	 if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
				        	
				        	 cross_Image4.setVisibility(0);  
				        	 //rongSound();
				        	 String strsnd1 = StringInCorrSound[InCorrNum];
					 	     	strsnd1     = strsnd1.replace(".mp3", "");
					 	     	strsnd1     = strsnd1.trim();
					 	     	strsnd1     = strsnd1.replaceAll(" ", "_");
					 	    	int idsnd1  = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
					 	    	try{
						 	    	 mediaPlayer.reset();
			 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 		 	    		 mediaPlayer.setVolume(100, 100);
			 	 		     	   mediaPlayer.start();
						 	    	
						 	    	
					 	    	 }catch(Exception e){
					 	    		Log.i("Error","Error in 2835 String Array"+e);
				    		      }
				        	 }
				        }
			      });
				  
			      break;
				     
		   case 1:
		
			        image_1.setImageResource(Oimage1);
			        image_2.setImageResource(Oimage);
			       if((temp < itotalImage) && (temp > 0) ) { 
			 			 try {
						    	mHandler.postDelayed(new Runnable() {
				 					public void run() {
				 						 int tmp = temp - 1 ; 
				 						 //text.setText(textHeaderArray[tmp]);
				 					}
				 				}, 1500);	
						    } catch (ArrayIndexOutOfBoundsException e) {
						    	Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
				     	        Log.i("temp","temp>>>"+temp);
				    	        Log.e("Error in Bound ****", "Yup, an ArrayIndexOutOfBoundsException was thrown. Details: " + e.toString());
				    	   } catch (Exception e) {
				    		    Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
				    	        Log.e("Error in Exception ****", "A general Exception was caught. Details: " + e.toString());
				    	   } finally {
								//
						   }   
			 		}   
		           ///////////// Clickon Sound//////////////////
	        	     
		            String sndclickon2 =Integer.toString(raw.clickon);
		        	sndclickon2 = sndclickon2.replace(".mp3", "");
		        	sndclickon2 = sndclickon2.trim();
		      		sndclickon2 = sndclickon2.replaceAll(" ", "_");
		     	    final int sndclickon12 = getResources().getIdentifier(sndclickon2 , "raw", getPackageName());
		     	    clickONPlayer = MediaPlayer.create(getBaseContext(), sndclickon12);
		 	        clickONPlayer.setVolume(100, 100);
		 	           
		     	    /////////////////  image Sound////////////////////////       
			        String strsnd2 =StringSound[temp];
		 	     	strsnd2 = strsnd2.replace(".mp3", "");
		 	     	strsnd2 = strsnd2.trim();
		 	     	strsnd2 = strsnd2.replaceAll(" ", "_");
		 	     	strsnd2 = strsnd2.toLowerCase();
		 	    	final int idsnd2 = getResources().getIdentifier(strsnd2 , "raw", getPackageName());
		     	  		 	    	 
		     	    mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd2);
		    		mediaPlayer.setVolume(100, 100);
	 	    		  
		     	    try {	 
		     		     mHandler.postDelayed(new Runnable() {
		     			    	
		     			    	public void run() {
	
		     			    		try {
			    		    			clickONPlayer.start();	
				    	    	     } catch (Exception e) {
									    e.fillInStackTrace();
								     } finally {
									  // 
								     }
		     			    		clickONPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
		    						
			    						public void onCompletion(MediaPlayer actMediaPlayer) {
			    							 try {
			    								 clickONPlayer.reset();
			    								 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd2);
			    			 		    		 mediaPlayer.setVolume(100, 100);
			    			 	 	    		 mediaPlayer.start();
			    								 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
													public void onCompletion(MediaPlayer mp) {
															mediaPlayer.reset();		
														}
												  });
			    								 isMediaPlayerActive = true;
			    							 } catch (Exception e) {
			    								 Log.i("Error","Errorrrrr0>>>" + e);
			    							 } finally {
			    								
			    							 } 		 	       			 
			    						}
		    					});
		     			   		}
		     			      }, 1000);
		     		    
		     		} catch(Exception e) {
		     		    	Log.i("Error","Exception"+e);
		     		}
			      
		 	     				        
			 		image_3.setImageResource(Oimage3);
			 	    image_4.setImageResource(Oimage2);
			 	    
			/////////////////Correct image Sound////////////////////////	 	    
			 	   if(image_2.isClickable()) {
			 		    image_2.setOnClickListener(new  View.OnClickListener() {
				        public void onClick(View v) {
				        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
				          isImageClick = false;
				          text.setEnabled(false);  
						   while(CorrsndLit<1){ 
							 
							  // correctSound();
							   right_Image2.setVisibility(0);
							    String corrsnd1 = StringCorrSound[CorrNum];
							    corrsnd1        = corrsnd1.replace(".mp3", "");
						 	    corrsnd1        = corrsnd1.trim();
						 	    corrsnd1        = corrsnd1.replaceAll(" ", "_");
						 	    int corrsnd     = getResources().getIdentifier(corrsnd1 , "raw", getPackageName());
						 	    try{
						 	      mediaPlayer.reset();
		 	 		    		  mediaPlayer   = MediaPlayer.create(getBaseContext(), corrsnd);
		 		 	    		  mediaPlayer.setVolume(100, 100);
		 	 		     	      mediaPlayer.start(); CorrsndLit++;
						 	  }catch(Exception e) {
						 		 Log.i("Error","Error in 1145 String Array"+e);
			    		       }
						 	   
						   }	  
						  
					        
					       cross_Image1.setVisibility(0);
						   cross_Image3.setVisibility(0);
					       cross_Image4.setVisibility(0);
					  
					       if(temp == itotalImage) {
						    	 mHandler.postDelayed(new Runnable() {
										public void run() {
											/*final Bundle bundle = new Bundle();
											bundle.putString("isCommingFromLearning", "false");
					 						bundle.putString("isCommingFromQuestion", "true");
									        bundle.putString("CategoryName", Category);
									        bundle.putStringArrayList("NextCategoryName", NextFlashCategory);   
									        bundle.putInt("Position", position);
									        bundle.putInt("musicLength", musicLength);
									        bundle.putInt("viewScroll", viewScroll);
											*/
											Intent i = new Intent(QuestionCategory.this,HomeScreen.class);
											//i.putExtras(bundle);
								            startActivity(i);
								            finish();
										}
								 }, 1000);
						   }else{
						 	     waitForOpen();
						 	}
						   }
				        }
			 		});
			 	   }
			 	  if(isImageClick) { 
			             text.setOnClickListener(new  View.OnClickListener() {        
					    	 public void onClick(View v) {
					    		 try {
					    			if (isMediaPlayerActive) {
			 				    		clickONPlayer.stop();
			 				    		clickONPlayer.reset();
			 				    		 mediaPlayer.stop();
			 				    		 mediaPlayer.reset();
		 				    		 }	 
					    		 } catch (Exception e) {
									e.fillInStackTrace();
								 } finally {
									// 
								 }
					     		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd2);
			 		    		mediaPlayer.setVolume(100, 100);
		 		 	    	 	mediaPlayer.start();
		 	    	          }
				 		 });
				     }
			         	   
			 	  if(image_1.isClickable()) {
			   		        image_1.setOnClickListener(new  View.OnClickListener() {
			  			        public void onClick(View v) {
			  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			  			      	
			  			      cross_Image1.setVisibility(0);
			  			   // rongSound();
			  			        	String strsnd1 = StringInCorrSound[InCorrNum+1];
						 	     	strsnd1        = strsnd1.replace(".mp3", "");
						 	     	strsnd1        = strsnd1.trim();
						 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
						 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
						 	    	try{
							 	    	 mediaPlayer.reset();
				 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
				 		 	    		 mediaPlayer.setVolume(100, 100);
				 	 		     	     mediaPlayer.start();
							 	    	  
							 	    	
						 	    	 }catch(Exception e){
						 	    		Log.i("Error","Error in 3034 String Array"+e);
					    		    }
						 	    
			  			      }
			  			        }
			   		    });
			 		} 
			 	  if(image_3.isClickable()) {
			   		        image_3.setOnClickListener(new  View.OnClickListener() {
		  			        public void onClick(View v) {
		  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
		  			        	
		  			        	cross_Image3.setVisibility(0);
		  			        //	rongSound();
		  			        	String strsnd1 = StringInCorrSound[InCorrNum];
					 	     	strsnd1        = strsnd1.replace(".mp3", "");
					 	     	strsnd1        = strsnd1.trim();
					 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
					 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
					 	    	try{
						 	    	 mediaPlayer.reset();
			 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 		 	    		 mediaPlayer.setVolume(100, 100);
			 	 		     	     mediaPlayer.start();
						 	    	  
						 	    	
					 	    	 }catch(Exception e) {
					 	    		Log.i("Error","Error in 3055 String Array"+e);
				    		     }
		  			        	}
		  			      }
			   		   });
			 		} 
			 	  if(image_4.isClickable()) {
			   		        image_4.setOnClickListener(new  View.OnClickListener() {
			  			        public void onClick(View v) {
			  			        	if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			  			        	
			  			        	 cross_Image4.setVisibility(0);
			  			        //	rongSound();
			  			        	String strsnd1 = StringInCorrSound[InCorrNum+2];
						 	     	strsnd1        = strsnd1.replace(".mp3", "");
						 	     	strsnd1        = strsnd1.trim();
						 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
						 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
						 	    	try{
							 	    	 mediaPlayer.reset();
				 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
				 		 	    		 mediaPlayer.setVolume(100, 100);
				 	 		     	     mediaPlayer.start();
							 	    	
							 	    	
						 	    	 }catch(Exception e){
						 	    		Log.i("Error","Error in 3077 String Array"+e);
					    		     }
			  			        	}
			  			       }
			   		    });
			 	   }    	    
			 	  break;
		   case 2:
			
			    image_1.setImageResource(Oimage1);
			    image_2.setImageResource(Oimage2);
			    image_3.setImageResource(Oimage);
			    if((temp < itotalImage) && (temp > 0) ) { 
		 			 try {
					    	mHandler.postDelayed(new Runnable() {
			 					public void run() {
			 						 int tmp = temp - 1 ; 
			 						// text.setText(textHeaderArray[tmp]);
			 					}
			 				}, 1500);	
					    } catch (ArrayIndexOutOfBoundsException e) {
					       Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
					       Log.i("temp","temp>>>"+temp);
			    	       Log.e("Error in Bound ****", "Yup, an ArrayIndexOutOfBoundsException was thrown. Details: " + e.toString());
			    	   } catch (Exception e) {
			    		   Log.i("Error","index>>>"+index+"itotalImage>>>>"+itotalImage);
			    	       Log.e("Error in Exception ****", "A general Exception was caught. Details: " + e.toString());
			    	   } finally {
							//
					   }   
		 			}   
			    
			    ///////////// Clickon Sound//////////////////
	   	     
	            String sndclickon3     = Integer.toString(raw.clickon);
	        	sndclickon3            = sndclickon3.replace(".mp3", "");
	        	sndclickon3            = sndclickon3.trim();
	      		sndclickon3            = sndclickon3.replaceAll(" ", "_");
	     	    final int sndclickon13 = getResources().getIdentifier(sndclickon3 , "raw", getPackageName());
	     	    clickONPlayer          = MediaPlayer.create(getBaseContext(), sndclickon13);
	 	        clickONPlayer.setVolume(100, 100);
	 	           
	 	          
	     	    /////////////////  image Sound////////////////////////	         
	      	    String strsnd3   = StringSound[temp];
			   	strsnd3          = strsnd3.replace(".mp3", "");
			 	strsnd3          = strsnd3.trim();
			  	strsnd3          = strsnd3.replaceAll(" ", "_");
			  	strsnd3          = strsnd3.toLowerCase();
				final int idsnd3 = getResources().getIdentifier(strsnd3 , "raw", getPackageName());
	     	  	mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd3);
	    		mediaPlayer.setVolume(100, 100);
		    		  
	     	    try {	 
	     		     mHandler.postDelayed(new Runnable() {
	     			    	
	     			    	public void run() {
	
	     			    		try {
		    		    			clickONPlayer.start();	
			    	    	     } catch (Exception e) {
								    e.fillInStackTrace();
							     } finally {
								  // 
							     }
	     			    		clickONPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
	    						
	    						public void onCompletion(MediaPlayer actMediaPlayer) {
	    							 try {
	    								 clickONPlayer.reset();
	    								 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd3);
	    			 		    		 mediaPlayer.setVolume(100, 100);
	    			 	 	    		 mediaPlayer.start();
	    								 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
	    									 public void onCompletion(MediaPlayer mp) {
												 mediaPlayer.reset();		
											  }
										 });
	    								 isMediaPlayerActive = true;
	    							 } catch (Exception e) {
	    								 Log.i("Error","Errorrrrr2>>>" + e);
	    							 } finally {
	    								
	    							 } 		 	       			 
	    						}
	    					});
	     			   		}
	     			      }, 1000);
	     		    
	     		} catch(Exception e) {
	     		    	System.out.print("Exception"+e);
	     		}
			   
						         
		       image_4.setImageResource(Oimage3);
		       
	          /////////////Correct image Sound/////////////////		 		     
		      if(image_3.isClickable()) {
			    image_3.setOnClickListener(new  View.OnClickListener() {
			       public void onClick(View v) {
			    	   if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			    	   isImageClick = false;
			    	   text.setEnabled(false);  
						 while(CorrsndLit < 1){ 
							
							// correctSound();
							 right_Image3.setVisibility(0);
					       String corrsnd1 = StringCorrSound[CorrNum];
					       corrsnd1        = corrsnd1.replace(".mp3", "");
					       corrsnd1        = corrsnd1.trim();
					 	   corrsnd1        = corrsnd1.replaceAll(" ", "_");
					 	   int corrsnd     = getResources().getIdentifier(corrsnd1 , "raw", getPackageName());
					 	    try{  
					 	     mediaPlayer.reset();
	 	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), corrsnd);
	 		 	    		 mediaPlayer.setVolume(100, 100);
	 	 		     	     mediaPlayer.start();
	 	 		     	     CorrsndLit++;
					 	  }catch(Exception e) {
					 		 Log.i("Error","Error in 3199 String Array"+e);
		    		       }
					 	  
						 }				
						
					     cross_Image1.setVisibility(0);
						 cross_Image2.setVisibility(0);
						 cross_Image4.setVisibility(0);
					
						 if(temp == itotalImage) {
					    	 mHandler.postDelayed(new Runnable() {
									public void run() {
									/*	final Bundle bundle = new Bundle();
										bundle.putString("isCommingFromLearning", "false");
				 						bundle.putString("isCommingFromQuestion", "true");
								        bundle.putString("CategoryName", Category);
								        bundle.putStringArrayList("NextCategoryName", NextFlashCategory);   
								        bundle.putInt("Position", position);
								        bundle.putInt("musicLength", musicLength);
								        bundle.putInt("viewScroll", viewScroll);
									*/
										Intent i= new Intent(QuestionCategory.this,HomeScreen.class);
									//	i.putExtras(bundle);
							            startActivity(i);
							            finish();
									}
							 }, 1000);
					    	
						}else{
					 	     waitForOpen();
					 	}
						 }
				    }
				 });
		 	   }
		     if(isImageClick) { 
	             text.setOnClickListener(new  View.OnClickListener() {        
			    	 public void onClick(View v) {
			    		 try {
			    			if (isMediaPlayerActive) {
	 				    		clickONPlayer.stop();
	 				    		clickONPlayer.reset();
	 				    		 mediaPlayer.stop();
	 				    		 mediaPlayer.reset();
					    		 }	 
			    		 } catch (Exception e) {
							e.fillInStackTrace();
						 } finally {
							// 
						 }
			     		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd3);
		 		    	mediaPlayer.setVolume(100, 100);
	 		 	    	mediaPlayer.start();
		    	     }
		 		});
		      }
	          	  
		      if(image_1.isClickable()) {
				 image_1.setOnClickListener(new  View.OnClickListener() {
			  	   public void onClick(View v) {
			  		 if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			  			
			  			cross_Image1.setVisibility(0);
			  		//	rongSound();
			  		   	String strsnd1 = StringInCorrSound[InCorrNum+1];
			 	     	strsnd1        = strsnd1.replace(".mp3", "");
			 	     	strsnd1        = strsnd1.trim();
			 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
			 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			 	    	try{
				 	    	mediaPlayer.reset();
			 		    	mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 	    		mediaPlayer.setVolume(100, 100);
			 		     	mediaPlayer.start();
				 	    						 	    	
			 	    	 }catch(Exception e){
			 	    		Log.i("Error","Error in 3286 String Array"+e);
		    		       }
			  		 }
			  	    }
				 });
			  } 
	
		     if(image_2.isClickable()) {
			   image_2.setOnClickListener(new  View.OnClickListener() {
			     public void onClick(View v) {
			    	 if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			    	 
			    	 cross_Image2.setVisibility(0); 
			   // 	 rongSound();
			    	String strsnd1 = StringInCorrSound[InCorrNum];
		 	     	strsnd1        = strsnd1.replace(".mp3", "");
		 	     	strsnd1        = strsnd1.trim();
		 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
		 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
		 	    	try{
		 	    	     mediaPlayer.reset();
	 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
	 	    		     mediaPlayer.setVolume(100, 100);
	 		     	     mediaPlayer.start();
			 	         
			 	        
		 	    	 }catch(Exception e){
		 	    		Log.i("Error","Error in 3300 String Array"+e);
	    		     }
			    	 }
			  	  }
				});
			 } 
		   
		    if(image_4.isClickable()) {
			  image_4.setOnClickListener(new  View.OnClickListener() {
			       public void onClick(View v) {
			    	   if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			    	  
			    	   cross_Image4.setVisibility(0);
			    	//   rongSound();
				    	String strsnd1 =StringInCorrSound[InCorrNum-1];
			 	     	strsnd1 = strsnd1.replace(".mp3", "");
			 	     	strsnd1 = strsnd1.trim();
			 	     	strsnd1 = strsnd1.replaceAll(" ", "_");
			 	    	int idsnd1 = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			 	    	    try{
				 	    	     mediaPlayer.reset();
			 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
				 	    		 mediaPlayer.setVolume(100, 100);
			 		     	     mediaPlayer.start();
				 	    	    
				 	    	 
			 	    	    }catch(Exception e){
			 	    	    	Log.i("Error","Error in 1145 String Array"+e);
		   		            }
			    	   }
			    	}
			  });
		    }   		     
			 break;
			 
		 case 3:
			
		   	 image_1.setImageResource(Oimage1);
		     image_2.setImageResource(Oimage2);
		     image_3.setImageResource(Oimage3);
			 image_4.setImageResource(Oimage);
			if((temp < itotalImage) && (temp > 0) ) { 
			try {
		    	mHandler.postDelayed(new Runnable() {
					
					public void run() {
						 int tmp = temp - 1 ; 
						// text.setText(textHeaderArray[tmp]);
					}
				}, 1500);	
			  } catch (ArrayIndexOutOfBoundsException e) {
				   Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
	    	        Log.i("temp","temp>>>"+temp);
	   	        Log.e("Error in Bound ****", "Yup, an ArrayIndexOutOfBoundsException was thrown. Details: " + e.toString());
	   	   } catch (Exception e) {
	   		   	Log.i("index","index>>>"+index+"itotalImage>>>>"+itotalImage);
	   	        Log.e("Error in Exception ****", "A general Exception was caught. Details: " + e.toString());
	   	   } finally {
					//
			   }   
			}   
			  ///////////// Clickon Sound//////////////////
	   	     
	            String sndclickon4     = Integer.toString(raw.clickon);
	        	sndclickon4            = sndclickon4.replace(".mp3", "");
	        	sndclickon4            = sndclickon4.trim();
	      		sndclickon4            = sndclickon4.replaceAll(" ", "_");
	     	    final int sndclickon14 = getResources().getIdentifier(sndclickon4 , "raw", getPackageName());
	     	    clickONPlayer          = MediaPlayer.create(getBaseContext(), sndclickon14);
	 	        clickONPlayer.setVolume(100, 100);
	 	           
	 	          
	     	    /////////////////  image Sound////////////////////////		
				String strsnd4   = StringSound[temp];
				strsnd4          = strsnd4.replace(".mp3", "");
				strsnd4          = strsnd4.trim();
				strsnd4          = strsnd4.replaceAll(" ", "_");
				strsnd4          = strsnd4.toLowerCase();
				final int idsnd4 = getResources().getIdentifier(strsnd4 , "raw", getPackageName());
	     	  		 	    	 
	     	    mediaPlayer      = MediaPlayer.create(getBaseContext(), idsnd4);
	    		 
	    			 mediaPlayer.setVolume(100, 100);
	    		   
	     	    try {	 
	     		     mHandler.postDelayed(new Runnable() {
	     			    public void run() {
	
	 			    		try {
	    		    			clickONPlayer.start();	
		    	    	     } catch (Exception e) {
							    e.fillInStackTrace();
						     } finally {
							  // 
						     }
	 			    		clickONPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {	
	 			    			public void onCompletion(MediaPlayer actMediaPlayer) {
									 try {
										 clickONPlayer.reset();
										 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd4);
					 		    		 mediaPlayer.setVolume(100, 100);
					 	 	    		 mediaPlayer.start();
										 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
											public void onCompletion(MediaPlayer mp) {
													mediaPlayer.reset();		
												}
											});
										 isMediaPlayerActive = true;
									 } catch (Exception e) {
										 Log.i("Error","Errorrrrr4>>>" + e);
									 } finally {
										//
									 } 	       			 
								}
							});
	     			   }
	     			 }, 1000);
	     		    
	     		} catch(Exception e) {
	     			Log.i("Error","Exception"+e);
	     		}
			
						
	         ///////////////////Correct image Sound///////////////		        	 
		  if(image_4.isClickable()) {
			image_4.setOnClickListener(new  View.OnClickListener() {
			  public void onClick(View v) {
				  if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
				  isImageClick = false;
				  text.setEnabled(false);  
				 while(CorrsndLit<1){ 
					
					  //correctSound();
					  right_Image4.setVisibility(0);
					  String corrsnd1 = StringCorrSound[CorrNum];
					  corrsnd1        = corrsnd1.replace(".mp3", "");
					  corrsnd1        = corrsnd1.trim();
					  corrsnd1        = corrsnd1.replaceAll(" ", "_");
					  int corrsnd     = getResources().getIdentifier(corrsnd1 , "raw", getPackageName());
					    try{
						     mediaPlayer.reset();
		 		    		 mediaPlayer = MediaPlayer.create(getBaseContext(), corrsnd);
			 	    		 mediaPlayer.setVolume(100, 100);
		 		     	     mediaPlayer.start(); 
		 		     	     CorrsndLit++;
					   }catch(Exception e){
						   Log.i("Error","Error in 3444 String Array"+e);
	   		           }
					  
				 }	 	  
				 
			    	
				 	cross_Image1.setVisibility(0);
				 	cross_Image3.setVisibility(0);
				 	cross_Image2.setVisibility(0);
				 	
				 	 if(temp == itotalImage) {
				    	 mHandler.postDelayed(new Runnable() {
								public void run() {
								/*	final Bundle bundle = new Bundle();
									bundle.putString("isCommingFromLearning", "false");
			 						bundle.putString("isCommingFromQuestion", "true");
							        bundle.putString("CategoryName", Category);
							        bundle.putStringArrayList("NextCategoryName", NextFlashCategory);   
							        bundle.putInt("Position", position);
							        bundle.putInt("musicLength", musicLength);
							        bundle.putInt("viewScroll", viewScroll);
								*/
									Intent i = new Intent(QuestionCategory.this,HomeScreen.class);
								//	i.putExtras(bundle);
						            startActivity(i);
						           finish();
								}
						 }, 1000);
				    	
					}else{
				 	    waitForOpen();
				 	}
				 }
			  	}
			 });
		  }
		 if(isImageClick) { 
	         text.setOnClickListener(new  View.OnClickListener() {        
		    	 public void onClick(View v) {
		    		 try {
		    			if (isMediaPlayerActive) {
				    		clickONPlayer.stop();
				    		clickONPlayer.reset();
				    		mediaPlayer.stop();
				    		mediaPlayer.reset();
				    	 }	 
		    		  } catch (Exception e) {
						e.fillInStackTrace();
					  } finally {
						// 
					 }
		     		mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd4);
		    		mediaPlayer.setVolume(100, 100);
	 	    	 	mediaPlayer.start();
	 	                }
	 			});
	          }
		 
		 if(image_1.isClickable()) {
	       image_1.setOnClickListener(new  View.OnClickListener() {
	           public void onClick(View v) {
	        	   if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
	        		
	        		cross_Image1.setVisibility(0);
	        	//	rongSound();
	        	    String strsnd1 = StringInCorrSound[InCorrNum-1];
		 	     	strsnd1        = strsnd1.replace(".mp3", "");
		 	     	strsnd1        = strsnd1.trim();
		 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
		 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
		 	    		try{
			 	    	     mediaPlayer.reset();
		 		    		   mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 	    		   mediaPlayer.setVolume(100, 100);
			 	    		   mediaPlayer.start();
			 	    		 
			 	    		
				        }catch(Exception e){
				        	Log.i("Error","Error in 1145 String Array"+e);
					    }
	        	   }
			     }
			});
		 } 
			
		 if(image_3.isClickable()) {
		  image_3.setOnClickListener(new  View.OnClickListener() {
			public void onClick(View v) {
				if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
				
				 cross_Image3.setVisibility(0);
			//	 rongSound();
				String strsnd1 = StringInCorrSound[InCorrNum];
	 	     	strsnd1        = strsnd1.replace(".mp3", "");
	 	     	strsnd1        = strsnd1.trim();
	 	     	strsnd1        = strsnd1.replaceAll(" ", "_");
	 	    	int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
	 	    	try{
		 	    	 mediaPlayer.reset();
			    	 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 	     mediaPlayer.setVolume(100, 100);
			     	 mediaPlayer.start();
		 	    	
		 	    	
	 	    	 }catch(Exception e){
			        Log.i("Error","Error in 3546 String Array"+e);
			     }
				}
			}
	       });
		 } 
		 if(image_2.isClickable()) {
		     image_2.setOnClickListener(new  View.OnClickListener() {
			     public void onClick(View v) {
			    	 if(!mediaPlayer.isPlaying() && !clickONPlayer.isPlaying()){
			    	 
			    	 cross_Image2.setVisibility(0);
			//    	 rongSound();
			    	 String strsnd1 = StringInCorrSound[InCorrNum - 2];
			 	     strsnd1        = strsnd1.replace(".mp3", "");
			 	     strsnd1        = strsnd1.trim();
			 	     strsnd1        = strsnd1.replaceAll(" ", "_");
			 	     int idsnd1     = getResources().getIdentifier(strsnd1 , "raw", getPackageName());
			 	     try{
				 	     mediaPlayer.reset();
				    	 mediaPlayer = MediaPlayer.create(getBaseContext(), idsnd1);
			 	    	 mediaPlayer.setVolume(100, 100);
				     	 mediaPlayer.start();
				 	    
				 	  
			 	    }catch(Exception e){
			 		 Log.i("Error","Error in 3567 String Array"+e);
			        }
			    	 } 
		         }
			});
		  }           	 
		 break; 			        
 } ////end of switch 2*/
     
  	flipper.setInAnimation(inFromRightAnimation());
  	flipper.setOutAnimation(outToLeftAnimation());
  	flipper.showNext();
  	temp++;
      } catch (Exception e) {
  	   Log.i("Error","Error in Next button" + e);
	} finally {
		//
	}      
 } /////////if//////    
  	/*else{
  		Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_LONG).show();
  		
  	} 	*/
  	//
      btnNext.setClickable(true); 
 }


/**
 * home button click listener will show home page.
 */
private OnClickListener homeListener = new OnClickListener() {
  		public void onClick(View v) {
  			// do something when the button is clicked
				Log.i("home","Clickedddddddddd");
				
				final Bundle bundle = new Bundle();
		        bundle.putInt("musicLength", musicLength);
		        bundle.putInt("viewScroll", viewScroll);
  			try {
        		mediaPlayer.stop();
      			mediaPlayer.reset();
      			clickONPlayer.stop(); 
      			clickONPlayer.reset();
				
        	} catch (Exception e) {
        		Log.i("Error","Error in 182");
        		e.printStackTrace();  //Output goes to System.err.
			}finally{
        		Log.i("Error","Error in 188");
        	}
        	try {
        		
        		Intent i = new Intent(v.getContext(), HomeScreen.class);
        		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		//i.putExtras(bundle);
               	startActivityForResult(i,0);
               	   finish();
              
        	} catch (Exception e) {
        		Log.i("Error","Output goes to System.err");
        		e.printStackTrace();  //Output goes to System.err.
        	}
  		}
  	};
	
/**
 * settings button click listener will show settings page.
 */
private OnClickListener settingsListener = new OnClickListener() {
	public void onClick(View v) {
		// do something when the button is clicked
		/* final Bundle bundleSetting = new Bundle();
 		 bundleSetting.putString("isCommingFromLearning", "false");
 		 bundleSetting.putString("isCommingFromQuestion", "true");
 		 bundleSetting.putString("CategoryName", Category);
 		 bundleSetting.putInt("position", position);
 		 bundleSetting.putInt("musicLength", musicLength);
 		 bundleSetting.putStringArrayList("NextCategoryName", NextFlashCategory);
 		 bundleSetting.putInt("viewScroll", viewScroll);*/
 		try {
      		mediaPlayer.stop();
  			mediaPlayer.reset();
  			clickONPlayer.stop(); 
  			clickONPlayer.reset();	 
      	} catch (Exception e) {
      		Log.i("Error","Error in 182");
      		e.printStackTrace();  //Output goes to System.err.
		}
      	try {
      		Intent i = new Intent(v.getContext(), Setting.class);
        	//i.putExtras(bundleSetting);
             	startActivity(i);
          	finish();
      	} catch (Exception e) {
      		e.printStackTrace();  //Output goes to System.err.
      	}
	}
};

public ArrayList<Integer> GenRandomImage(int size) {
     Random random=new Random();
     int  Num=random.nextInt(size);
       for(int i=0;i<4;) {
           Num=random.nextInt(size);
           if(arrayRandomImage.indexOf( Num)==(-1)) {
         	     arrayRandomImage.add( Num);
               i++;
      	  } 
       }
       return arrayRandomImage;
}

/**
 * Populate sound array from raw folder.
 */
private void populatesoundArray(){
	CorrtSoundArray.add(Integer.toString(raw.youareawesome));
    CorrtSoundArray.add(Integer.toString(raw.beautiful));
    CorrtSoundArray.add(Integer.toString(raw.bravo));
    CorrtSoundArray.add(Integer.toString(raw.excellent));
    CorrtSoundArray.add(Integer.toString(raw.fantastic));
    CorrtSoundArray.add(Integer.toString(raw.goodanswer));
    CorrtSoundArray.add(Integer.toString(raw.goodjob));
    CorrtSoundArray.add(Integer.toString(raw.great));
    CorrtSoundArray.add(Integer.toString(raw.marvelous));
    CorrtSoundArray.add(Integer.toString(raw.sensational));
    CorrtSoundArray.add(Integer.toString(raw.spectacular));
    InCorrtSoundArray.add(Integer.toString(raw.oopsie));
    InCorrtSoundArray.add(Integer.toString(raw.tryagain));
    InCorrtSoundArray.add(Integer.toString(raw.uhoh));
    InCorrtSoundArray.add(Integer.toString(raw.youcandoit));
    
    StringCorrSound   = (String[]) CorrtSoundArray.toArray(new String[CorrtSoundArray.size()]);
    StringInCorrSound = (String[]) InCorrtSoundArray.toArray(new String[InCorrtSoundArray.size()]);
}
public void waitForOpen(){
	 mHandler.postDelayed(new Runnable() {
			public void run() {
				CallOnRight();
			}
	 }, 2000);
}

public ArrayList<Integer> GenRandomNum(ArrayList<Integer> arrayID2) {
    ArrayList<Integer> arraylist = new ArrayList<Integer>();
    int length =arrayID2.size();
      Random random = new Random();
      int  randomNum = random.nextInt(500);
         for(int i=0; i<length;) {
             randomNum=random.nextInt(500);
             if(arrayID2.contains(randomNum)) {
          		   if(arraylist.indexOf(randomNum)==(-1)) {
          			     arraylist.add(randomNum);
      	    	         i++;
      	    	     continue;      
      	           }
             }
         }
      return arraylist;
  }/////////ArrayList  



public void unbindDrawables(View view) {
      if (view.getBackground() != null) {
      view.getBackground().setCallback(null);
      }
      if (view instanceof ViewGroup) {
          for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
          unbindDrawables(((ViewGroup) view).getChildAt(i));
          }
      ((ViewGroup) view).removeAllViews();
      }
  }
	 


 @Override
	public void onBackPressed() {
	// btnHome.setOnClickListener(homeListener);
	//Toast.makeText(getApplicationContext(), "method end", Toast.LENGTH_LONG).show();
	   
	    Intent i = new Intent(this, HomeScreen.class);
	   // i.putExtras(bundle);
		  startActivity(i); 
		  finish();
	
}
 
/* private void correctSound(){
	 
	// for extra sound 
	   // android.os.SystemClock.sleep(1000);
		mediaPlayer1 = MediaPlayer.create(this, R.raw.right_answer);
		mediaPlayer1.start();
		android.os.SystemClock.sleep(700);
 }
 private void rongSound(){
	 
		// for extra sound 
	        //
			mediaPlayer1 = MediaPlayer.create(this, R.raw.wrong_answer);
			mediaPlayer1.start();
			android.os.SystemClock.sleep(800);
	 }
*/


} //end of main