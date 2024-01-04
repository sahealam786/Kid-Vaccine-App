package com.example.vaccineproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "logindb3.db";
    public DBHelper(Context context) {
        super(context, "logindb3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table Logindb(id integer primary key autoincrement,Username TEXT NOT NULL UNIQUE, Password TEXT NOT NULL)");
        db.execSQL("create table vaccinations(vid INTEGER primary key autoincrement,vname TEXT NOT NULL,age TEXT  NOT NULL, due_date TEXT NOT NULL)");
        db.execSQL("create table kid(_id INTEGER primary key autoincrement, name TEXT NOT NULL UNIQUE,hospitalname TEXT NOT NULL,birthdate TEXT NOT NULL,birthtime TEXT NOT NULL,bloodgroup TEXT NOT NULL,birthweight Real,uid integer,FOREIGN KEY(uid) REFERENCES Logindb)");
        db.execSQL("create table kid_vaccine(_id integer,vid integer,taken_date TEXT NOT NULL, FOREIGN KEY(vid) REFERENCES vaccinations ,FOREIGN KEY(_id) REFERENCES kid)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVer, int NewVer)
    {
        db.execSQL("drop table if exists Logindb");
        db.execSQL("drop table if exists kid");
        db.execSQL("drop table if exists kid_vaccine");
        db.execSQL("drop table if exists vaccinations");
    }

    public boolean insertUserData(String Username, String Password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Username",Username);
        values.put("Password",Password);

        long result = db.insert("Logindb", null, values);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkall(String Username, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Logindb where Username=? and Password=?", new String[]{Username, Password});
        if (cursor.getCount() > 0) {
            return true;
        }

        else
        {
            return false;        }
    }

    public int returnid(String Username, String Password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id from Logindb where Username=? and Password=?", new String[]{Username, Password});
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            int id=cursor.getInt(0);
            return id;
        }
        else
            return 0;
    }

    public Boolean insertkid(String name,String hospitalname,String birthdate,String birthtime,String bloodgroup,double birthweight,int uid){
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("hospitalname",hospitalname);
        values.put("birthdate",birthdate);
        values.put("birthtime",birthtime);
        values.put("bloodgroup",bloodgroup);
        values.put("birthweight",birthweight);
        values.put("uid",uid);

        long result = db1.insert("kid", null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean checkallkid(String name,int uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c3 = db.rawQuery("select * from kid where name=? and uid=?",new String[] {name,String.valueOf(uid)});
        if(c3.getCount()>0)
            return true;
        else
            return false;
    }


    public Cursor kidsinfo(int uid){
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor c3 = database.rawQuery("select * from kid where uid=?",new String[] {String.valueOf(uid)});
        return c3;
    }

    public Cursor diskidsinfo(int uid){
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor kid = database.rawQuery("select * from kid where _id=?",new String[] {String.valueOf(uid)});
        return kid;
    }

    public void insertvaccinedata(String vname, String age, String due_date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("vname",vname);
        values.put("age",age);
        values.put("due_date",due_date);
        db.insert("vaccinations", null, values);
    }

    public void insertVaccine(int id, int vid, String taken_date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id",id);
        values.put("vid",vid);
        //values.put("taken_date",taken_date);
        values.put("taken_date",taken_date);
        db.insert("kid_vaccine",null,values);
    }


    public Cursor viewvaccine(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c3 = db.rawQuery("select * from vaccinations where vid in(select vid from kid_vaccine where _id=?)",new String[] {String.valueOf(id)});
        return c3;

    }

    public Boolean deletevaccine(int vid, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from kid_vaccine where vid=? and _id=?",new String[]{String.valueOf(vid),String.valueOf(id)});
        if (cursor.getCount() > 0) {
            return true;
        }

        else
        {
            return false;        }
    }


}
