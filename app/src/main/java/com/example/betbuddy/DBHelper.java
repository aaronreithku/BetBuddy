package com.example.betbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "BetData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(sportsbook TEXT, team1 TEXT, team2 TEXT, betType TEXT, odds TEXT, amountBet TEXT, amountWon TEXT, won TEXT, id TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertUserdata(String sportsbook, String team1, String team2, String betType, String odds, String amountBet, String amountWon, String won, String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sportsbook", sportsbook);
        contentValues.put("team1", team1);
        contentValues.put("team2", team2);
        contentValues.put("betType", betType);
        contentValues.put("odds", odds);
        contentValues.put("amountBet", amountBet);
        contentValues.put("amountWon", amountWon);
        contentValues.put("won", won);
        contentValues.put("id", id);
        long result = DB.insert("Userdetails", null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updateUserdata(String sportsbook, String team1, String team2, String betType, String odds, String amountBet, String amountWon, String won, String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sportsbook", sportsbook);
        contentValues.put("team1", team1);
        contentValues.put("team2", team2);
        contentValues.put("betType", betType);
        contentValues.put("odds", odds);
        contentValues.put("amountBet", amountBet);
        contentValues.put("amountWon", amountWon);
        contentValues.put("won", won);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where id = ?", new String[] {id});
        if(cursor.getCount()>0) {

            long result = DB.update("Userdetails", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Boolean deleteUserdata(String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where id = ?", new String[] {id});
        if(cursor.getCount()>0) {

            long result = DB.delete("Userdetails", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Cursor getUserdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
