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

import android.widget.Button;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;


public class DrinksMenuActivity extends AppCompatActivity{
    private Button alcohol;
    private Button softdrinks;
    private Button water;
    private Button teacoffee;
    private Button other;


    private TextView name;
    private TextView price;
    private TextView description;

    private ArrayList<JSONObject> listItems;
    private JSONArray sendarr;
    private JSONObject jsonObj;


    private String name2;
    private String price2;
    private String description2;
    private Exception exception;
    private ImageButton helpbtn;
    private String tableid = "0";
    private String tbnum;


    private String xz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_menu);

        alcohol = (Button) findViewById(R.id.alcohol);
        softdrinks = (Button) findViewById(R.id.softdrinks);
        water = (Button) findViewById(R.id.water);
        teacoffee = (Button) findViewById(R.id.teacoffee);
        other = (Button) findViewById(R.id.other);


        helpbtn = (ImageButton) findViewById(R.id.helpbtn);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()


        new DrinksMenuActivity.RetrieveMenuTask().execute("http://homepage.cs.latrobe.edu.au/jamorran/menu.json");


               Bundle extras = getIntent().getExtras();
        if(extras != null) {

            tbnum = extras.getString("tbnumber");

            alcohol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(DrinksMenuActivity.this, AlcoholMenuActivity.class);

                    myIntent.putExtra("tbnumber", tbnum);
                    DrinksMenuActivity.this.startActivity(myIntent);
                }
            });

            softdrinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(DrinksMenuActivity.this, SoftDrinksMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    DrinksMenuActivity.this.startActivity(myIntent);
                }
            });

            teacoffee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(DrinksMenuActivity.this, TeaCoffeeActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    DrinksMenuActivity.this.startActivity(myIntent);
                }
            });

            other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(DrinksMenuActivity.this, OtherDrinksMenuActivity.class);
                    myIntent.putExtra("tbnumber", tbnum);
                    DrinksMenuActivity.this.startActivity(myIntent);
                }
            });

            water.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();

                    intent.setClass(DrinksMenuActivity.this, ItemActivity.class);


                    try {
                        sendarr = jsonObj.getJSONArray("water");
                        String name3 = sendarr.getJSONObject(0).get("name").toString();
                        intent.putExtra("name", name3);

                        String price3 = sendarr.getJSONObject(0).get("price").toString();
                        intent.putExtra("price", price3);

                        String glutenfree3 = sendarr.getJSONObject(0).get("glutenfree").toString();
                        intent.putExtra("glutenfree", glutenfree3);

                        String description3 = sendarr.getJSONObject(0).get("description").toString();
                        intent.putExtra("description", description3);


                        String img3 = sendarr.getJSONObject(0).get("img_src").toString();

                        intent.putExtra("img_src", img3);

                        intent.putExtra("tbnumber", tbnum);

                    } catch (Exception e) {

                    }

                    startActivity(intent);

                }
            });

        }

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

    class RetrieveMenuTask extends AsyncTask<String, Void, Integer> {
        private Exception exception;
        private Bitmap img;

        @Override
        protected Integer doInBackground(String... urlStrs) {
            try {

                java.net.URL url = new java.net.URL(urlStrs[0]);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                jsonObj = new JSONObject(buffer.toString());
                JSONArray arr = jsonObj.getJSONArray("water");
                sendarr = jsonObj.getJSONArray("water");


                listItems=getArrayListFromJSONArray(arr);


                return new Integer(0);
            } catch (Exception e) {
                this.exception = e;
                return new Integer(-1);
            }
        }

        private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){

            ArrayList<JSONObject> aList=new ArrayList<JSONObject>();

            try {

                if (jsonArray != null) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        aList.add(jsonArray.getJSONObject(i));

                    }

                }

            }catch (JSONException je){je.printStackTrace();}

            return  aList;

        }


        private void getBitmapFromURL(String src) {
            try {
                java.net.URL url = new java.net.URL(src);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                img = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
