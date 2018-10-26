package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

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

public class SpecialsMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listV;
    private TextView name;
    private TextView price;
    private TextView description;
    private ListAdapter adapter;

    private ArrayList<JSONObject> listItems;
    private JSONArray sendarr;
    private JSONObject jsonObj;


    private String name2;
    private String price2;
    private String description2;
    private ImageButton helpbtn;
    private String tableid = "0";
    private String tbnum;

    private String xz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specials_menu);
        new SpecialsMenuActivity.RetrieveMenuTask().execute("http://homepage.cs.latrobe.edu.au/jamorran/menu.json");


        helpbtn = (ImageButton) findViewById(R.id.helpbtn);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()



        listV=(ListView)findViewById(R.id.listv);
        name=(TextView)findViewById(R.id.name);
        price=(TextView)findViewById(R.id.price);
        description=(TextView)findViewById(R.id.description);

        listV.setOnItemClickListener(this);

        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendBroadcast();
            }
        });

    }




    private Exception exception;
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            String tbnum = extras.getString("tbnumber");

            Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);

            Intent intent = new Intent();
            intent.setClass(this, ItemActivity.class);



            String id3 = String.valueOf(id);
            int id2 = Integer.parseInt(id3);

            try {
                sendarr = jsonObj.getJSONArray("specials");
                String name3 = sendarr.getJSONObject(id2).get("name").toString();
                intent.putExtra("name", name3);

                String price3 = sendarr.getJSONObject(id2).get("price").toString();
                intent.putExtra("price", price3);

                String glutenfree3 = sendarr.getJSONObject(id2).get("glutenfree").toString();
                intent.putExtra("glutenfree", glutenfree3);

                String description3 = sendarr.getJSONObject(id2).get("description").toString();
                intent.putExtra("description", description3);


                String img3 = sendarr.getJSONObject(0).get("img_src").toString();

                intent.putExtra("img_src", img3);
                intent.putExtra("tbnumber", tbnum);

            } catch (Exception e) {
                this.exception = e;

            }


            startActivity(intent);
        }

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
                JSONArray arr = jsonObj.getJSONArray("specials");
                sendarr = jsonObj.getJSONArray("specials");


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

        protected void onPostExecute(Integer res) {
     adapter=new ListAdapter(getApplicationContext(), R.layout.list_layout,R.id.txtid,listItems);
            listV.setAdapter(adapter);

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
