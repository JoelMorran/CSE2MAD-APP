package com.example.kobiqoi_laptop.assignment;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.List;
import java.util.regex.Pattern;

public class CreateNewTableSetupActivity extends AppCompatActivity {

    private Spinner spinner;
    DBHandler2 db;
    private Button createtable;
    private EditText tablenumber;
    private EditText tablesize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_table_setup);

        createtable = (Button) findViewById(R.id.createtable);
        tablenumber = (EditText) findViewById(R.id.tablenumber);
        tablesize = (EditText) findViewById(R.id.tablesize);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()



    db = new DBHandler2(this);

        createtable.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {



            boolean isFound = false;

            DBHandler2 db = new DBHandler2(getApplicationContext());
            List<Table> tables = db.getAllTables();

            db.close();

            for (Table cn : tables)
            {
                if(tablenumber.getText().toString().equals(cn.getTablenumber()) ) // ==   gives error but says do it?
                {
                    isFound = true;
                    break;
                }
            }

            if(!Pattern.matches("[a-zA-Z]+", tablenumber.getText().toString())
                    && tablenumber.getText().toString().length() <= 2 && tablenumber.getText().toString().length() >= 1
                    && !isFound

                   && tablesize.getText().toString().length() <= 2 && tablesize.getText().toString().length() >= 1
                    && !Pattern.matches("[a-zA-Z]+", tablesize.getText().toString())
                            && Integer.parseInt(tablesize.getText().toString()) <= 30
                            && Integer.parseInt(tablesize.getText().toString()) >= 1)
            {


                    db.addTable(new Table(tablenumber.getText().toString(), tablesize.getText().toString()));

                db.close();

                    Intent myIntent = new Intent(CreateNewTableSetupActivity.this, TableListActivity.class);
                    CreateNewTableSetupActivity.this.startActivity(myIntent);


                    tablenumber.getText().clear();
                    tablesize.getText().clear();

                    createlog();
                    tablenumber.setFocusable(true);
                    tablenumber.requestFocus();

            }

            else
                {  // if first box error  focus  & message
                    Animation shake = AnimationUtils.loadAnimation(CreateNewTableSetupActivity.this, R.anim.shake);
                    if(!(!Pattern.matches("[a-zA-Z]+", tablenumber.getText().toString())
                            && tablenumber.getText().toString().length() <= 2 && tablenumber.getText().toString().length() >= 1 && !isFound))
                    {  // if both error focus 1st and message both
                        if(!(tablesize.getText().toString().length() <= 2 && tablesize.getText().toString().length() >= 1
                                && !Pattern.matches("[a-zA-Z]+", tablesize.getText().toString())
                                && Integer.parseInt(tablesize.getText().toString()) <= 30
                                && Integer.parseInt(tablesize.getText().toString()) >= 1))
                        {
                            tablenumber.startAnimation(shake);
                            tablenumber.setFocusable(true);
                            tablenumber.requestFocus();
                            tablenumber.getText().clear();
                            tablenumber.setError("Please enter a valid table number that is not already in use");

                            tablesize.startAnimation(shake);
                            tablesize.getText().clear();
                            tablesize.setError("Please enter a valid table size between 1-30");
                        }  //first error only focus and message
                        else
                        {
                            tablenumber.startAnimation(shake);
                            tablenumber.setFocusable(true);
                            tablenumber.requestFocus();
                            tablenumber.getText().clear();
                            tablenumber.setError("Please enter a valid table number that is not already in use");

                        }

                    } // second only focus and message
                    else
                    {
                        tablesize.startAnimation(shake);
                        tablesize.setFocusable(true);
                        tablesize.requestFocus();
                        tablesize.getText().clear();
                        tablesize.setError("Please enter a valid table size between 1-30");
                    }


            }


        }
    });


}


    private void createlog()
    {
        DBHandler2 db = new DBHandler2(getApplicationContext());
        // Reading all students
        Log.d("Reading: ", "Reading all accounts..");
        List<Table> tables = db.getAllTables();
        for (Table cn : tables) {
            String log = "Id: "+cn.getID()+" ,Tablenumber: " + cn.getTablenumber() + " ,Tablesize: " + cn.getTablesize();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        db.close();
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
