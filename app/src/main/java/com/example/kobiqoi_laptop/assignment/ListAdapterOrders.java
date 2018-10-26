package com.example.kobiqoi_laptop.assignment;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;


import java.util.ArrayList;

public class ListAdapterOrders extends ArrayAdapter<Order>  {

    DBHandler3 db;

    int vg;

    YourCartActivity y = new YourCartActivity();

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

        Button addbtn=(Button) itemView.findViewById(R.id.addbtn);

        Button minus=(Button) itemView.findViewById(R.id.minus);

        Button remove = (Button) itemView.findViewById(R.id.remove);

        db = new DBHandler3(context);
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
                int t = list.get(position).getID();

                String one = String.valueOf(amount.getText());
                int one2 = Integer.parseInt(one);
                ++one2;
                one = String.valueOf(one2);
                amount.setText(one);


                Order order = db.getOrder(t);
                order.setAmount(one);
                order.getTableid();
                db.updateOrder(order);
               db.close();




            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = list.get(position).getID();

                String one = String.valueOf(amount.getText());
                int one2 = Integer.parseInt(one);
                if(one2 >= 2) {
                    --one2;
                }
                one = String.valueOf(one2);
                amount.setText(one);



                Order order = db.getOrder(t);
                order.setAmount(one);
                db.updateOrder(order);
               db.close();



            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = list.get(position).getID();

                db.deleteOrder(db.getOrder(t));
                db.close();


                ListAdapterOrders.this.remove(list.get(position));


            }
        });

        return itemView;

    }


}

