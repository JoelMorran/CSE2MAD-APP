package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class CreateNewAccountActivity extends AppCompatActivity {
    private Button createAccount;
    private EditText name;
    private EditText username;
    private EditText email;
    private EditText password;

    private Button populateDb;
    private Spinner spinner;
    DBHandler db;
    //View focusView = name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);


        createAccount  = (Button) findViewById(R.id.createAccount);
        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

       /*createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CreateNewAccountActivity.this, LoginPageActivity.class);
                CreateNewAccountActivity.this.startActivity(myIntent);
            }
        });*/

        /*signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SignInSignUpActivity.this, CreateNewAccountActivity.class);

                SignInSignUpActivity.this.startActivity(myIntent);
            }
        });*/


        spinner = (Spinner) findViewById(R.id.spinner);
        populateDb = (Button) findViewById(R.id.populateDb);


        db = new DBHandler(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(getApplicationContext());
                db.addAccount(new Account(name.getText().toString(), username.getText().toString(),
                        email.getText().toString(), password.getText().toString()));

                Intent myIntent = new Intent(CreateNewAccountActivity.this, LoginPageActivity.class);
                CreateNewAccountActivity.this.startActivity(myIntent);


                username.getText().clear();
                email.getText().clear();
                password.getText().clear();
                name.getText().clear();
                updateSpinner();
                createlog();

            }
        });


        populateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(getApplicationContext());
                // Inserting students
                Log.d("Insert: ", "Inserting ..");
                db.addAccount(new Account("Mat", "Mat2", "test1@hotmail.com", "password1"));
                db.addAccount(new Account("Alex", "Alex1", "test1@hotmail.com.au", "password2"));
                db.addAccount(new Account("Sameer", "Sameerwashere", "test1@outlook.com", "password3"));
                db.addAccount(new Account("Shaz", "Shazisaspaz", "test1@outlook.com.au", "password4"));
                updateSpinner();
                createlog();
            }
        });


    }

    private void updateSpinner() {
        DBHandler db = new DBHandler(getApplicationContext());
        List<Account> accounts = db.getAllAccounts();
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, accounts);
        spinner.setAdapter(adapter);
        createlog();

    }

    private void createlog()
    {
        DBHandler db = new DBHandler(getApplicationContext());
        // Reading all students
        Log.d("Reading: ", "Reading all accounts..");
        List<Account> accounts = db.getAllAccounts();
        for (Account cn : accounts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Username: " + cn.getUsername()
                    + " ,Email: " + cn.getEmail()+ " ,Password: " + cn.getPassword();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_back:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubuttons, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) searchItem.getActionView();

        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }






}
