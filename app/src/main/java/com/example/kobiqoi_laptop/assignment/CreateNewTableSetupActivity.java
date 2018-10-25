package com.example.kobiqoi_laptop.assignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        //spinner = (Spinner) findViewById(R.id.spinner);

        //signUp = (Button) findViewById(R.id.signUp);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);
        //updateSpinner();
        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

       /* signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SignInSignUpActivity.this, LoginPageActivity.class);

                SignInSignUpActivity.this.startActivity(myIntent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SignInSignUpActivity.this, CreateNewAccountActivity.class);

                SignInSignUpActivity.this.startActivity(myIntent);
            }
        });*/




   // populateDb = (Button) findViewById(R.id.populateDb);


    db = new DBHandler2(this);

        createtable.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            //Pattern.matches("[a-zA-Z]+", tablenumber.getText().toString()) == false

            /*if(!Pattern.matches("[a-zA-Z]+", tablenumber.getText().toString())
                    && tablenumber.getText().toString().length() <= 2 && tablesize.getText().toString().length() <= 2
                    && tablesize.getText().toString().length() >= 1 && !Pattern.matches("[a-zA-Z]+", tablesize.getText().toString())
                    && tablenumber.getText().toString().length() >= 1 && Integer.parseInt(tablesize.getText().toString()) <= 30
                    && Integer.parseInt(tablesize.getText().toString()) >= 1)*/


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
                //String check = tablesize.getText().toString();
                //int check2 = Integer.parseInt(check);

                    db.addTable(new Table(tablenumber.getText().toString(), tablesize.getText().toString()));

                db.close();

                    Intent myIntent = new Intent(CreateNewTableSetupActivity.this, TableListActivity.class);
                    CreateNewTableSetupActivity.this.startActivity(myIntent);


                    tablenumber.getText().clear();
                    tablesize.getText().clear();
                    //updateSpinner();
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

  /*  private void updateSpinner() {
        DBHandler2 db = new DBHandler2(getApplicationContext());
        List<Table> tables = db.getAllTables();
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, tables);
        spinner.setAdapter(adapter);
        createlog();

    }*/

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

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                // User chose the "Settings" item, show the app settings UI...
                //sendBroadcast();
                Intent myIntent = new Intent(this, OrderHistoryActivity.class);

                this.startActivity(myIntent);

                return true;

            case R.id.action_cart:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                // sendBroadcast();
                Intent myIntent2 = new Intent(this, YourCartActivity.class);

                this.startActivity(myIntent2);
                return true;

            case R.id.action_menu:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                // sendBroadcast();
                Intent myIntent3 = new Intent(this, MenuActivity.class);

                this.startActivity(myIntent3);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                //sendBroadcast();
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubuttons, menu);

        /*MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) searchItem.getActionView();*/

        // Configure the search info and add any event listeners...

     /*   return super.onCreateOptionsMenu(menu);
    }*/


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
