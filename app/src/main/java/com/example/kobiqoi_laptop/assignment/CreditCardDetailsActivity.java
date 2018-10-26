package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Toast;

import java.util.ArrayList;

public class CreditCardDetailsActivity extends AppCompatActivity {

    private Button paynow;
    private EditText nameoncard;
    private EditText cardnumber;
    private EditText expiry;
    private EditText cvc;
    DBHandler3 db;
    private ImageButton helpbtn;
    private String xz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_details);
        paynow = (Button) findViewById(R.id.paynow);
        nameoncard = (EditText) findViewById(R.id.nameoncard);
        cardnumber = (EditText) findViewById(R.id.cardnumber);
        expiry = (EditText) findViewById(R.id.expiry);
        cvc = (EditText) findViewById(R.id.cvc);

        helpbtn = (ImageButton) findViewById(R.id.helpbtn);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardnumber.getText().toString().length() >= 14 && cardnumber.getText().toString().length() <= 19 &&
                        expiry.getText().toString().length() == 5 && nameoncard.getText().toString().length() >= 1
                        && cvc.getText().toString().length() >= 3 && cvc.getText().toString().length() <= 4)
                {



                    Intent myIntent = new Intent(CreditCardDetailsActivity.this, Checkout2Activity.class);

                    CreditCardDetailsActivity.this.startActivity(myIntent);

                    cardnumber.getText().clear();
                    expiry.getText().clear();
                    cvc.getText().clear();
                    nameoncard.getText().clear();

                    nameoncard.setFocusable(true);
                    nameoncard.requestFocus();

                }
                else
                {  // if first box error  focus  & message
                    Animation shake = AnimationUtils.loadAnimation(CreditCardDetailsActivity.this, R.anim.shake);
                    if(!(nameoncard.getText().toString().length() >= 1))
                    {  // if both error focus 1st and message both
                        if(!(nameoncard.getText().toString().length() >= 1 &&  cardnumber.getText().toString().length() >= 1))
                        {
                            if(!(expiry.getText().toString().length() == 5)) {
                                if(!(cvc.getText().toString().length() >= 3 && cvc.getText().toString().length() <= 4)){
                                    //logic checked check
                                    nameoncard.startAnimation(shake);
                                    nameoncard.setFocusable(true);
                                    nameoncard.requestFocus();
                                    nameoncard.getText().clear();
                                    nameoncard.setError("Please enter a valid name on card > 0");

                                        cardnumber.startAnimation(shake);
                                        cardnumber.getText().clear();
                                        cardnumber.setError("Please enter a valid card number between 14 and 19 digits");

                                      expiry.startAnimation(shake);
                                        expiry.getText().clear();
                                        expiry.setError("Please enter a valid expiry");

                                    cvc.startAnimation(shake);
                                    cvc.getText().clear();
                                    cvc.setError("Please enter a vaild cvc between 3 and 4 digits");
                                }
                                else
                                {
                                    //logic checked check
                                    nameoncard.startAnimation(shake);
                                    nameoncard.setFocusable(true);
                                    nameoncard.requestFocus();
                                    nameoncard.getText().clear();
                                    nameoncard.setError("Please enter a valid name on card > 0");

                                    cardnumber.startAnimation(shake);
                                    cardnumber.getText().clear();
                                    cardnumber.setError("Please enter a valid card number between 14 and 19 digits");

                                    expiry.startAnimation(shake);
                                    expiry.getText().clear();
                                    expiry.setError("Please enter a valid expiry");
                                }
                            }
                            else if(!(cvc.getText().toString().length() >= 3 && cvc.getText().toString().length() <= 4 )){
                                //logic checked check
                                nameoncard.startAnimation(shake);
                                nameoncard.setFocusable(true);
                                nameoncard.requestFocus();
                                nameoncard.getText().clear();
                                nameoncard.setError("Please enter a valid name on card > 0");

                                cardnumber.startAnimation(shake);
                                cardnumber.getText().clear();
                                cardnumber.setError("Please enter a valid card number between 14 and 19 digits");

                                cvc.startAnimation(shake);
                                cvc.getText().clear();
                                cvc.setError("Please enter a vaild cvc between 3 and 4 digits");

                            }
                            else{
                                //logic checked check
                                nameoncard.startAnimation(shake);
                                nameoncard.setFocusable(true);
                                nameoncard.requestFocus();
                                nameoncard.getText().clear();
                                nameoncard.setError("Please enter a valid name on card > 0");

                                cardnumber.startAnimation(shake);
                                cardnumber.getText().clear();
                                cardnumber.setError("Please enter a valid card number between 14 and 19 digits");


                            }
                        }

                        //first error only focus and message
                        else if(!(expiry.getText().toString().length() == 5))
                        {

                            if(!(cvc.getText().toString().length() >= 3 && cvc.getText().toString().length() <= 4 )){
                                //logic checked check
                                nameoncard.startAnimation(shake);
                                nameoncard.setFocusable(true);
                                nameoncard.requestFocus();
                                nameoncard.getText().clear();
                                nameoncard.setError("Please enter a valid name on card > 0");

                                expiry.startAnimation(shake);
                                expiry.getText().clear();
                                expiry.setError("Please enter a valid expiry");

                                cvc.startAnimation(shake);
                                cvc.getText().clear();
                                cvc.setError("Please enter a vaild cvc between 3 and 4 digits");
                            }
                            else
                            {
                                //logic checked check
                                nameoncard.startAnimation(shake);
                                nameoncard.setFocusable(true);
                                nameoncard.requestFocus();
                                nameoncard.getText().clear();
                                nameoncard.setError("Please enter a valid name on card > 0");

                                expiry.startAnimation(shake);
                                expiry.getText().clear();
                                expiry.setError("Please enter a valid expiry");

                            }

                        }
                        else if(!(cvc.getText().toString().length() >= 3 && cvc.getText().toString().length() <= 4 ))
                        {
                            //logic checked check
                            nameoncard.startAnimation(shake);
                            nameoncard.setFocusable(true);
                            nameoncard.requestFocus();
                            nameoncard.getText().clear();
                            nameoncard.setError("Please enter a valid name on card > 0");

                            cvc.startAnimation(shake);
                            cvc.getText().clear();
                            cvc.setError("Please enter a vaild cvc between 3 and 4 digits");

                        }
                        else
                        {
                            //logic checked check
                            nameoncard.startAnimation(shake);
                            nameoncard.setFocusable(true);
                            nameoncard.requestFocus();
                            nameoncard.getText().clear();
                            nameoncard.setError("Please enter a valid name on card > 0");
                        }

                    } // second only focus and message

                    else if(!( cardnumber.getText().toString().length() >= 14 && cardnumber.getText().toString().length() <= 19 ))
                    {
                        if(!(expiry.getText().toString().length() == 5)) {
                            if(!(cvc.getText().toString().length() >= 3 && cvc.getText().toString().length() <= 4 )){
                                //logic checked check


                                    cardnumber.startAnimation(shake);
                                    cardnumber.setFocusable(true);
                                    cardnumber.requestFocus();
                                    cardnumber.getText().clear();
                                cardnumber.setError("Please enter a valid card number between 14 and 19 digits");


                                expiry.startAnimation(shake);
                                expiry.getText().clear();
                                expiry.setError("Please enter a valid expiry");


                                cvc.startAnimation(shake);
                                cvc.getText().clear();
                                cvc.setError("Please enter a vaild cvc between 3 and 4 digits");
                            }
                            else{
                                //logic checked check

                                cardnumber.startAnimation(shake);
                                cardnumber.setFocusable(true);
                                cardnumber.requestFocus();
                                cardnumber.getText().clear();
                                cardnumber.setError("Please enter a valid card number between 14 and 19 digits");

                                expiry.startAnimation(shake);
                                expiry.getText().clear();
                                expiry.setError("Please enter a valid expiry");



                            }
                        }
                        else if(!(cvc.getText().toString().length() >= 3 && cvc.getText().toString().length() <= 4 )){
                            //logic checked check

                            cardnumber.startAnimation(shake);
                            cardnumber.setFocusable(true);
                            cardnumber.requestFocus();
                            cardnumber.getText().clear();
                            cardnumber.setError("Please enter a valid card number between 14 and 19 digits");


                            cvc.startAnimation(shake);
                            cvc.getText().clear();
                            cvc.setError("Please enter a vaild cvc between 3 and 4 digits");
                        }
                        else{
                            //logic checked check

                            cardnumber.startAnimation(shake);
                            cardnumber.setFocusable(true);
                            cardnumber.requestFocus();
                            cardnumber.getText().clear();
                            cardnumber.setError("Please enter a valid card number between 14 and 19 digits");

                        }
                    }
                    else if(!(expiry.getText().toString().length() == 5)) {
                        if(!(cvc.getText().toString().length() >= 3 && cvc.getText().toString().length() <= 4 )){
                            //logic checked check

                                expiry.startAnimation(shake);
                                expiry.setFocusable(true);
                                expiry.requestFocus();
                                expiry.getText().clear();
                            expiry.setError("Please enter a valid expiry");


                            cvc.startAnimation(shake);
                            cvc.getText().clear();
                            cvc.setError("Please enter a vaild cvc between 3 and 4 digits");
                        }
                        else
                        {
                            //logic checked check

                                expiry.startAnimation(shake);
                                expiry.setFocusable(true);
                                expiry.requestFocus();
                                expiry.getText().clear();
                            expiry.setError("Please enter a valid expiry");

                        }
                    }
                    else
                    {
                        //logic checked check
                        cvc.startAnimation(shake);
                        cvc.setFocusable(true);
                        cvc.requestFocus();
                        cvc.getText().clear();
                        cvc.setError("Please enter a vaild cvc between 3 and 4 digits");
                    }


                }

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
        intent.putExtra("Life_form", "Table " + s + " needs assistance \n");
        intent.putExtra("tableid", "Table " + s + " needs assistance \n"  );
        Toast.makeText(this.getApplicationContext(),"HELOOOOOOOOOO", Toast.LENGTH_LONG);
        sendBroadcast(intent);
    }
}
