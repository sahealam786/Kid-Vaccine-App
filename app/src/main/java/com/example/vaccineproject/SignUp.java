package com.example.vaccineproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    EditText NameEditText,PwdEditText,CoPwdEditText;
    Button RegistrationButton;
    TextView atoz, AtoZ, num, charcount;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        RegistrationButton = findViewById(R.id.register);
        NameEditText = findViewById(R.id.uname);
        PwdEditText = findViewById(R.id.pwd);
        CoPwdEditText = findViewById(R.id.ed1);
        atoz = findViewById(R.id.atoz);
        AtoZ = findViewById(R.id.AtoZ);
        num = findViewById(R.id.num);
        charcount = findViewById(R.id.charcount);

        String pwd = PwdEditText.getText().toString();
        DB = new DBHelper(this);

        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name = NameEditText.getText().toString();
                final String password = PwdEditText.getText().toString();
                final String confpwd = CoPwdEditText.getText().toString();
                if (Name.length() == 0 && password.length() == 0 && confpwd.length() == 0) {
                    NameEditText.requestFocus();
                    NameEditText.setError("FIELD CANNOT BE EMPTY");
                    PwdEditText.requestFocus();
                    PwdEditText.setError("FIELD CANNOT BE EMPTY");
                    CoPwdEditText.requestFocus();
                    CoPwdEditText.setError("FILED CANNOT BE EMPTY");
                } else if (!Name.matches("[a-zA-Z]+")) {
                    NameEditText.requestFocus();
                    NameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTERS");

                } else if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUp.this, "Please fill in all the information", Toast.LENGTH_SHORT).show();
                }
                else if(validatepass(password)==true)
                {
                    if(!confpwd.matches(password))
                    {
                        CoPwdEditText.requestFocus();
                        CoPwdEditText.setError("DOES NOT MATCH WITH PASSWORD");
                    }
                    else {
                        Boolean i = DB.insertUserData(Name, password);
                        if (i == true) {
                            Toast.makeText(SignUp.this, "Successfully data inserted!", Toast.LENGTH_SHORT).show();
                            int id = DB.returnid(Name, password);
                            Intent intent = new Intent(SignUp.this, HomePage.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "Unsuccessful insertion!", Toast.LENGTH_SHORT);
                        }
                    }
                }

            }

        });

    }

    public boolean validatepass(String password) {
        password = PwdEditText.getText().toString();
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        boolean small = false, large = false, num1 =  false, count = false;
        if (!lowercase.matcher(password).find()) {
            atoz.setTextColor(Color.RED);
        } else {
            atoz.setTextColor(Color.GREEN);
            small = true;
        }

        if (!uppercase.matcher(password).find()) {
            AtoZ.setTextColor(Color.RED);
        } else {
            AtoZ.setTextColor(Color.GREEN);
            large = true;
        }

        if (!digit.matcher(password).find()) {
            num.setTextColor(Color.RED);
        } else {
            num.setTextColor(Color.GREEN);
            num1 = true;
        }

        if (password.length() < 8) {
            charcount.setTextColor(Color.RED);
        } else {
            charcount.setTextColor(Color.GREEN);
            count = true;
        }
        if(small==true && large==true && num1==true && count==true)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}