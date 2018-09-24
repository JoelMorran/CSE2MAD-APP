package com.example.kobiqoi_laptop.assignment;

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

            }

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


}
