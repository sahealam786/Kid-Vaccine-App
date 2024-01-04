package com.example.vaccineproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button addChild,viewkid;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_page);
        addChild=findViewById(R.id.addchild);
        viewkid=findViewById(R.id.vkids);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userid= extras.getInt("id");
        }
        DBHelper db = new DBHelper(this);


        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this,home.class);
                i.putExtra("id",userid);
                startActivity(i);
            }
        });


        viewkid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this,viewkids.class);
                i.putExtra("id",userid);
                startActivity(i);
            }
        });

    }
}