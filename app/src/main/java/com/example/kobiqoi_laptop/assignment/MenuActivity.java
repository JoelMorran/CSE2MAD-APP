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
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button drinks;
    private Button food;
    private Button specials;
    private Button kids;
    private ImageButton helpbtn;
    private String tableid = "0";
    private String tbnum;
    private String tbnum2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        drinks = (Button) findViewById(R.id.drinks);
        food= (Button) findViewById(R.id.food);
        kids = (Button) findViewById(R.id.kids);
        specials = (Button) findViewById(R.id.specials);


        helpbtn = (ImageButton) findViewById(R.id.helpbtn);



        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()
        Bundle extras = getIntent().getExtras();

        if(extras != null) {

            tbnum = extras.getString("tbnumber");
            tbnum2 = extras.getString("tbnumber");

            drinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MenuActivity.this, DrinksMenuActivity.class);
                   myIntent.putExtra("tbnumber", tbnum);
                    MenuActivity.this.startActivity(myIntent);
                }
            });

            food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MenuActivity.this, FoodMenuActivity.class);
                   myIntent.putExtra("tbnumber", tbnum);
                    MenuActivity.this.startActivity(myIntent);
                }
            });

            specials.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MenuActivity.this, SpecialsMenuActivity.class);
                   myIntent.putExtra("tbnumber", tbnum);
                    MenuActivity.this.startActivity(myIntent);
                }
            });

            kids.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MenuActivity.this, KidsMenuActivity.class);
                   myIntent.putExtra("tbnumber", tbnum);
                    MenuActivity.this.startActivity(myIntent);
                }
            });

            helpbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Log.d("","help");
                    sendBroadcast();
                }
            });

        }

/*        if(!(tbnum2.equals("")))
        {
            tbnum = tbnum2;


            drinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MenuActivity.this, DrinksMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    MenuActivity.this.startActivity(myIntent);
                }
            });

            food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MenuActivity.this, FoodMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    MenuActivity.this.startActivity(myIntent);
                }
            });

            specials.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MenuActivity.this, SpecialsMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    MenuActivity.this.startActivity(myIntent);
                }
            });

            kids.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(MenuActivity.this, KidsMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    MenuActivity.this.startActivity(myIntent);
                }
            });

            helpbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Log.d("","help");
                    sendBroadcast();
                }
            });
        }*/


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                // User chose the "Settings" item, show the app settings UI...
                //sendBroadcast();
                Intent myIntent = new Intent(this, OrderHistoryActivity.class);
                myIntent.putExtra("tbnumber", tbnum);
                this.startActivity(myIntent);

                return true;

            case R.id.action_cart:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                // sendBroadcast();
                Intent myIntent2 = new Intent(this, YourCartActivity.class);
                myIntent2.putExtra("tbnumber", tbnum);
                this.startActivity(myIntent2);
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

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void sendBroadcast() {
        Intent intent = new Intent();
        intent.setAction("com.example.kobiqoi_laptop.assignment");
        intent.putExtra("Life_form", "Table " + tbnum + " needs assistance \n");
        intent.putExtra("tableid", "Table " + tbnum + " needs assistance \n"  );
        Toast.makeText(this.getApplicationContext(),"HELOOOOOOOOOO", Toast.LENGTH_LONG);
        sendBroadcast(intent);
    }
}
