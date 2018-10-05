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

public class ListAdapterTable extends ArrayAdapter<Table> {

    int vg;

    ArrayList<Table> list;

    Context context;

    public ListAdapterTable(Context context, int vg, int id, ArrayList<Table> list){

        super(context,vg, id,list);

        this.context=context;

        this.vg=vg;

        this.list=list;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);

        TextView txtnumber = (TextView)itemView.findViewById(R.id.txtnumber);

        TextView txtsize=(TextView)itemView.findViewById(R.id.txtsize);

        //TextView txtSex=(TextView)itemView.findViewById(R.id.txtsex);

        try {

            txtnumber.setText("Table " + list.get(position).getTablenumber());

            txtsize.setText("Table Size = " + list.get(position).getTablesize());

        } catch (Exception e) {

            e.printStackTrace();

        }



        return itemView;

    }

}
