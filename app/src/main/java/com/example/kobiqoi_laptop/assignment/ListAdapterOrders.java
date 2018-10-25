package com.example.kobiqoi_laptop.assignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.kobiqoi_laptop.assignment.YourCartActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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


        //TextView txtSex=(TextView)itemView.findViewById(R.id.txtsex);
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


               // db.updateOrder(new Order(db.getOrder(t).getName(), db.getOrder(t).getExtra(), amount.getText().toString(), db.getOrder(t).getNote(),
                //        db.getOrder(t).getPrice(), db.getOrder(t).getCost(), db.getOrder(t).getTableid()));
                //db.getOrder(t).setAmount(one);
                //db.updateOrder(db.getOrder(t));

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

                /*String t = (list.get(position).getString("id").toString());
                String t2 = (list.get(position).getString("name").toString());
                String t3 = (list.get(position).getString("extra").toString());
                String t4 = (list.get(position).getString("amount").toString());
                String t5 = (list.get(position).getString("note").toString());
                String t6 = (list.get(position).getString("price").toString());
                // String t7 = (list.get(position).getString("cost").toString());
                String t8 = (list.get(position).getString("tableid").toString());
                String t9 = (list.get(position).getString("date").toString());*/


                Order order = db.getOrder(t);
                order.setAmount(one);
                db.updateOrder(order);
               db.close();
                //db.updateOrder(order(db.getOrder(t).getName(), db.getOrder(t).getExtra(), amount.getText().toString(), db.getOrder(t).getNote(),
                //        db.getOrder(t).getPrice(), db.getOrder(t).getCost(), db.getOrder(t).getTableid()));



                //db.getOrder(t).setAmount(one);
               // db.updateOrder(db.getOrder(t));


            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = list.get(position).getID();
               //int z = list.get(position);
                db.deleteOrder(db.getOrder(t));
                db.close();

               // ListAdapterOrders.

               /// list.get(position).remove(view);


                ListAdapterOrders.this.remove(list.get(position));

                //Intent myIntent = new Intent(context, YourCartActivity.class);
                //startActivity(myIntent);
                //ListAdapterOrders.this.notifyDataSetChanged();
                //ListAdapterOrders.this.invalidateViews();

            }
        });

        return itemView;

    }







}

