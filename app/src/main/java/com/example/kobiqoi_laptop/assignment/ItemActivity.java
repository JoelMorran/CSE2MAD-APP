package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;


public class ItemActivity extends AppCompatActivity {

    private TextView name;
    private TextView price;
    private TextView glutenfree;
    private TextView description;
    private TextView amount;

    private Bitmap img3;
    private ImageButton item1Button;
    private String II;
    private Bitmap img;
    private ImageButton add;
    private ImageButton remove;
    private Button addtoorder;
    DBHandler3 db;
    private String cost2;
    private EditText addnote2;
    private ImageView imgbtn;
    private ImageButton helpbtn;
    private String tableid = "0";
    private String tbnum;

    private String xz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        name = (TextView)findViewById(R.id.name);
        price = (TextView)findViewById(R.id.price);
        glutenfree = (TextView)findViewById(R.id.gf);
        amount = (TextView)findViewById(R.id.amount);
        description = (TextView)findViewById(R.id.description);

        add = (ImageButton)findViewById(R.id.add);
        remove = (ImageButton)findViewById(R.id.remove);
        addtoorder = (Button)findViewById(R.id.addtoorder);
        addnote2 = (EditText)findViewById(R.id.addnote2);
        imgbtn = (ImageView)findViewById(R.id.imgbtn);


        helpbtn = (ImageButton) findViewById(R.id.helpbtn);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        Bundle extras = getIntent().getExtras();

        if(extras != null) {


            String name3 = extras.getString("name");
            name.setText(name3);

            String price3 = extras.getString("price");
            price.setText(price3);

            String glutenfree3 = extras.getString("glutenfree");
            if(glutenfree3.equals("true"))
            {
                glutenfree.setText("GF");
            }
            else
            {
                glutenfree.setText("NON GF");
            }


            String description2 = extras.getString("description");
            description.setText(description2);


            String II = extras.getString("img_src");
            new RetriveImg().execute(II);

            String tbnum = extras.getString("tbnumber");


        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String one = String.valueOf(amount.getText());
                int one2 = Integer.parseInt(one);
                ++one2;
                one = String.valueOf(one2);
                amount.setText(one);

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String one = String.valueOf(amount.getText());
                int one2 = Integer.parseInt(one);
                if(one2 >= 2) {
                    --one2;
                }
                one = String.valueOf(one2);
                amount.setText(one);

            }
        });



        db = new DBHandler3(this);

        addtoorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {
                Bundle extras = getIntent().getExtras();

                if (extras != null) {

                    String name3 = extras.getString("name");

                    String price3 = extras.getString("price");


                    String glutenfree3 = extras.getString("glutenfree");
                    if (glutenfree3.equals("true")) {
                        glutenfree3 = "GF";
                    } else {
                        glutenfree3 = "NON GF";
                    }
                    String Extra = extras.getString("extra");


                    String amountCheck = String.valueOf(amount.getText());
                    int amountCheck2 = Integer.parseInt(amountCheck);

                    if (amountCheck2 >= 2) {
                        String total = String.valueOf(price3);
                        double total2 = Double.parseDouble(total);

                        String mult = String.valueOf(amount.getText());
                        Double mult2 = Double.parseDouble(mult);

                        Double cost = mult2 * total2;
                        cost2 = Double.toString(cost);

                    }

                    String description2 = extras.getString("description");

                    tbnum = extras.getString("tbnumber");




                db.addOrder(new Order(name3, description2, amount.getText().toString(), addnote2.getText().toString(),
                               price3, cost2, tbnum));

                    db.close();
            }

                    Intent myIntent = new Intent(ItemActivity.this, YourCartActivity.class);

                    ItemActivity.this.startActivity(myIntent);


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

    public class RetriveImg extends AsyncTask<String, Void, Integer> {
        private Exception exception;

        @Override
        protected Integer doInBackground(String... urlStrs) {
            try {
                java.net.URL url = new java.net.URL(urlStrs[0]);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                img = BitmapFactory.decodeStream(input);
                int a = 0;
                return new Integer(0);
            } catch (Exception e) {
                this.exception = e;
                return new Integer(-1);
            }
        }
        protected void onPostExecute(Integer res) {

            imgbtn.setImageBitmap(img);
        }
    }


    private void sendBroadcast() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            tbnum = extras.getString("tbnumber");
        }
        Intent intent = new Intent();
        intent.setAction("com.example.kobiqoi_laptop.assignment");
        intent.putExtra("Life_form", "Table " + tbnum + " needs assistance \n");
        intent.putExtra("tableid", "Table " + tbnum + " needs assistance \n"  );
        Toast.makeText(this.getApplicationContext(),"HELOOOOOOOOOO", Toast.LENGTH_LONG);
        sendBroadcast(intent);
    }


}

