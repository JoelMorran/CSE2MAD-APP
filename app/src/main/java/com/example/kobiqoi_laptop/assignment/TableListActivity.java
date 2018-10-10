package com.example.kobiqoi_laptop.assignment;

import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TableListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listV;

    private ListAdapterTable adapter;

    private ArrayList<Table> listItems;
   // private JSONArray sendarr;
   // private JSONObject jsonObj;


    private Button hack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        hack = (Button) findViewById(R.id.hack);
        //signUp = (Button) findViewById(R.id.signUp);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

       // new RetrieveTable().execute();

       hack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TableListActivity.this, LetsEat2Activity.class);

                TableListActivity.this.startActivity(myIntent);
                startLockTask();
                //setLockTaskPackages();
            }
        });

        /*signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SignInSignUpActivity.this, CreateNewAccountActivity.class);

                SignInSignUpActivity.this.startActivity(myIntent);
            }
        });*/



                // JSONArray jsonArray; //=getJSonData("jsondata.json");

        // ArrayList<JSONObject> listItems;     //getArrayListFromJSONArray(jsonArray);*/



        // ListAdapter adapter=new ListAdapter(this,R.layout.list_layout,R.id.txtid,listItems);



        //* *EDIT* *
        listV=(ListView)findViewById(R.id.listv);
        DBHandler2 db = new DBHandler2(getApplicationContext());

        listItems =  db.getAllTables();


        adapter=new ListAdapterTable(getApplicationContext(), R.layout.list_layout_tables,R.id.txtname,listItems);
        listV.setAdapter(adapter);

        listV.setOnItemClickListener(this);
    }

    private Exception exception;
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        //v.get
        Intent intent = new Intent();
        intent.setClass(this, LetsEat2Activity.class);
        //intent.putExtra("position", position);
        // Or / And
        //intent.putExtra("id", id);
        //Intent intenttb = new Intent();
        //intenttb.setClass(this, ItemActivity.class);
        //JSONArray arr = jsonObj.getJSONArray("mains");
       String id3 = String.valueOf(id);
        int id2 = Integer.parseInt(id3);
        String t = listItems.get(position).getTablenumber();

        try
        {

            startLockTask();

            intent.putExtra("tbnumber", listItems.get(position).getTablenumber());
            //intent.putExtra("tbnumber", listItems.get(position).getTablenumber());
            /*sendarr = jsonObj.getJSONArray("alcohol");
            String name3 = sendarr.getJSONObject(id2).get("name").toString();


            String price3 = sendarr.getJSONObject(id2).get("price").toString();
            intent.putExtra("price", price3);

            String glutenfree3 = sendarr.getJSONObject(id2).get("glutenfree").toString();
            intent.putExtra("glutenfree", glutenfree3);

            String description3 = sendarr.getJSONObject(id2).get("description").toString();
            intent.putExtra("description", description3);


            String img3 = sendarr.getJSONObject(id2).get("img_src").toString();
            //getBitmapFromURL(sendarr.getJSONObject(0).get("img_src").toString());
            intent.putExtra("img_src", img3);*/

        }
        catch (Exception e)
        {
            this.exception = e;
            // return new Integer(-1);
        }
        startActivity(intent);
        //startActivity(intenttb);


    }

  /* @Override
    public void startLockTask()
    {

    }*/
    DBHandler2 db;

    class RetrieveTable extends AsyncTask<String, Void, Integer> {
        private Exception exception;
        private Bitmap img;
        // private JSONArray arr;
        // private ListAdapter adapter;
        //private ArrayList<JSONObject> listItems;
        @Override
        protected Integer doInBackground(String... urlStrs) {
            try {
// get the menu
               /* java.net.URL url = new java.net.URL(urlStrs[0]);
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
                JSONArray arr = jsonObj.getJSONArray("mains");
                sendarr = jsonObj.getJSONArray("mains");
                // arr.getJSONObject(0).get("img_src").toString();
                */
                DBHandler2 db = new DBHandler2(getApplicationContext());

                List<Table> tables = db.getAllTables();

                //Object tablex(int id, String str);

                /*ArrayList<Object> list2 = new ArrayList<Object>();

                 for (Table cn : tables)
                {
                    list2.add(cn);
                }*/


                /*List<Table> tables = db.getAllTables();
                ArrayList t  = new ArrayList(tables);

                listItems = t;*/


                /*
                for (Account cn : accounts)
                {
                    if(email.getText().toString().equals(cn.getEmail()) )
                    {
                        isFoundEmail = true;
                        break;
                    }
                }*/

                //listItems=getArrayListFromJSONArray(arr);


                //getBitmapFromURL(arr.getJSONObject(0).get("img_src").toString()); LAAAAAAAAAAAGGGGGGGGGGGGGGGGGAGGGGGGGGGGGGGGGGGGGGGGGGGGGaGGGGAGAGAGAGAGAGAGAGAGAGAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGGGGGGGGGAGAGAGAGAGGA

                return new Integer(0);
            } catch (Exception e) {
                this.exception = e;
                return new Integer(-1);
            }
        }

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


       /* private void getBitmapFromURL(String src) {
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
        }*/

        protected void onPostExecute(Integer res) {
// modify the UI Thread
            //item1Button.setImageBitmap(img);
            //ListAdapter adapter=new ListAdapter(AlcoholMenuActivity.this, R.layout.list_layout,R.id.txtid,listItems);
            adapter=new ListAdapterTable(getApplicationContext(), R.layout.list_layout_orders2,R.id.txtname,listItems);
            listV.setAdapter(adapter);

        }
    }

  /*  @Override
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

    /*    return super.onCreateOptionsMenu(menu);
    }*/


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /*@Override
    public void onBackPressed() {
       /* if (!shouldAllowBack()) {
            doSomething();
        } else {
            super.onBackPressed();
        }*/

       /* if (onSupportNavigateUp() == true) {
            super.onBackPressed();
        } else
        {

        }

    }*/
}
