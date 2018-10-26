package com.example.kobiqoi_laptop.assignment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TableListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listV;

    private ListAdapterTable adapter;

    private ArrayList<Table> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//this works with onSupportNavigateUp()
        getSupportActionBar().setDisplayShowHomeEnabled(true); //this works with onSupportNavigateUp()


        listV=(ListView)findViewById(R.id.listv);
        DBHandler2 db = new DBHandler2(getApplicationContext());

        listItems =  db.getAllTables();

        db.close();


        adapter=new ListAdapterTable(getApplicationContext(), R.layout.list_layout_tables,R.id.txtname,listItems);
        listV.setAdapter(adapter);

        listV.setOnItemClickListener(this);
    }

    private Exception exception;
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);

        Intent intent = new Intent();
        intent.setClass(this, LetsEat2Activity.class);

       String id3 = String.valueOf(id);
        int id2 = Integer.parseInt(id3);
        String t = listItems.get(position).getTablenumber();

        try
        {

            startLockTask();

            intent.putExtra("tbnumber", listItems.get(position).getTablenumber());


        }
        catch (Exception e)
        {
            this.exception = e;

        }
        startActivity(intent);



    }


    DBHandler2 db;

    class RetrieveTable extends AsyncTask<String, Void, Integer> {
        private Exception exception;
        private Bitmap img;

        @Override
        protected Integer doInBackground(String... urlStrs) {
            try {

                DBHandler2 db = new DBHandler2(getApplicationContext());

                List<Table> tables = db.getAllTables();

                db.close();


                return new Integer(0);
            } catch (Exception e) {
                this.exception = e;
                return new Integer(-1);
            }
        }


        protected void onPostExecute(Integer res) {
          adapter=new ListAdapterTable(getApplicationContext(), R.layout.list_layout_orders2,R.id.txtname,listItems);
            listV.setAdapter(adapter);

        }
    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
