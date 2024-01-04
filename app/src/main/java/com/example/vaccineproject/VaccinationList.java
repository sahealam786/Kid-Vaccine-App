package com.example.vaccineproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class VaccinationList extends AppCompatActivity {

    private CheckBox vc1, vc2, vc3, vc4, vc5, vc6, vc7, vc8, vc9, vc10, vc11, vc12, vc13, vc14, vc15, vc16, vc17, vc18, vc19;
    EditText vc1tv1, vc2tv1, vc3tv1, vc4tv1, vc5tv1, vc6tv1, vc7tv1, vc8tv1, vc9tv1, vc10tv1, vc11tv1, vc12tv1, vc13tv1, vc14tv1, vc15tv1, vc16tv1, vc17tv1, vc18tv1, vc19tv1;
    DBHelper DB = new DBHelper(this);
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_vaccination_list);
        vc1 = findViewById(R.id.vacc1);
        vc2 = findViewById(R.id.vacc2);
        vc3 = findViewById(R.id.vacc3);
        vc4 = findViewById(R.id.vacc4);
        vc5 = findViewById(R.id.vacc5);
        vc6 = findViewById(R.id.vacc6);
        vc7 = findViewById(R.id.vacc7);
        vc8 = findViewById(R.id.vacc8);
        vc9 = findViewById(R.id.vacc9);
        vc10 = findViewById(R.id.vacc10);
        vc11 = findViewById(R.id.vacc11);
        vc12 = findViewById(R.id.vacc12);
        vc13 = findViewById(R.id.vacc13);
        vc14 = findViewById(R.id.vacc14);
        vc15 = findViewById(R.id.vacc15);
        vc16 = findViewById(R.id.vacc16);
        vc17 = findViewById(R.id.vacc17);
        vc18 = findViewById(R.id.vacc18);
        vc19 = findViewById(R.id.vacc19);
        vc1tv1 = findViewById(R.id.v1tv1);
        String tv1 = vc1tv1.getText().toString();
        vc2tv1 = findViewById(R.id.v2tv1);
        String tv2 = vc2tv1.getText().toString();
        vc3tv1 = findViewById(R.id.v3tv1);
        String tv3 = vc3tv1.getText().toString();
        vc4tv1 = findViewById(R.id.v4tv1);
        String tv4 = vc4tv1.getText().toString();
        vc5tv1 = findViewById(R.id.v5tv1);
        String tv5 = vc5tv1.getText().toString();
        vc6tv1 = findViewById(R.id.v6tv1);
        String tv6 = vc6tv1.getText().toString();
        vc7tv1 = findViewById(R.id.v7tv1);
        String tv7 = vc7tv1.getText().toString();
        vc8tv1 = findViewById(R.id.v8tv1);
        String tv8 = vc8tv1.getText().toString();
        vc9tv1 = findViewById(R.id.v9tv1);
        String tv9 = vc9tv1.getText().toString();
        vc10tv1 = findViewById(R.id.v10tv1);
        String tv10 = vc10tv1.getText().toString();
        vc11tv1 = findViewById(R.id.v11tv1);
        String tv11 = vc11tv1.getText().toString();
        vc12tv1 = findViewById(R.id.v12tv1);
        String tv12 = vc12tv1.getText().toString();
        vc13tv1 = findViewById(R.id.v13tv1);
        String tv13 = vc13tv1.getText().toString();
        vc14tv1 = findViewById(R.id.v14tv1);
        String tv14 = vc14tv1.getText().toString();
        vc15tv1 = findViewById(R.id.v15tv1);
        String tv15 = vc15tv1.getText().toString();
        vc16tv1 = findViewById(R.id.v16tv1);
        String tv16 = vc16tv1.getText().toString();
        vc17tv1 = findViewById(R.id.v17tv1);
        String tv17 = vc17tv1.getText().toString();
        vc18tv1 = findViewById(R.id.v18tv1);
        String tv18 = vc18tv1.getText().toString();
        vc19tv1 = findViewById(R.id.v19tv1);
        String tv19 = vc19tv1.getText().toString();

        DB.insertvaccinedata("BCG", "Birth","2_days");
        DB.insertvaccinedata("Hep-B_1", "Birth","2_days");
        DB.insertvaccinedata("Hep-B_2", "6_week","1_week");
        DB.insertvaccinedata("Rotavirus-1", "6_week","1_week");
        DB.insertvaccinedata("PCV-1", "6_week","1_week");
        DB.insertvaccinedata("Rotavirus-2", "10_week","2_week");
        DB.insertvaccinedata("PCV-2", "10_week","2_week");
        DB.insertvaccinedata("Rotavirus-3", "14_week","2_week");
        DB.insertvaccinedata("PCV-3", "14_week","2_week");
        DB.insertvaccinedata("OPV", "6_month","3_week");
        DB.insertvaccinedata("Hep-B_3", "6_month","3_week");
        DB.insertvaccinedata("Influenza_Vaccine-1", "6_month","3_week");
        DB.insertvaccinedata("Influenza_Vaccine-2", "7_month","3_week");
        DB.insertvaccinedata("MMR-1", "9_month","3_week");
        DB.insertvaccinedata("Typhoid","10_month","3_week");
        DB.insertvaccinedata("PCV_Booster", "15_month","4_week");
        DB.insertvaccinedata("Hib_Booster", "16-18_month","4_week");
        DB.insertvaccinedata("Typhoid_Booster", "2_year","5_week");
        DB.insertvaccinedata("HPV", "10-12_year","6_week");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userid = extras.getInt("id");
        }


            vc1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc1.isChecked()) {
                        DB.insertVaccine(userid, 1, tv1);
                        vc2.setChecked(true);
                    }
                }
            });
            vc2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc2.isChecked()) {
                        DB.insertVaccine(userid, 2, tv2);
                        vc2.setChecked(true);
                    }
                }
            });
            vc3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc3.isChecked()) {
                        DB.insertVaccine(userid, 3, tv3);
                        vc3.setChecked(true);
                    }

                }
            });
            vc4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc4.isChecked()) {
                        DB.insertVaccine(userid, 4, tv4);
                        vc4.setChecked(true);
                    }
                }
            });
            vc5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc5.isChecked()) {
                        DB.insertVaccine(userid, 5, tv5);
                        vc5.setChecked(true);
                    }
                }
            });
            vc6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc6.isChecked()) {
                        DB.insertVaccine(userid, 6, tv6);
                        vc6.setChecked(true);
                    }
                }
            });
            vc7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc7.isChecked()) {
                        DB.insertVaccine(userid, 7, tv7);
                        vc7.setChecked(true);
                    }
                }
            });
            vc8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc8.isChecked()) {
                        DB.insertVaccine(userid, 8, tv8);
                        vc8.setChecked(true);
                    }
                }
            });
            vc9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc9.isChecked()) {
                        DB.insertVaccine(userid, 9, tv9);
                        vc9.setChecked(true);
                    }
                }
            });
            vc10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc10.isChecked()) {
                        DB.insertVaccine(userid, 10, tv10);
                        vc10.setChecked(true);
                    }
                }
            });
            vc11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc11.isChecked()) {
                        DB.insertVaccine(userid, 11, tv11);
                        vc11.setChecked(true);
                    }
                }
            });
            vc12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc12.isChecked()) {
                        DB.insertVaccine(userid, 12, tv12);
                        vc12.setChecked(true);
                    }
                }
            });
            vc13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc13.isChecked()) {
                        DB.insertVaccine(userid, 13, tv13);
                        vc13.setChecked(true);
                    }
                }
            });
            vc14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc14.isChecked()) {
                        DB.insertVaccine(userid, 14, tv14);
                        vc14.setChecked(true);
                    }
                }
            });
            vc15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc15.isChecked()) {
                        DB.insertVaccine(userid, 15, tv15);
                        vc15.setChecked(true);
                    }
                }
            });
            vc16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc16.isChecked()) {
                        DB.insertVaccine(userid, 16, tv16);
                        vc16.setChecked(true);
                    }
                }
            });
            vc17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc17.isChecked()) {
                        DB.insertVaccine(userid, 17, tv17);
                        vc17.setChecked(true);
                    }
                }
            });
            vc18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc18.isChecked()) {
                        DB.insertVaccine(userid, 18, tv18);
                        vc18.setChecked(true);
                    }
                }
            });
            vc19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vc19.isChecked()) {
                        DB.insertVaccine(userid, 19, tv19);
                        vc19.setChecked(true);
                    }
                }
            });

        Boolean check1 = DB.deletevaccine(1,userid);
        {
            if(check1==true)
            {
                ((ViewGroup)vc1.getParent()).removeView(vc1);
                ((ViewGroup)vc1tv1.getParent()).removeView(vc1tv1);
            }
        }
        Boolean check2 = DB.deletevaccine(2,userid);
        {
            if(check2==true)
            {
                ((ViewGroup)vc2.getParent()).removeView(vc2);
                ((ViewGroup)vc2tv1.getParent()).removeView(vc2tv1);
            }
        }
        Boolean check3 = DB.deletevaccine(3,userid);
        {
            if(check3==true)
            {
                ((ViewGroup)vc3.getParent()).removeView(vc3);
                ((ViewGroup)vc3tv1.getParent()).removeView(vc3tv1);
            }
        }
        Boolean check4 = DB.deletevaccine(4,userid);
        {
            if(check4==true)
            {
                ((ViewGroup)vc4.getParent()).removeView(vc4);
                ((ViewGroup)vc4tv1.getParent()).removeView(vc4tv1);
            }
        }
        Boolean check5 = DB.deletevaccine(5,userid);
        {
            if(check5==true)
            {
                ((ViewGroup)vc5.getParent()).removeView(vc5);
                ((ViewGroup)vc5tv1.getParent()).removeView(vc5tv1);
            }
        }
        Boolean check6 = DB.deletevaccine(6,userid);
        {
            if(check6==true)
            {
                ((ViewGroup)vc6.getParent()).removeView(vc6);
                ((ViewGroup)vc6tv1.getParent()).removeView(vc6tv1);
            }
        }
        Boolean check7 = DB.deletevaccine(7,userid);
        {
            if(check7==true)
            {
                ((ViewGroup)vc7.getParent()).removeView(vc7);
                ((ViewGroup)vc7tv1.getParent()).removeView(vc7tv1);
            }
        }
        Boolean check8 = DB.deletevaccine(8,userid);
        {
            if(check8==true)
            {
                ((ViewGroup)vc8.getParent()).removeView(vc8);
                ((ViewGroup)vc8tv1.getParent()).removeView(vc8tv1);
            }
        }
        Boolean check9 = DB.deletevaccine(9,userid);
        {
            if(check9==true)
            {
                ((ViewGroup)vc9.getParent()).removeView(vc9);
                ((ViewGroup)vc9tv1.getParent()).removeView(vc9tv1);
            }
        }
        Boolean check10 = DB.deletevaccine(1,userid);
        {
            if(check10==true)
            {
                ((ViewGroup)vc10.getParent()).removeView(vc10);
                ((ViewGroup)vc10tv1.getParent()).removeView(vc10tv1);
            }
        }
        Boolean check11 = DB.deletevaccine(11,userid);
        {
            if(check11==true)
            {
                ((ViewGroup)vc11.getParent()).removeView(vc11);
                ((ViewGroup)vc11tv1.getParent()).removeView(vc11tv1);
            }
        }
        Boolean check12 = DB.deletevaccine(12,userid);
        {
            if (check12 == true) {
                ((ViewGroup) vc12.getParent()).removeView(vc12);
                ((ViewGroup) vc12tv1.getParent()).removeView(vc12tv1);
            }
        }
        Boolean check13 = DB.deletevaccine(13,userid);
        {
            if (check13 == true) {
                ((ViewGroup) vc13.getParent()).removeView(vc13);
                ((ViewGroup) vc13tv1.getParent()).removeView(vc13tv1);
            }
        }
        Boolean check14 = DB.deletevaccine(14,userid);
        {
            if (check14 == true) {
                ((ViewGroup) vc14.getParent()).removeView(vc14);
                ((ViewGroup) vc14tv1.getParent()).removeView(vc14tv1);
            }
        }
        Boolean check15 = DB.deletevaccine(15,userid);
        {
            if (check15 == true) {
                ((ViewGroup) vc15.getParent()).removeView(vc15);
                ((ViewGroup) vc15tv1.getParent()).removeView(vc15tv1);
            }
        }
        Boolean check16 = DB.deletevaccine(16,userid);
        {
            if (check16 == true) {
                ((ViewGroup) vc16.getParent()).removeView(vc16);
                ((ViewGroup) vc16tv1.getParent()).removeView(vc16tv1);
            }
        }
        Boolean check17 = DB.deletevaccine(17,userid);
        {
            if (check17 == true) {
                ((ViewGroup) vc17.getParent()).removeView(vc17);
                ((ViewGroup) vc17tv1.getParent()).removeView(vc17tv1);
            }
        }
        Boolean check18 = DB.deletevaccine(18,userid);
        {
            if (check18 == true) {
                ((ViewGroup) vc18.getParent()).removeView(vc18);
                ((ViewGroup) vc18tv1.getParent()).removeView(vc18tv1);
            }
        }
        Boolean check19 = DB.deletevaccine(19,userid);
        {
            if (check19 == true) {
                ((ViewGroup) vc19.getParent()).removeView(vc19);
                ((ViewGroup) vc19tv1.getParent()).removeView(vc19tv1);
            }
        }
        }
    }