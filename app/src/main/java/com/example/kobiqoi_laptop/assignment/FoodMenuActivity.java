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

import java.util.ArrayList;

public class FoodMenuActivity extends AppCompatActivity {

    private Button entree;
    private Button mains;
    private Button dessert;
    private Button other;
    private ImageButton helpbtn;
    private String tableid = "0";
    private String tbnum;


    private String xz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        entree = (Button) findViewById(R.id.entree);
        mains = (Button) findViewById(R.id.mains);
        dessert = (Button) findViewById(R.id.dessert);
        other = (Button) findViewById(R.id.other);


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


            entree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(FoodMenuActivity.this, EntreeMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    FoodMenuActivity.this.startActivity(myIntent);
                }
            });

            mains.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(FoodMenuActivity.this, MainsMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    FoodMenuActivity.this.startActivity(myIntent);
                }
            });

            dessert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(FoodMenuActivity.this, DessertMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    FoodMenuActivity.this.startActivity(myIntent);
                }
            });

            other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(FoodMenuActivity.this, OtherDrinksMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    FoodMenuActivity.this.startActivity(myIntent);
                }
            });
        }

        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.d("","help");
                sendBroadcast();
            }
        });

    }

    @Override
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
                tbid();
                myIntent3.putExtra("tbnumber", xz);
                this.startActivity(myIntent3);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                //sendBroadcast();
                return super.onOptionsItemSelected(item);

        }
    }

    private void tbid() {
        DBHandler3 db;
        db = new DBHandler3(getApplicationContext());
        ArrayList<Order> orders = db.getAllOrders();

        db.close();

        for (Order cn : orders)
        {

            xz = (cn.getTableid());


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
        intent.putExtra("Life_form", "Table " + tableid + " needs assistance \n");
        intent.putExtra("tableid", "Table " + tableid + " needs assistance \n"  );
        Toast.makeText(this.getApplicationContext(),"HELOOOOOOOOOO", Toast.LENGTH_LONG);
        sendBroadcast(intent);
    }
}
