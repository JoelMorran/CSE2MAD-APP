package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CheckoutActivity extends AppCompatActivity {

    private Button placeorder;
    private ImageButton helpbtn;
    private Spinner spinner;

    ListView listV;

    private ListAdapterCheckout adapter;

    private ArrayList<Order> listItems;

    private Button emptycart;
    private Button checkout;
    DBHandler3 db;
    private TextView items;
    private TextView subtotal;
    private TextView total2;
    private TextView gst;


    private String xz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkout = (Button) findViewById(R.id.checkout);
        items = (TextView) findViewById(R.id.items);
        subtotal = (TextView) findViewById(R.id.subtotal);
        total2 = (TextView) findViewById(R.id.total2);
        gst = (TextView) findViewById(R.id.GST);
        placeorder = (Button) findViewById(R.id.placeorder);

        helpbtn = (ImageButton) findViewById(R.id.helpbtn);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()


        listV = (ListView) findViewById(R.id.listV);
        db = new DBHandler3(getApplicationContext());

        listItems = db.getAllOrders();

        db.close();


        adapter = new ListAdapterCheckout(getApplicationContext(), R.layout.list_layout_checkout, R.id.txtname, listItems);
        listV.setAdapter(adapter);

        ArrayList<Order> orders = db.getAllOrders();

        db.close();

        double total = 0;
        double t = 0;
        int tot = 0;
        int count = 0;

        for (Order cn : orders) {
            //++count;

            double tt = Double.parseDouble(cn.getPrice());
            double ttt = Double.parseDouble(cn.getAmount());
            t = tt * ttt;
            total = t + total;
            int tot2 = Integer.parseInt(cn.getAmount());
             tot = tot + tot2;


        }
        double gsts = 10 * (total / 100);
        double subt = total - (10 * (total / 100));
        double x = total;
        String s = String.valueOf(x);
        String k = String.valueOf(x);
        String ss = String.valueOf(tot);
        String sss = String.valueOf(subt);
        String ssss = String.valueOf(gsts);
        items.setText("Quantity: " + ss);
        subtotal.setText("Subtotal: " + sss);

        gst.setText("GST: " + ssss);
        total2.setText("Total: " + k);

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CheckoutActivity.this, CheckoutPaymentMethodActivity.class);

                CheckoutActivity.this.startActivity(myIntent);
            }
        });


        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendBroadcast();
            }
        });



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
                tbid();
                myIntent3.putExtra("tbnumber", xz);
                this.startActivity(myIntent3);
                return true;

            default:

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

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void sendBroadcast() {
        db = new DBHandler3(getApplicationContext());
        ArrayList<Order> orders = db.getAllOrders();

        db.close();
        String s = "";
        for (Order cn : orders)
        {

            s = (cn.getTableid());


        }
        Intent intent = new Intent();
        intent.setAction("com.example.kobiqoi_laptop.assignment");
        intent.putExtra("Life_form", "Table " + s + " needs assistance \n" );
        intent.putExtra("tableid", "Table " + s + " needs assistance \n"  );
        Toast.makeText(this.getApplicationContext(),"HELOOOOOOOOOO", Toast.LENGTH_LONG);
        sendBroadcast(intent);
    }

}