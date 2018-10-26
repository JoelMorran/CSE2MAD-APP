package com.example.kobiqoi_laptop.assignment;


import android.content.Intent;
import android.os.Handler;
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
    int progressStatus = 0;

    private Handler handler = new Handler();


    private String xz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_cart);


        emptycart = (Button) findViewById(R.id.emptycart);
        checkout = (Button) findViewById(R.id.checkout);
        items = (TextView) findViewById(R.id.items);
        subtotal = (TextView) findViewById(R.id.subtotal);

        helpbtn = (ImageButton) findViewById(R.id.helpbtn);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()
        Bundle extras = getIntent().getExtras();

        if(extras != null) {

            xz = extras.getString("tbnumber");
        }


        listV=(ListView)findViewById(R.id.listv);
        db = new DBHandler3(getApplicationContext());

        listItems =  db.getAllOrders();

        db.close();


        adapter=new ListAdapterOrders(getApplicationContext(), R.layout.list_layout_orders2,R.id.txtname,listItems);
        listV.setAdapter(adapter);

        ArrayList<Order> orders = db.getAllOrders();

        db.close();

        double total = 0;
        double t = 0;
        int count = 0;

        for (Order cn : orders)
        {
            ++count;
            double tt = Double.parseDouble(cn.getPrice());
            double ttt = Double.parseDouble(cn.getAmount());
            t = tt * ttt;
            total = t + total;

        }

      String s = String.valueOf(total);
         String ss = String.valueOf(count);
        items.setText(ss);
        subtotal.setText(s);



    emptycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tbid();

                db.allDeleteOrder();


                listItems =  db.getAllOrders();
                adapter=new ListAdapterOrders(getApplicationContext(), R.layout.list_layout_orders2,R.id.txtname,listItems);
                listV.setAdapter(adapter);

                db.close();
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


                sendBroadcast();
            }
        });

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 1023) {
                    progressStatus += 1;

                    handler.post(new Runnable() {
                        public void run() {


                            ArrayList<Order> orders = db.getAllOrders();

                            db.close();
                            int tot = 0;
                            double total = 0;
                            double t = 0;
                            int count = 0;

                            for (Order cn : orders) {
                                ++count;
                                double tt = Double.parseDouble(cn.getPrice());
                                double ttt = Double.parseDouble(cn.getAmount());
                                t = tt * ttt;
                                total = t + total;

                                int tot2 = Integer.parseInt(cn.getAmount());
                                tot = tot + tot2;

                            }
                            String s = String.valueOf(total);
                            String ss = String.valueOf(tot);
                            items.setText(ss);
                            subtotal.setText(s);

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(800); ///900000
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();



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
        intent.putExtra("Life_form", "Table " + s + " needs assistance \n");
        intent.putExtra("tableid", "Table " + s + " needs assistance \n"  );
        Toast.makeText(this.getApplicationContext(),"HELOOOOOOOOOO", Toast.LENGTH_LONG);
        sendBroadcast(intent);
    }



}
