package com.example.sudoku;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class communicates with the DB (adds stuff, removes stuff and get stuff)
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "highScoreManager";
 
    // HighScore table name
    private static final String HIGH_SCORE = "hiScore";
 
    // HighScore Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "score";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + HIGH_SCORE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " INTEGER"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + HIGH_SCORE);
        // Create tables again
        onCreate(db);
    }
    
    // Adding new score
    void addScore(int score) throws SQLException{
        
    	ContentValues values = new ContentValues();
        values.put(KEY_NAME, score); // Contact Name
        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        db.insert(HIGH_SCORE, null, values);
        db.close(); // Closing database connection&
    }
    
    // Getting All Scores
    public List<Integer> getAllScores() {
        List<Integer> highScoreList = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + HIGH_SCORE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	highScoreList.add(cursor.getInt(1));
            } while (cursor.moveToNext());
        }
        // return highScore list
        return highScoreList;
    }
    
    //Deleting all items
    public void purge(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HIGH_SCORE,null,null);
    }
}