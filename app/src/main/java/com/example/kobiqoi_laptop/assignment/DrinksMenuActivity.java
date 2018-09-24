package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class DrinksMenuActivity extends AppCompatActivity {
    private Button alcohol;
    private Button softdrinks;
    private Button water;
    private Button teacoffee;
    private Button other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_menu);

        alcohol = (Button) findViewById(R.id.alcohol);
        softdrinks = (Button) findViewById(R.id.softdrinks);
        water = (Button) findViewById(R.id.water);
        teacoffee = (Button) findViewById(R.id.teacoffee);
        other = (Button) findViewById(R.id.other);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()



        alcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent myIntent = new Intent(DrinksMenuActivity.this, AlcoholMenuActivity.class);


                DrinksMenuActivity.this.startActivity(myIntent);
            }
        });

        softdrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(DrinksMenuActivity.this, SoftDrinksMenuActivity.class);

                DrinksMenuActivity.this.startActivity(myIntent);
            }
        });

        /*water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(DrinksMenuActivity.this, .class);

                DrinksMenuActivity.this.startActivity(myIntent);
            }
        });*/

        teacoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(DrinksMenuActivity.this, TeaCoffeeActivity.class);

                DrinksMenuActivity.this.startActivity(myIntent);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(DrinksMenuActivity.this, OtherDrinksMenuActivity.class);

                DrinksMenuActivity.this.startActivity(myIntent);
            }
        });


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
