package com.example.kobiqoi_laptop.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListAdapterOrders extends ArrayAdapter<Order> {
    DBHandler3 db;
    int vg;

    ArrayList<Order> list;

    Context context;

    public ListAdapterOrders(Context context, int vg, int id, ArrayList<Order> list){

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

        ImageButton addbtn=(ImageButton) itemView.findViewById(R.id.addbtn);

        ImageButton minus=(ImageButton) itemView.findViewById(R.id.minus);

        Button remove = (Button) itemView.findViewById(R.id.remove);


        //TextView txtSex=(TextView)itemView.findViewById(R.id.txtsex);

        try {

            txtName.setText(list.get(position).getName());

            txtPrice.setText(list.get(position).getPrice());

            amount.setText(list.get(position).getAmount());


        } catch (Exception e) {

            e.printStackTrace();

        }

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String one = String.valueOf(amount.getText());
                int one2 = Integer.parseInt(one);
                ++one2;
                one = String.valueOf(one2);
                amount.setText(one);

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String one = String.valueOf(amount.getText());
                int one2 = Integer.parseInt(one);
                if(one2 >= 2) {
                    --one2;
                }
                one = String.valueOf(one2);
                amount.setText(one);

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              db.deleteOrder(list.get(position));
            }
        });

        return itemView;

    }

}

