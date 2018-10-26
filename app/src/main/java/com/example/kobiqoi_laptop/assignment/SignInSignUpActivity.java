package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class SignInSignUpActivity extends AppCompatActivity {

    private Button signUp = null;
    private Button signIn = null;
    private Toolbar myToolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in_sign_up);
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);
        myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


       getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

        signIn.setOnClickListener(new View.OnClickListener() {
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
        });

    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

