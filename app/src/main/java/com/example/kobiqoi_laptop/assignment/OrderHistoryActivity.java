package com.example.kobiqoi_laptop.assignment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    private Button placeorder;
    private ImageButton helpbtn;
    private Spinner spinner;

    ListView listV;

    private ListAdapterOrderHistory adapter;



    private ArrayList<JSONObject> listItems;
    private JSONArray sendarr;
    private JSONObject jsonObj;

    private Button emptycart;
    private Button checkout;
    DBHandler3 db;
    private TextView items;
    private TextView subtotal;
    private TextView total2;
    private TextView gst;
    private ProgressBar progressBar;

    private int progressStatus = 0;

    String masterstring;

    private Handler handler = new Handler();
    String TAG = "Checkout2Activity";


    private FileInputStream fileIn;
    private FileOutputStream fileOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private Object outputObject;
    private String filePath;

    private String xz;

    private String tbnum;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);


        checkout = (Button) findViewById(R.id.checkout);
        items = (TextView) findViewById(R.id.items);
        subtotal = (TextView) findViewById(R.id.subtotal);
        total2 = (TextView) findViewById(R.id.total2);
        gst = (TextView) findViewById(R.id.GST);


        helpbtn = (ImageButton) findViewById(R.id.helpbtn);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

               getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()


        listV = (ListView) findViewById(R.id.listV);
        db = new DBHandler3(getApplicationContext());



        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.d("","help");
                sendBroadcast();
            }
        });


        try {


            String tt = readObject("1.json").toString();

            JSONArray arr = new JSONArray(tt);

            listItems=getArrayListFromJSONArray(arr);
        }
        catch( JSONException e)
        {
            e.printStackTrace();
        }

        adapter = new ListAdapterOrderHistory(getApplicationContext(), R.layout.list_layout_order_history, R.id.id2, listItems);
        listV.setAdapter(adapter);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:

                Intent myIntent = new Intent(this, OrderHistoryActivity.class);
                myIntent.putExtra("tbnumber", xz);
                this.startActivity(myIntent);

                return true;

            case R.id.action_cart:

                Intent myIntent2 = new Intent(this, YourCartActivity.class);

                this.startActivity(myIntent2);
                return true;

            case R.id.action_menu:

                Intent myIntent3 = new Intent(this, MenuActivity.class);

                Bundle extras = getIntent().getExtras();

                if(extras != null) {

                    xz = extras.getString("tbnumber");
                }

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

    public Object readObject(String fileName){
        try {
            filePath = this.getFilesDir().getAbsolutePath() + "/" + fileName;
            fileIn = new FileInputStream(filePath);
            objectIn = new ObjectInputStream(fileIn);
            outputObject = objectIn.readObject();
            Log.d (TAG, outputObject.toString() + "I R FILE");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputObject;
    }


    public void writeObject(Object inputObject, String fileName)  {
        try {

            filePath = this.getFilesDir().getAbsolutePath() + "/" + fileName;
            fileOut = new FileOutputStream(filePath);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(inputObject);
            fileOut.getFD().sync();
            Log.d (TAG, inputObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {

                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("test3.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


}
