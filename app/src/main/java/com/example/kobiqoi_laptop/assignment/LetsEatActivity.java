package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LetsEatActivity extends AppCompatActivity {

    private Button LetsEat = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lets_eat);
        LetsEat = (Button) findViewById(R.id.LetsEat);


        LetsEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LetsEatActivity.this, SignInSignUpActivity.class);

                LetsEatActivity.this.startActivity(myIntent);
                }
        });
    }
}

