package com.example.vaccineproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewVaccination extends AppCompatActivity {

    Integer userid;
    ListView list;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_vaccination);


    list = findViewById(R.id.list);
    db = new DBHelper(this);
    Bundle extras = getIntent().getExtras();
        if (extras != null) {
        userid = extras.getInt("id");
    }

    ArrayList<String> thelist = new ArrayList<>();
    Cursor data = db.viewvaccine(userid);

        if(data.getCount()==0){
        Toast.makeText(ViewVaccination.this,"Please enter something",Toast.LENGTH_SHORT).show();
    }
        else {
        while(data.moveToNext()){
            thelist.add(data.getString(1));
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,thelist);
            list.setAdapter(arrayAdapter);
        }
    }

}
}