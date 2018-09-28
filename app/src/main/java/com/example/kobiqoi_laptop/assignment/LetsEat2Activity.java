package com.example.kobiqoi_laptop.assignment;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LetsEat2Activity extends AppCompatActivity {

    private Button LetsEat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_eat2);

        LetsEat = (Button) findViewById(R.id.LetsEat);
        //signUp = (Button) findViewById(R.id.signUp);
       // Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        //setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        //getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

       LetsEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(LetsEat2Activity.this, MenuActivity.class);

                LetsEat2Activity.this.startActivity(myIntent);
            }
        });

        /*signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SignInSignUpActivity.this, CreateNewAccountActivity.class);

                SignInSignUpActivity.this.startActivity(myIntent);
            }
        });*/

    }

    /*@Override
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

*/
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



        //String tv = count;
        //Log.d("working2", );
        if(count >= 10)
        {
            super.onBackPressed();////helpsssssssssssssssssssssssssssssss
            Log.d("working", "working2");

        }
        else
        {
            Log.d("working", "wtf");
            ++count;
        }
       /* if (!shouldAllowBack()) {
            doSomething();
        } else {
            super.onBackPressed();
        }*/
    }
}
