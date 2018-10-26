package com.example.kobiqoi_laptop.assignment;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;

public class LetsEat2Activity extends AppCompatActivity {

    private Button LetsEat;
    private String tbnum;
    private Button backdoor;
    DBHandler3 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_eat2);
        db = new DBHandler3(getApplicationContext());
        db.allDeleteOrder();
        db.close();

        LetsEat = (Button) findViewById(R.id.LetsEat);
        backdoor = (Button) findViewById(R.id.backdoor);





       LetsEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = getIntent().getExtras();
                if(extras != null) {

                    String tbnum = extras.getString("tbnumber");



                Intent myIntent = new Intent(LetsEat2Activity.this, MenuActivity.class);
               myIntent.putExtra("tbnumber", tbnum);
                LetsEat2Activity.this.startActivity(myIntent);
            }
            }
        });

        backdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LetsEat2Activity.this, LetsEatActivity.class);

                LetsEat2Activity.this.startActivity(myIntent);
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    int count = 1;
    @Override
    public void onBackPressed() {
        Log.d("working2", "working");

        ++count;



           if(count >= 10)
        {
            super.onBackPressed();
            Log.d("working", "working2");

        }
        else
        {
            Log.d("working", "wtf");
            ++count;
        }

    }
}
