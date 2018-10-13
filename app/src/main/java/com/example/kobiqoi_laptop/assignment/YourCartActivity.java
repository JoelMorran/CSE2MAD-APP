package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class YourCartActivity extends AppCompatActivity {
    private Spinner spinner;

    ListView listV;

    private ListAdapterOrders adapter;

    private ArrayList<Order> listItems;

    private Button emptycart;
    private Button checkout;
    DBHandler3 db;
    private TextView items;
    private TextView subtotal;
    private ImageButton helpbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_cart);
        spinner = (Spinner) findViewById(R.id.spinner3);
        updateSpinner();
        emptycart = (Button) findViewById(R.id.emptycart);
        checkout = (Button) findViewById(R.id.checkout);
        items = (TextView) findViewById(R.id.items);
        subtotal = (TextView) findViewById(R.id.subtotal);

        helpbtn = (ImageButton) findViewById(R.id.helpbtn);
        //signUp = (Button) findViewById(R.id.signUp);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()



        listV=(ListView)findViewById(R.id.listv);
        db = new DBHandler3(getApplicationContext());

        listItems =  db.getAllOrders();


        adapter=new ListAdapterOrders(getApplicationContext(), R.layout.list_layout_orders2,R.id.txtname,listItems);
        listV.setAdapter(adapter);

        ArrayList<Order> orders = db.getAllOrders();

        double total = 0;
        double t = 0;
        int count = 0;

        for (Order cn : orders)
        {
            ++count;
            Double tt = Double.parseDouble(cn.getPrice());
            t = tt;
            total = t + total;

        }
        String s = String.valueOf(total);
        String ss = String.valueOf(count);
        items.setText(ss);
        subtotal.setText(s);


        //listV.setOnItemClickListener(this);
    emptycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(YourCartActivity.this, CheckoutActivity.class);

                YourCartActivity.this.startActivity(myIntent);
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

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void updateSpinner() {
        DBHandler3 db = new DBHandler3(getApplicationContext());
        List<Order> orders = db.getAllOrders();
        ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, orders);
        spinner.setAdapter(adapter);
        //createlog();

    }

    private void updateSpinner2() {
        DBHandler db = new DBHandler(getApplicationContext());
        List<Account> accounts = db.getAllAccounts();
        ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, accounts);
        spinner.setAdapter(adapter);
        //createlog();

    }


    private void sendBroadcast() {
        db = new DBHandler3(getApplicationContext());
        ArrayList<Order> orders = db.getAllOrders();
        String s = "";
        for (Order cn : orders)
        {

            s = (cn.getTableid());


        }
        Intent intent = new Intent();
        intent.setAction("com.example.kobiqoi_laptop.assignment");
        intent.putExtra("Life_form", "_DROID_");
        intent.putExtra("tableid", "Table " + s + " needs assistance \n"  );
        Toast.makeText(this.getApplicationContext(),"HELOOOOOOOOOO", Toast.LENGTH_LONG);
        sendBroadcast(intent);
    }



}
