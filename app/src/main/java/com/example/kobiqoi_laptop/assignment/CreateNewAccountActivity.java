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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()


        db = new DBHandler(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(getApplicationContext());


                boolean isFoundUsername = false;
                boolean isFoundEmail = false;

                List<Account> accounts = db.getAllAccounts();

                db.close();

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



                if(isEmailValid(email.getText().toString()) && !isFoundEmail && !isFoundUsername
                        &&  username.getText().toString().length() >= 1 && name.getText().toString().length() >= 1
                        && password.getText().toString().length() >= 1)
                {

                    db.addAccount(new Account(name.getText().toString(), username.getText().toString(),
                            email.getText().toString(), password.getText().toString()));

                    db.close();

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





    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

        db.close();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




}
