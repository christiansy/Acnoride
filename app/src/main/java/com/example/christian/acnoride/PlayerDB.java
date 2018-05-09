package com.example.christian.acnoride;

/**
 * Created by Christian on 5/5/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class PlayerDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "player";
    private static final String TABLE_PLAYER_INFORMATION = "playerInformation";


    // TABLE COLUMN NAMES
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_SCORE = "score";
    private static final String KEY_RANK = "rank";


    public PlayerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYER_INFORMATION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYER_INFORMATION
                + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_SCORE + " TEXT,"
                + KEY_RANK + " TEXT " + ")";

        db.execSQL(CREATE_PLAYER_INFORMATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drops older table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_INFORMATION);
        //recreates table
        onCreate(db);
    }


    public void insertNewPlayer(Player newUser) {
        // Create a new map of values, where column names are the keys
        ContentValues playerInfo = new ContentValues();

        playerInfo.put(KEY_USERNAME, newUser.getUsername());
        playerInfo.put(KEY_PASSWORD, newUser.getPassword());
        playerInfo.put(KEY_SCORE, newUser.getScore());
        playerInfo.put(KEY_RANK, newUser.getRank());


        // Insert the new row, returning the primary key value of the new row
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PLAYER_INFORMATION, null, playerInfo);
        db.close();
    }

    public Player findUsername(String username)
    {
        String query = "Select * FROM " + TABLE_PLAYER_INFORMATION + " WHERE " + KEY_USERNAME + " = \"" + username + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Player foundPlayer = new Player();
        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            foundPlayer.setId(Integer.parseInt(cursor.getString(0)));
            foundPlayer.setUsername(cursor.getString(1));
            foundPlayer.setPassword(cursor.getString(2));
            foundPlayer.setScore(cursor.getString(3));
            foundPlayer.setRank(cursor.getString(4));
            cursor.close();
        }
        else
        {
            foundPlayer = null;
        }
        cursor.close();
        db.close();
        return foundPlayer;
    }

    public Player findId(int Id)
    {
        String query = "Select * FROM " + TABLE_PLAYER_INFORMATION + " WHERE " + KEY_ID + " = \"" + Id + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Player foundPlayer = new Player();
        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            foundPlayer.setId(Integer.parseInt(cursor.getString(0)));
            foundPlayer.setUsername(cursor.getString(1));
            foundPlayer.setPassword(cursor.getString(2));
            foundPlayer.setScore(cursor.getString(3));
            foundPlayer.setRank(cursor.getString(4));
            cursor.close();
        }
        else
        {
            foundPlayer = null;
        }
        cursor.close();
        db.close();
        return foundPlayer;
    }

    public Player findScore(int Score)
    {
        String query = "Select * FROM " + TABLE_PLAYER_INFORMATION + " WHERE " + KEY_ID + " = \"" + Score + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Player foundPlayer = new Player();
        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            foundPlayer.setId(Integer.parseInt(cursor.getString(0)));
            foundPlayer.setUsername(cursor.getString(1));
            foundPlayer.setPassword(cursor.getString(2));
            foundPlayer.setScore(cursor.getString(3));
            foundPlayer.setRank(cursor.getString(4));
            cursor.close();
        }
        else
        {
            foundPlayer = null;
        }
        cursor.close();
        db.close();
        return foundPlayer;
    }

    public Player findRank(int Rank)
    {
        String query = "Select * FROM " + TABLE_PLAYER_INFORMATION + " WHERE " + KEY_ID + " = \"" + Rank + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Player foundPlayer = new Player();
        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            foundPlayer.setId(Integer.parseInt(cursor.getString(0)));
            foundPlayer.setUsername(cursor.getString(1));
            foundPlayer.setPassword(cursor.getString(2));
            foundPlayer.setScore(cursor.getString(3));
            foundPlayer.setRank(cursor.getString(4));
            cursor.close();
        }
        else
        {
            foundPlayer = null;
        }
        cursor.close();
        db.close();
        return foundPlayer;
    }



    public ArrayList<Player> getAllPlayers()
    {
        ArrayList<Player> player_list = new ArrayList<Player>();

        String query = "select * FROM " + TABLE_PLAYER_INFORMATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( query , null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            Player foundPlayer = new Player();

            foundPlayer.setId(Integer.parseInt(cursor.getString(0)));
            foundPlayer.setUsername(cursor.getString(1));
            foundPlayer.setPassword(cursor.getString(2));
            foundPlayer.setScore(cursor.getString(3));
            foundPlayer.setRank(cursor.getString(4));

            player_list.add(foundPlayer);//add users to list here
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return player_list;
    }





    public boolean deletePlayer(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PLAYER_INFORMATION, KEY_USERNAME + "=" + username, null) > 0;
    }



/*  USE FOR FINDING RANK FROM SCORE (?)
    public String prankConversion(String first, String second, String third, String fourth) {
        String[] nextStringArray = {first, second, third, fourth};
        String appendedPrank = "";


        for (int i = 0; i<nextStringArray.length;i++) {
            switch (nextStringArray[i]) {
                case "Active Hours":
                    appendedPrank += 1;
                    break;
                case "Cleanliness":
                    appendedPrank += 2;
                    break;
                case "Sound Levels":
                    appendedPrank += 3;
                    break;
                case "Sociability":
                    appendedPrank += 4;
                    break;
                default:
                    break;
            }
        }
        Log.d("appended prank", appendedPrank);
        return appendedPrank;
    }
    */



}



