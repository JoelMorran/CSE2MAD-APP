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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        //spinner = (Spinner) findViewById(R.id.spinner);
        //populateDb = (Button) findViewById(R.id.populateDb);

        //updateSpinner();


        db = new DBHandler(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(getApplicationContext());


                boolean isFoundUsername = false;
                boolean isFoundEmail = false;

                List<Account> accounts = db.getAllAccounts();

                for (Account cn : accounts)
                {
                    if(username.getText().toString().equals(cn.getUsername()) )
                    {
                        isFoundUsername = true;
                        break;
                    }
                }

                for (Account cn : accounts)
                {
                    if(email.getText().toString().equals(cn.getEmail()) )
                    {
                        isFoundEmail = true;
                        break;
                    }
                }

                //String email2 = email.getText().toString();

                //isEmailValid(email.getText().toString());

                if(isEmailValid(email.getText().toString()) && !isFoundEmail && !isFoundUsername
                        &&  username.getText().toString().length() >= 1 && name.getText().toString().length() >= 1
                        && password.getText().toString().length() >= 1)
                {
                    //String check = tablesize.getText().toString();
                    //int check2 = Integer.parseInt(check);

                    db.addAccount(new Account(name.getText().toString(), username.getText().toString(),
                            email.getText().toString(), password.getText().toString()));

                    Intent myIntent = new Intent(CreateNewAccountActivity.this, LoginPageActivity.class);
                    CreateNewAccountActivity.this.startActivity(myIntent);

                    username.getText().clear();
                    email.getText().clear();
                    password.getText().clear();
                    name.getText().clear();
                    //updateSpinner();
                    createlog();
                    name.setFocusable(true);
                    name.requestFocus();

                }
                else
                {  // if first box error  focus  & message
                    Animation shake = AnimationUtils.loadAnimation(CreateNewAccountActivity.this, R.anim.shake);
                    if(!(name.getText().toString().length() >= 1))
                    {  // if both error focus 1st and message both
                        if(!(!isFoundUsername &&  username.getText().toString().length() >= 1))
                        {
                            if(!(isEmailValid(email.getText().toString()) && !isFoundEmail)) {
                                if(!(password.getText().toString().length() >= 1)){
                                    //logic checked check
                                    name.startAnimation(shake);
                                    name.setFocusable(true);
                                    name.requestFocus();
                                    name.getText().clear();
                                    name.setError("Please enter a valid name > 0");

                                    if(isFoundUsername) {
                                        username.startAnimation(shake);
                                        username.getText().clear();
                                        username.setError("Please enter a valid username that is not already taken");
                                    }
                                    else{
                                        username.startAnimation(shake);
                                        username.getText().clear();
                                        username.setError("Please enter a valid username > 0");
                                    }

                                    if(isFoundEmail) {
                                        email.startAnimation(shake);
                                        email.getText().clear();
                                        email.setError("Please enter a valid email that is not already taken");
                                    }
                                    else
                                    {
                                        email.startAnimation(shake);
                                        email.getText().clear();
                                        email.setError("Please enter a valid email");
                                    }

                                    password.startAnimation(shake);
                                    password.getText().clear();
                                    password.setError("Please enter any password > 0");
                                }
                                else
                                {
                                    //logic checked check
                                    name.startAnimation(shake);
                                    name.setFocusable(true);
                                    name.requestFocus();
                                    name.getText().clear();
                                    name.setError("Please enter a valid name > 0");

                                    if(isFoundUsername) {
                                        username.startAnimation(shake);
                                        username.getText().clear();
                                        username.setError("Please enter a valid username that is not already taken");
                                    }
                                    else
                                    {
                                        username.startAnimation(shake);
                                        username.getText().clear();
                                        username.setError("Please enter a valid username > 0");
                                    }

                                    if(isFoundEmail) {
                                        email.startAnimation(shake);
                                        email.getText().clear();
                                        email.setError("Please enter a valid email that is not already taken");
                                    }
                                    else{
                                        email.startAnimation(shake);
                                        email.getText().clear();
                                        email.setError("Please enter a valid email > 0");
                                    }
                                }
                            }
                            else if(!(password.getText().toString().length() >= 1)){
                                //logic checked check
                                name.startAnimation(shake);
                                name.setFocusable(true);
                                name.requestFocus();
                                name.getText().clear();
                                name.setError("Please enter a valid name > 0");

                                if(isFoundUsername){
                                username.startAnimation(shake);
                                username.getText().clear();
                                username.setError("Please enter a valid username that is not already taken");
                                }
                                else{
                                    username.startAnimation(shake);
                                    username.getText().clear();
                                    username.setError("Please enter a valid username > 0");
                                }

                                password.startAnimation(shake);
                                password.getText().clear();
                                password.setError("Please enter any password > 0");

                            }
                            else{
                                //logic checked check
                                name.startAnimation(shake);
                                name.setFocusable(true);
                                name.requestFocus();
                                name.getText().clear();
                                name.setError("Please enter a valid name > 0");

                                if(isFoundUsername) {
                                    username.startAnimation(shake);
                                    username.getText().clear();
                                    username.setError("Please enter a valid username that is not already taken");
                                }
                                else{
                                    username.startAnimation(shake);
                                    username.getText().clear();
                                    username.setError("Please enter a valid username > 0");
                                }


                            }
                        }

                        //first error only focus and message
                        else if(!(isEmailValid(email.getText().toString()) && !isFoundEmail))
                        {

                            if(!(password.getText().toString().length() >= 1)){
                                //logic checked check
                                name.startAnimation(shake);
                                name.setFocusable(true);
                                name.requestFocus();
                                name.getText().clear();
                                name.setError("Please enter a valid name > 0");

                                if(isFoundEmail) {
                                    email.startAnimation(shake);
                                    email.getText().clear();
                                    email.setError("Please enter a valid email that is not already taken");
                                }
                                else{
                                    email.startAnimation(shake);
                                    email.getText().clear();
                                    email.setError("Please enter a valid email > 0");
                                }

                                password.startAnimation(shake);
                                password.getText().clear();
                                password.setError("Please enter any password > 0");
                            }
                            else
                            {
                                //logic checked check
                                name.startAnimation(shake);
                                name.setFocusable(true);
                                name.requestFocus();
                                name.getText().clear();
                                name.setError("Please enter a valid name > 0");

                                if(isFoundEmail) {
                                    email.startAnimation(shake);
                                    email.getText().clear();
                                    email.setError("Please enter a valid email that is not already taken");
                                }
                                else{
                                    email.startAnimation(shake);
                                    email.getText().clear();
                                    email.setError("Please enter a valid email > 0");
                                }
                            }

                        }
                        else if(!(password.getText().toString().length() >= 1))
                        {
                            //logic checked check
                            name.startAnimation(shake);
                            name.setFocusable(true);
                            name.requestFocus();
                            name.getText().clear();
                            name.setError("Please enter a valid name > 0");

                            password.startAnimation(shake);
                            password.getText().clear();
                            password.setError("Please enter any password > 0");

                        }
                        else
                        {
                            //logic checked check
                            name.startAnimation(shake);
                            name.setFocusable(true);
                            name.requestFocus();
                            name.getText().clear();
                            name.setError("Please enter a valid name > 0");
                        }

                    } // second only focus and message

                    else if(!(!isFoundUsername &&  username.getText().toString().length() >= 1))
                    {
                        if(!(isEmailValid(email.getText().toString()) && !isFoundEmail)) {
                            if(!(password.getText().toString().length() >= 1)){
                                //logic checked check

                                if(isFoundUsername){
                                username.startAnimation(shake);
                                username.setFocusable(true);
                                username.requestFocus();
                                username.getText().clear();
                                username.setError("Please enter a valid username that is not already taken");
                                }
                                else{
                                    username.startAnimation(shake);
                                    username.setFocusable(true);
                                    username.requestFocus();
                                    username.getText().clear();
                                    username.setError("Please enter a valid username > 0");
                                }

                                if(isFoundEmail) {
                                    email.startAnimation(shake);
                                    email.getText().clear();
                                    email.setError("Please enter a valid email that is not already taken");
                                }
                                else{
                                    email.startAnimation(shake);
                                    email.getText().clear();
                                    email.setError("Please enter a valid email > 0");
                                }

                                password.startAnimation(shake);
                                password.getText().clear();
                                password.setError("Please enter any password > 0");
                            }
                            else{
                                //logic checked check
                                if(isFoundUsername){
                                    username.startAnimation(shake);
                                    username.setFocusable(true);
                                    username.requestFocus();
                                    username.getText().clear();
                                    username.setError("Please enter a valid username that is not already taken");
                                }
                                else{
                                    username.startAnimation(shake);
                                    username.setFocusable(true);
                                    username.requestFocus();
                                    username.getText().clear();
                                    username.setError("Please enter a valid username > 0");
                                }

                                if(isFoundEmail) {
                                    email.startAnimation(shake);
                                    email.getText().clear();
                                    email.setError("Please enter a valid email that is not already taken");
                                }
                                else{
                                    email.startAnimation(shake);
                                    email.getText().clear();
                                    email.setError("Please enter a valid email > 0");
                                }

                            }
                        }
                        else if(!(password.getText().toString().length() >= 1)){
                            //logic checked check
                            if(isFoundUsername){
                                username.startAnimation(shake);
                                username.setFocusable(true);
                                username.requestFocus();
                                username.getText().clear();
                                username.setError("Please enter a valid username that is not already taken");
                            }
                            else{
                                username.startAnimation(shake);
                                username.setFocusable(true);
                                username.requestFocus();
                                username.getText().clear();
                                username.setError("Please enter a valid username > 0");
                            }

                            password.startAnimation(shake);
                            password.getText().clear();
                            password.setError("Please enter any password > 0");
                        }
                        else{
                            //logic checked check
                            if(isFoundUsername){
                                username.startAnimation(shake);
                                username.setFocusable(true);
                                username.requestFocus();
                                username.getText().clear();
                                username.setError("Please enter a valid username that is not already taken");
                            }
                            else{
                                username.startAnimation(shake);
                                username.setFocusable(true);
                                username.requestFocus();
                                username.getText().clear();
                                username.setError("Please enter a valid username > 0");
                            }

                        }
                    }
                    else if(!(isEmailValid(email.getText().toString()) && !isFoundEmail)) {
                            if(!(password.getText().toString().length() >= 1)){
                                //logic checked check
                                if(isFoundEmail) {
                                    email.startAnimation(shake);
                                    email.setFocusable(true);
                                    email.requestFocus();
                                    email.getText().clear();
                                    email.setError("Please enter a valid email that is not already taken");
                                }
                                else{
                                    email.startAnimation(shake);
                                    email.setFocusable(true);
                                    email.requestFocus();
                                    email.getText().clear();
                                    email.setError("Please enter a valid email");
                                }

                                password.startAnimation(shake);
                                password.getText().clear();
                                password.setError("Please enter any password > 0");
                            }
                            else
                            {
                                //logic checked check
                                if(isFoundEmail) {
                                    email.startAnimation(shake);
                                    email.setFocusable(true);
                                    email.requestFocus();
                                    email.getText().clear();
                                    email.setError("Please enter a valid email that is not already taken");
                                }
                                else{
                                    email.startAnimation(shake);
                                    email.setFocusable(true);
                                    email.requestFocus();
                                    email.getText().clear();
                                    email.setError("Please enter a valid email");
                                }
                            }
                    }
                    else
                    {
                        //logic checked check
                        password.startAnimation(shake);
                        password.setFocusable(true);
                        password.requestFocus();
                        password.getText().clear();
                        password.setError("Please enter a valid password > 0");
                    }


                }

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
                //updateSpinner();
                createlog();
            }
        });


    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

   /* private void updateSpinner() {
        DBHandler db = new DBHandler(getApplicationContext());
        List<Account> accounts = db.getAllAccounts();
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, accounts);
        spinner.setAdapter(adapter);
        //createlog();

    }*/

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

    /*@Override
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

    /*    return super.onCreateOptionsMenu(menu);
    }*/


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }






}
