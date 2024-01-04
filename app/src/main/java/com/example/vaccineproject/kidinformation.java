package com.example.vaccineproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class kidinformation extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5, t6, t7;
    DBHelper DB;
    Button updateprogress, viewprogress, logout;
    long value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_kidinformation);
        t1 = (TextView) findViewById(R.id.kididcheck);
        t2 = (TextView) findViewById(R.id.kidnamecheck);
        t3 = (TextView) findViewById(R.id.kidhospitalcheck);
        t4 = (TextView) findViewById(R.id.kidweightcheck);
        t5 = (TextView) findViewById(R.id.kidbgcheck);
        t6 = (TextView) findViewById(R.id.kidbdcheck);
        t7 = (TextView) findViewById(R.id.kidbtcheck);
        DB = new DBHelper(this);

        int uid=1;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getLong("id");
            t1.setText(String.valueOf(value));
        }

        Cursor t = DB.diskidsinfo((int)value);
        //t.moveToFirst();
        //StringBuffer buffer=new StringBuffer();
        t.moveToNext();

        t1.setText(""+t.getInt(0));
        t2.setText(""+t.getString(1));
        t3.setText(""+t.getString(2));
        t4.setText(""+t.getFloat(6));
        t5.setText(""+t.getString(5));
        t6.setText(""+t.getString(3));
        t7.setText(""+t.getString(4));

    updateprogress = findViewById(R.id.vaccineprogress);
        viewprogress = findViewById(R.id.viewvaccine);
        logout = findViewById(R.id.logout);


        updateprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(kidinformation.this, VaccinationList.class);
                i.putExtra("id", (int)value);//passing kid id of child selected/clicked
                startActivity(i);
            }
        });

        viewprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(kidinformation.this, ViewVaccination.class);
                i.putExtra("id", (int)value);//passing kid id of child selected/clicked
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
