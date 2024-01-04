package com.example.vaccineproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class viewkids extends AppCompatActivity {
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_viewkids);
        int[] id = { R.id.txtListElement };
        ListView lst=(ListView)findViewById(R.id.list);
        DBHelper db = new DBHelper(this);
        SQLiteDatabase sqlDb =db.getReadableDatabase();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userid= extras.getInt("id");
        }

        Cursor c=db.kidsinfo(userid);
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.list_template,c,new String[] { "name"},id,0);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(viewkids.this,kidinformation.class);
                i.putExtra("id",id);//passing kid id of child selected/clicked
                startActivity(i);
            }
        });
    }

}