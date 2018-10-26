package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class ViewPastOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_order);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:

                Intent myIntent = new Intent(this, OrderHistoryActivity.class);

                this.startActivity(myIntent);

                return true;

            case R.id.action_cart:

                Intent myIntent2 = new Intent(this, YourCartActivity.class);

                this.startActivity(myIntent2);
                return true;

            case R.id.action_menu:

                Intent myIntent3 = new Intent(this, MenuActivity.class);

                this.startActivity(myIntent3);
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubuttons, menu);



        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
