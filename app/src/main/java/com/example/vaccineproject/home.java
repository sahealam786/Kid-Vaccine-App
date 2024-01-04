package com.example.vaccineproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class home extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;
    private Button dateButton, timeButton, register;
    EditText name, bp, wt;
    String bloodgroupselected,date,birthtime;
    int hour, minute,second;
    Boolean dateselected=false;
    Boolean timeselected=false;
    int userid;
    boolean isAllFieldsChecked = false;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        String[] BloodGroup = {"A+", "B+", "O+", "AB+", "AB-", "A-", "B-", "O-"};
        Spinner spin = (Spinner) findViewById(R.id.bloodgp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, BloodGroup);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        initDatePicker();
        DB = new DBHelper(this);
        dateButton = findViewById(R.id.bdate);
        timeButton = findViewById(R.id.btime);
        name = findViewById(R.id.kidname);
        bp = findViewById(R.id.birthplace);
        wt = findViewById(R.id.birthweight);
        register=findViewById(R.id.registerbt);
        Spinner spinner = (Spinner) findViewById(R.id.bloodgp);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userid= extras.getInt("id");
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    //Toast.makeText(home.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                    String kidsname = name.getText().toString();
                    String kidsbirthplace = bp.getText().toString();
                    double birthweight = Double.parseDouble(wt.getText().toString());

                    if (dateselected == false) {
                        date = getTodaysDate();
                    }
                    if (timeselected == false) {
                        birthtime = getCurrentTime();
                    }

                    //DB.insertkid("Radha","Gandhi","2021-09-09","12:07:00","A+",5.6,1);
                    boolean exist = DB.checkallkid(kidsname, userid);

                    if (exist == true) {
                        Toast.makeText(home.this, "Kid already exists!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(home.this, viewkids.class);
                        i.putExtra("id", userid);
                        startActivity(i);
                    } else {
                        Boolean success = DB.insertkid(kidsname, kidsbirthplace, date, birthtime, bloodgroupselected, birthweight, userid);
                        if (success) {
                            Toast.makeText(home.this, "kid successfully registered!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(home.this, "kid registration unsuccessful!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bloodgroupselected = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), bloodgroupselected, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) { }

    private boolean CheckAllFields() {
        if (TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(bp.getText().toString()) && TextUtils.isEmpty(wt.getText().toString()))
        {
            name.setError("This field is required");
            bp.setError("This field is required");
            wt.setError("This field is required");
            return false;
        }
        String checkname = "[a-zA-Z]+";
        if((!name.getText().toString().matches(checkname))){
            name.setError("Name cannot contain digits!");
            return false;
        }

        if(TextUtils.isEmpty(name.getText().toString())) {
            name.setError("This field is required");
            return false;
        }

        if (TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(bp.getText().toString()))
        {
            name.setError("This field is required");
            bp.setError("This field is required");
            return false;
        }

        if (TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(wt.getText().toString()))
        {
            name.setError("This field is required");
            wt.setError("This field is required");
            return false;
        }

        if (TextUtils.isEmpty(bp.getText().toString()) && TextUtils.isEmpty(wt.getText().toString()))
        {
            bp.setError("This field is required");
            wt.setError("This field is required");
            return false;
        }

        if(TextUtils.isEmpty(name.getText().toString())) {
            name.setError("This field is required");
            return false;
        }

        if (TextUtils.isEmpty(bp.getText().toString())) {
            bp.setError("This field is required");
            return false;
        }

        if (TextUtils.isEmpty(wt.getText().toString())) {
            wt.setError("This field is required");
            return false;
        }
        // after all validation return true.
        return true;

    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String getCurrentTime()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate =mdformat.format(calendar.getTime());
        return strDate;
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                dateselected=true;
                month = month + 1;
                date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this,dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day,int month,int year)
    {
        return year +"-"+month+"-"+day;
    }
    private String getMonthFormat(int month)
    {
        if(month==1)
            return "JAN";
        if(month==2)
            return "FEB";
        if(month==3)
            return "MAR";
        if(month==4)
            return "APR";
        if(month==5)
            return "MAY";
        if(month==6)
            return "JUN";
        if(month==7)
            return "JUL";
        if(month==8)
            return "AUG";
        if(month==9)
            return "SEP";
        if(month==10)
            return "OCT";
        if(month==11)
            return "NOV";
        if(month==12)
            return "DEC";
        //default should never happen
        return "JAN";
    }
    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                timeselected=true;
                hour=selectedHour;
                minute=selectedMinute;
                birthtime=String.format(Locale.getDefault(),"%02d:%02d:00",hour,minute);
                timeButton.setText(birthtime);
            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(this,onTimeSetListener,hour,minute,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}
