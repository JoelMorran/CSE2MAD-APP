package com.example.kobiqoi_laptop.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapterCheckout extends ArrayAdapter<Order> {

    DBHandler3 db;

    int vg;

    //YourCartActivity y = new YourCartActivity();

    ArrayList<Order> list;

    Context context;

    public ListAdapterCheckout(Context context, int vg, int id, ArrayList<Order> list){

        super(context,vg, id,list);

        this.context=context;

        this.vg=vg;

        this.list=list;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);

        TextView txtName=(TextView)itemView.findViewById(R.id.txtname);

        TextView txtPrice=(TextView)itemView.findViewById(R.id.txtprice);

        final TextView amount=(TextView)itemView.findViewById(R.id.amount);

       // ImageButton addbtn=(ImageButton) itemView.findViewById(R.id.addbtn);

        //ImageButton minus=(ImageButton) itemView.findViewById(R.id.minus);

        //Button remove = (Button) itemView.findViewById(R.id.remove);


        //TextView txtSex=(TextView)itemView.findViewById(R.id.txtsex);
        db = new DBHandler3(context);
        try {

            txtName.setText(list.get(position).getName());

            txtPrice.setText("$" + list.get(position).getPrice());

            amount.setText(list.get(position).getAmount() + "x");


        } catch (Exception e) {

            e.printStackTrace();

        }


        return itemView;

    }




}

