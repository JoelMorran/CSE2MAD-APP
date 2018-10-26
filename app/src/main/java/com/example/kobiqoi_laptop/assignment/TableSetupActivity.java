package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;




public class TableSetupActivity extends AppCompatActivity {

    private Button loadsetup;
    private Button newsetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_setup);

        loadsetup = (Button) findViewById(R.id.loadsetup);
        newsetup = (Button) findViewById(R.id.newsetup);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

       loadsetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TableSetupActivity.this, TableListActivity.class);

                TableSetupActivity.this.startActivity(myIntent);
            }
        });

        newsetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TableSetupActivity.this, CreateNewTableSetupActivity.class);

                TableSetupActivity.this.startActivity(myIntent);
            }
        });

    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
