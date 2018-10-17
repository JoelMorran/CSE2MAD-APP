package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
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

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;




public class Checkout2Activity extends AppCompatActivity {

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
    private ProgressBar progressBar;

    private int progressStatus = 0;

    private Handler handler = new Handler();
    String TAG = "Checkout2Activity";

    private Context parent;
    private FileInputStream fileIn;
    private FileOutputStream fileOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private Object outputObject;
    private String filePath;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout2);
        spinner = (Spinner) findViewById(R.id.spinner3);


        checkout = (Button) findViewById(R.id.checkout);
        items = (TextView) findViewById(R.id.items);
        subtotal = (TextView) findViewById(R.id.subtotal);
        total2 = (TextView) findViewById(R.id.total2);
        gst = (TextView) findViewById(R.id.GST);
        //placeorder = (Button) findViewById(R.id.placeorder);

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


        listV = (ListView) findViewById(R.id.listV);
        db = new DBHandler3(getApplicationContext());

        listItems = db.getAllOrders();


        adapter = new ListAdapterCheckout(getApplicationContext(), R.layout.list_layout_checkout, R.id.txtname, listItems);
        listV.setAdapter(adapter);

        ArrayList<Order> orders = db.getAllOrders();

        double total = 0;
        double t = 0;
        int count = 0;

        for (Order cn : orders) {
            ++count;
            Double tt = Double.parseDouble(cn.getPrice());
            t = tt;
            total = t + total;

        }
        double gsts = 10 * (total / 100);
        double subt = total - (10 * (total / 100));
        double x = total;
        String s = String.valueOf(x);
        String k = String.valueOf(x);
        String ss = String.valueOf(count);
        String sss = String.valueOf(subt);
        String ssss = String.valueOf(gsts);
        items.setText("Quantity: " + ss);
        subtotal.setText("Subtotal: " + sss);

        gst.setText("GST: " + ssss);
        total2.setText("Total: " + k);

        /*placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CheckoutActivity.this, CheckoutPaymentMethodActivity.class);

                CheckoutActivity.this.startActivity(myIntent);
            }
        });*/


        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.d("","help");
                sendBroadcast();
            }
        });


        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 60) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            Log.d (TAG, "happy time");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(15000); ///900000
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                go();
            }
        }).start();

        int count3 = 0;
        String count2 = "1";
        readObject("test.json");
        for(){

        }

        JSONObject Obj = new JSONObject();
        JSONArray orderArr = new JSONArray();
        try{

            for(Order or : orders){
                JSONObject item = new JSONObject();
                item.put("id", or.getID());
                item.put("name", or.getName());
                item.put("extra", or.getExtra());
                item.put("amount", or.getAmount());
                item.put("note", or.getNote());
                item.put("price", or.getPrice());
                item.put("cost", or.getCost());
                item.put("tableid", or.getTableid());
                orderArr.put(item);

            }

        Obj.put(count2, orderArr);


        }
        catch (JSONException e)
        {
            Log.e(TAG, "JSONException: " + e.getMessage());

        }

        try{
            Log.d(TAG, Obj.toString(4));
            if(Obj != null) {
                writeObject(Obj, "test.json");


                readObject("test.json");
                Log.e(TAG,"is nto null");
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }



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
    private void go() {
                Intent myIntent = new Intent(Checkout2Activity.this, Checkout3Activity.class);

                Checkout2Activity.this.startActivity(myIntent);
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

        public void writeObject(Object inputObject, String fileName){
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


}
