package com.example.kobiqoi_laptop.assignment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

class RetrieveMenuTask2 extends AsyncTask<String, Void, Integer> {
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
        adapter=new ListAdapter(null, R.layout.list_layout,R.id.txtid,listItems);
        //listV.setAdapter(adapter);

    }
}
