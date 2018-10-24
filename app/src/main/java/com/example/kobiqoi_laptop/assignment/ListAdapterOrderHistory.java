package com.example.kobiqoi_laptop.assignment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListAdapterOrderHistory extends ArrayAdapter<JSONObject> {

    int vg;

    ArrayList<JSONObject> list;

    Context context;

    public ListAdapterOrderHistory(Context context, int vg, int id, ArrayList<JSONObject> list){

        super(context,vg, id,list);

        this.context=context;

        this.vg=vg;

        this.list=list;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(vg, parent, false);

        TextView id2=(TextView)itemView.findViewById(R.id.id2);

        TextView name=(TextView)itemView.findViewById(R.id.name2);

        TextView extra=(TextView)itemView.findViewById(R.id.extra2);

        TextView amount=(TextView)itemView.findViewById(R.id.amount2);

        TextView note=(TextView)itemView.findViewById(R.id.note2);

        TextView price=(TextView)itemView.findViewById(R.id.price2);

        TextView cost=(TextView)itemView.findViewById(R.id.cost2);

        TextView tableid=(TextView)itemView.findViewById(R.id.tableid2);

        TextView date=(TextView)itemView.findViewById(R.id.date2);




        try {

           String t = (list.get(position).getString("id").toString());
            String t2 = (list.get(position).getString("name").toString());
            String t3 = (list.get(position).getString("extra").toString());
            String t4 = (list.get(position).getString("amount").toString());
            String t5 = (list.get(position).getString("note").toString());
            String t6 = (list.get(position).getString("price").toString());
           // String t7 = (list.get(position).getString("cost").toString());
            String t8 = (list.get(position).getString("tableid").toString());
            String t9 = (list.get(position).getString("date").toString());





            id2.setText(list.get(position).getString("id"));
            name.setText(list.get(position).getString("name"));
            extra.setText(list.get(position).getString("extra"));
            amount.setText(list.get(position).getString("amount"));
            if(!(list.get(position).getString("note").equals(""))){
                note.setText(list.get(position).getString("note"));
            }
            else
            {
                note.setText(" ");
            }


            double x = Double.valueOf(list.get(position).getString("amount"));
            double y = Double.valueOf(list.get(position).getString("price"));

            String z = Double.toString((x * y));
            cost.setText(z);

            tableid.setText(list.get(position).getString("tableid"));

            price.setText(list.get(position).getString("price"));

            date.setText(list.get(position).getString("date"));









        }

        catch (JSONException e)
        {
e.printStackTrace();
        }



        return itemView;

    }

}
