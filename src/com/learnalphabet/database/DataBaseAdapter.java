package com.learnalphabet.database;

import java.io.IOException; 
import java.util.ArrayList;

import com.learnalphabets.extras.BeanClass;

import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log; 
 
public class DataBaseAdapter  
{ 
    protected static final String TAG = "DataAdapter"; 
 
    private final Context mContext; 
    private SQLiteDatabase mDb; 
    private DataBaseHelper mDbHelper; 
 
    public DataBaseAdapter(Context context)  
    { 
        this.mContext = context; 
        mDbHelper = new DataBaseHelper(mContext); 
    } 
 
    public DataBaseAdapter createDatabase() throws SQLException  
    { 
        try  
        { 
            mDbHelper.createDataBase(); 
        }  
        catch (IOException mIOException)  
        { 
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    } 
 
    public DataBaseAdapter open() throws SQLException  
    { 
        try  
        { 
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            mDb = mDbHelper.getReadableDatabase(); 
        }  
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 
 
    public void close()  
    { 
        mDbHelper.close(); 
    } 
 
     public void getSixAlphabet(String oneAlphabet){
         try 
         { 
             String sql ="SELECT * FROm Alphabets WHERE Alphabet='"+oneAlphabet+"'"; 
 
             Cursor cursor = mDb.rawQuery(sql, null); 
             
             ArrayList arrayList = new ArrayList();
           
             if (cursor.moveToFirst()) {
               do {
                  String name=cursor.getString(2); 
                 
                  //String[] alphabets = String[name];
                  
                  arrayList.add(name);
                  
                System.out.println("name ================"+ name +"===============");

               } while (cursor.moveToNext());
            }
            if (cursor != null && !cursor.isClosed()) {
               cursor.close();
            }
            
            BeanClass.setArrayList(arrayList);
            
         } 
         catch (SQLException mSQLException)  
         { 
             Log.e(TAG, "getTestData >>"+ mSQLException.toString()); 
             throw mSQLException; 
         } 
     }
     

 	public boolean SaveEmployee(String name, String email) 
 	{
 		try
 		{
 			ContentValues cv = new ContentValues();
 			cv.put("Name", name);
 			cv.put("Email", email);
 			
 			mDb.insert("Employees", null, cv);

 			Log.d("SaveEmployee", "informationsaved");
 			return true;
 			
 		}
 		catch(Exception ex)
 		{
 			Log.d("SaveEmployee", ex.toString());
 			return false;
 		}
 	}
     

} 

