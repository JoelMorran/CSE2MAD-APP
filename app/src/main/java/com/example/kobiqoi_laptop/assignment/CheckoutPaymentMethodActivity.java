package com.example.kobiqoi_laptop.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class CheckoutPaymentMethodActivity extends AppCompatActivity {

    private Button placeorder;
    private RadioButton paypaypal;
    private RadioButton paycreditcard;
    private RadioButton paydebit;
    private RadioButton paycash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_payment_method);

        placeorder = (Button) findViewById(R.id.placeorder);
        paypaypal = (RadioButton) findViewById(R.id.paypaypal);
        paycreditcard = (RadioButton) findViewById(R.id.paycreditcard);
        paydebit = (RadioButton) findViewById(R.id.paydebit);
        paycash = (RadioButton) findViewById(R.id.paycash);
        //signUp = (Button) findViewById(R.id.signUp);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        // ActionBar ab = getSupportActionBar();//this works with onSupportNavigateUp()

        // Enable the Up button
        //  ab.setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()

       placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(paypaypal.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // get selected radio button from radioGroup
                    int selectedId = gender.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    selectedRadioButton = (RadioButton)findViewById(selectedId);
                    Toast.makeText(getApplicationContext(), selectedRadioButton.getText().toString()+" is selected", Toast.LENGTH_SHORT).show();
                }*/
               if(paypaypal.isChecked())
                {
                Intent myIntent = new Intent(CheckoutPaymentMethodActivity.this, PayPalActivity.class);

                CheckoutPaymentMethodActivity.this.startActivity(myIntent);
            }
            else if(paycreditcard.isChecked())
                {
                    Intent myIntent = new Intent(CheckoutPaymentMethodActivity.this, CreditCardDetailsActivity.class);

                    CheckoutPaymentMethodActivity.this.startActivity(myIntent);
                }
                else if(paydebit.isChecked())
                {
                    Intent myIntent = new Intent(CheckoutPaymentMethodActivity.this, CreditCardDetailsActivity.class);

                    CheckoutPaymentMethodActivity.this.startActivity(myIntent);
                }
                else if(paycash.isChecked()) {


               }
               else{
                   Toast.makeText(getApplicationContext(), "Please select Payment option", Toast.LENGTH_SHORT).show();
               }
            }
        });

        /*signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SignInSignUpActivity.this, CreateNewAccountActivity.class);

                SignInSignUpActivity.this.startActivity(myIntent);
            }
        });*/

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
}
