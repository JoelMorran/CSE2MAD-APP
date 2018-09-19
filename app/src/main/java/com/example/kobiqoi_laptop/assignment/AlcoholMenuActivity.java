package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class AlcoholMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
private ListView listV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_menu);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);





        //signIn = (Button) findViewById(R.id.signIn);
        //signUp = (Button) findViewById(R.id.signUp);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

       /* signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SignInSignUpActivity.this, LoginPageActivity.class);

                SignInSignUpActivity.this.startActivity(myIntent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SignInSignUpActivity.this, CreateNewAccountActivity.class);

                SignInSignUpActivity.this.startActivity(myIntent);
            }
        });*/
       new RetrieveMenuTask().execute("http://homepage.cs.latrobe.edu.au/jamorran/menu.json");
        listV=(ListView)findViewById(R.id.listv);
      // JSONArray jsonArray; //=getJSonData("jsondata.json");

       // ArrayList<JSONObject> listItems;     //getArrayListFromJSONArray(jsonArray);*/



       // ListAdapter adapter=new ListAdapter(this,R.layout.list_layout,R.id.txtid,listItems);



        //* *EDIT* *


        listV.setOnItemClickListener(this);

    }



    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        //v.get
        Intent intent = new Intent();
        intent.setClass(this, ItemActivity.class);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    }


    /*private JSONArray getJSonData(String fileName){

        JSONArray jsonArray=null;

        try {

            InputStream is = getResources().getAssets().open(fileName);

            int size = is.available();

            byte[] data = new byte[size];

            is.read(data);

            is.close();

            String json = new String(data, "UTF-8");

            jsonArray=new JSONArray(json);

        }catch (IOException e){

            e.printStackTrace();

        }catch (JSONException je){

            je.printStackTrace();

        }

        return jsonArray;

    }*/

    /*private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){

        ArrayList<JSONObject> aList=new ArrayList<JSONObject>();

        try {

            if (jsonArray != null) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    aList.add(jsonArray.getJSONObject(i));

                }

            }

        }catch (JSONException je){je.printStackTrace();}

        return  aList;

    }*/



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

    class RetrieveMenuTask extends AsyncTask<String, Void, Integer> {
        private Exception exception;
        private Bitmap img;
        // private JSONArray arr;
        private ListAdapter adapter;
        private ArrayList<JSONObject> listItems;
        @Override
        protected Integer doInBackground(String... urlStrs) {
            try {
// get the menu
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
                JSONObject jsonObj = new JSONObject(buffer.toString());
                JSONArray arr = jsonObj.getJSONArray("menu");
                arr.getJSONObject(0).get("img_src").toString();



                listItems=getArrayListFromJSONArray(arr);


                //getBitmapFromURL(arr.getJSONObject(0).get("img_src").toString());

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
// modify the UI Thread
            //item1Button.setImageBitmap(img);
            //ListAdapter adapter=new ListAdapter(AlcoholMenuActivity.this, R.layout.list_layout,R.id.txtid,listItems);
            adapter=new ListAdapter(getApplicationContext(), R.layout.list_layout,R.id.txtid,listItems);
            listV.setAdapter(adapter);

        }
    }

}
