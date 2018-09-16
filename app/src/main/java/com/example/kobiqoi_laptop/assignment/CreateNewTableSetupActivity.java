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
        spinner = (Spinner) findViewById(R.id.spinner);

        //signUp = (Button) findViewById(R.id.signUp);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);
        updateSpinner();
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
        public void onClick(View view) {
            DBHandler2 db = new DBHandler2(getApplicationContext());
            db.addTable(new Table(tablenumber.getText().toString(), tablesize.getText().toString()));

            Intent myIntent = new Intent(CreateNewTableSetupActivity.this, TableListActivity.class);
            CreateNewTableSetupActivity.this.startActivity(myIntent);


            tablenumber.getText().clear();
            tablesize.getText().clear();
            updateSpinner();
            createlog();
            tablenumber.setFocusable(true);
            tablenumber.requestFocus();

        }
    });


      /*  populateDb.setOnClickListener(new View.OnClickListener() {
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
    });*/


}

    private void updateSpinner() {
        DBHandler2 db = new DBHandler2(getApplicationContext());
        List<Table> tables = db.getAllTables();
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, tables);
        spinner.setAdapter(adapter);
        createlog();

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
