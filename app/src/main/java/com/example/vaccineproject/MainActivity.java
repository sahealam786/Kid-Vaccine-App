package com.example.vaccineproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Name, Pwd;
    Button LogButton,SignupButton;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Name= findViewById(R.id.LogName);
        Pwd= findViewById(R.id.LogPwd);
        LogButton= findViewById(R.id.LogButton);
        SignupButton=findViewById(R.id.SignupButton);
        DB = new DBHelper(this);
        DB.insertUserData("Sahealam","Sahe123");
        DB.insertUserData("Sultan","Sahe@123");

        LogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String LogName = Name.getText().toString();
                String LogPwd = Pwd.getText().toString();

                if(TextUtils.isEmpty(LogName) || TextUtils.isEmpty(LogPwd))
                    Toast.makeText(MainActivity.this, "Please fill in all the information", Toast.LENGTH_SHORT).show();
                else
                {
                    Boolean checkuser = DB.checkall(LogName, LogPwd);
                    if(checkuser == true)
                    {
                        int id=DB.returnid(LogName,LogPwd);
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this,HomePage.class);
                        i.putExtra("id",id);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Wrong credentials. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}