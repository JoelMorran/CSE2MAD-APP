package com.example.kobiqoi_laptop.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListAdapter2 extends ArrayAdapter<JSONObject> {

    int vg;

    ArrayList<JSONObject> list;

    Context context;

    public ListAdapter2(Context context, int vg, int id, ArrayList<JSONObject> list){

        super(context,vg, id,list);

        this.context=context;

        this.vg=vg;

        this.list=list;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);

        TextView name=(TextView)itemView.findViewById(R.id.name);

        TextView price=(TextView)itemView.findViewById(R.id.price);

        TextView description=(TextView)itemView.findViewById(R.id.description);

        try {

            name.setText(list.get(position).getString("name"));

            /*if(list.get(position).getString("glutenfree").equals("true")) {
                //list.get(position).getString("glutenfree").equals("true");
                txtName.setText("GF");
            }
            else
            {
                txtName.setText("Not GF");
            }*/
            price.setText(list.get(position).getString("price"));

            description.setText(list.get(position).getString("description"));



        } catch (JSONException e) {

            e.printStackTrace();

        }



        return itemView;

    }

}


