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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private TextView name;
    private TextView price;
    private TextView glutenfree;
    private TextView description;
    private TextView amount;
    // private ImageView img;
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
        //ImageView img = (ImageView) findViewById(R.id.img);
       // item1Button = (ImageButton)findViewById(R.id.item1Button);
        add = (ImageButton)findViewById(R.id.add);
        remove = (ImageButton)findViewById(R.id.remove);
        addtoorder = (Button)findViewById(R.id.addtoorder);
        addnote2 = (EditText)findViewById(R.id.addnote2);
        imgbtn = (ImageView)findViewById(R.id.imgbtn);


        helpbtn = (ImageButton) findViewById(R.id.helpbtn);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        Bundle extras = getIntent().getExtras();

        if(extras != null) {

           /* Long data2 = extras.getLong("id");
            String data = String.valueOf(data2);
            name.setText(data);*/
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

            //Bitmap imgs = ;
            //getBitmapFromURL(arr.getJSONObject(0).get("img_src").toString());
            String II = extras.getString("img_src");
            new RetriveImg().execute(II);
            //getBitmapFromURL(II);
            String tbnum = extras.getString("tbnumber");
            //img.setImageBitmap(img3);




            // int data = extras.getInt("position");
            // name.setText(data);
        }

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String one = String.valueOf(amount.getText());
                int one2 = Integer.parseInt(one);
                ++one2;
                one = String.valueOf(one2);
                amount.setText(one);
                //Intent myIntent = new Intent(ItemActivity.this, LoginPageActivity.class);

                // ItemActivity.this.startActivity(myIntent);
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
                //Intent myIntent = new Intent(ItemActivity.this, LoginPageActivity.class);

                // ItemActivity.this.startActivity(myIntent);
            }
        });

        //spinner = (Spinner) findViewById(R.id.spinner);

        db = new DBHandler3(this);
        //db.onUpgrade(db.getReadableDatabase(),1,2); /////////////////////////////////////////////////////////
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


                    //description.setText(description2);

                    // Order(String name, String extra, String amount, String note, String price, String cost, String tableid)
                    // db = new DBHandler3(getApplicationContext());
                   // db.addOrder(new Order(name3, description2, amount.getText().toString(), addnote2.getText().toString(),
                     //       price3, cost2, "0"));


                db.addOrder(new Order(name3, description2, amount.getText().toString(), addnote2.getText().toString(),
                               price3, cost2, tbnum));
                    //db.addOrder(new Order("GH", "GHFDGFJG", "HFHJ", "JJHHF",
                     //       "HJGHFG", "KGJFGH", "0"));
                    //db.addOrder(new Order("tasdfatt", "asdf", "fa", "ttasdft","asdf", "tasdftt", "t0sadf"));
                    //db.addOrder(new Order("ttasdft", "tasdftt", "tasdftt", "tasdftt","tasdftt", "ttasdft", "dsft0"));
                   // db.addOrder(new Order("asdfttt", "ttsdft", "tasdftt", "ttsdaft","tsdftt", "ttsdaft", "t0"));
                  //  db.addOrder(new Order("tdfastt", "tsdaftt", "tfdsatt", "tsadftt","ttasdft", "ttasdft", "t0"));
                  //  db.addOrder(new Order("ttsadft", "tasdftt", "ttasdft", "tasdftt","tasdfastt", "dsftt", "t0"));
//
            }
                   // updateSpinner();
                    Intent myIntent = new Intent(ItemActivity.this, YourCartActivity.class);
               // myIntent.putExtra("tbnumber", tbnum);
                    ItemActivity.this.startActivity(myIntent);


                /*username.getText().clear();
                email.getText().clear();
                password.getText().clear();
                name.getText().clear();
                updateSpinner();
                createlog();
                name.setFocusable(true);
                name.requestFocus();*/


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
                tbid();
                myIntent3.putExtra("tbnumber", xz);
                this.startActivity(myIntent3);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                //sendBroadcast();
                return super.onOptionsItemSelected(item);

        }
    }

    private void tbid() {
        DBHandler3 db;
        db = new DBHandler3(getApplicationContext());
        ArrayList<Order> orders = db.getAllOrders();

        for (Order cn : orders)
        {

            xz = (cn.getTableid());


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

    public class RetriveImg extends AsyncTask<String, Void, Integer> {
        private Exception exception;
        //private Bitmap img;
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
// modify the UI Thread
            imgbtn.setImageBitmap(img);
        }
    }

    /*private void getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            img3 = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onPostExecute(Integer res) {
// modify the UI Thread
        item1Button.setImageBitmap(img3);
    }*/

    private void updateSpinner() {
        DBHandler3 db = new DBHandler3(getApplicationContext());
        List<Order> orders = db.getAllOrders();
        ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, orders);
        //spinner.setAdapter(adapter);
        //createlog();

    }

    private void sendBroadcast() {
        Intent intent = new Intent();
        intent.setAction("com.example.kobiqoi_laptop.assignment");
        intent.putExtra("Life_form", "Table " + tableid + " needs assistance \n");
        intent.putExtra("tableid", "Table " + tableid + " needs assistance \n"  );
        Toast.makeText(this.getApplicationContext(),"HELOOOOOOOOOO", Toast.LENGTH_LONG);
        sendBroadcast(intent);
    }


}












/*
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ItemActivity extends AppCompatActivity {

    private TextView name;
    private TextView price;
    private TextView glutenfree;
    private TextView description;
   // private ImageView img;
    private Bitmap img3;
    private ImageButton item1Button;
    private String II;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        TextView name = (TextView)findViewById(R.id.name);
        TextView price = (TextView)findViewById(R.id.price);
        TextView glutenfree = (TextView)findViewById(R.id.gf);
        TextView description = (TextView)findViewById(R.id.description);
        //ImageView img = (ImageView) findViewById(R.id.img);
        ImageButton item1Button = (ImageButton)findViewById(R.id.item1Button);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {

           /* Long data2 = extras.getLong("id");
            String data = String.valueOf(data2);
            name.setText(data);*/
 /*           String name3 = extras.getString("name");
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

           //Bitmap imgs = ;
            //getBitmapFromURL(arr.getJSONObject(0).get("img_src").toString());
            String II = extras.getString("img_src");
            new RetriveImg().execute("http://foodology.ca/wp-content/uploads/2011/03/BP-pizza.jpg");
            //getBitmapFromURL(II);

            //img.setImageBitmap(img3);




           // int data = extras.getInt("position");
            // name.setText(data);
        }

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

  /*          }

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

    public class RetriveImg extends AsyncTask<String, Void, Integer> {
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
// modify the UI Thread
            item1Button.setImageBitmap(img);
        }
    }

    /*private void getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            img3 = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onPostExecute(Integer res) {
// modify the UI Thread
        item1Button.setImageBitmap(img3);
    }*/

/*
}*/
