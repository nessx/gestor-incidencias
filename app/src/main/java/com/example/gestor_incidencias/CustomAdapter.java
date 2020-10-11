package com.example.gestor_incidencias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_incidencias.clases.arrayli;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    //se que lo de abajo es una cagada, pero estoy viendo videos de POO para entender un poco mas sobre arrayList y arreglar la pequeña cagadita. :)
    ArrayList<arrayli> arraylist;
    Context context;

    public CustomAdapter(Context context, ArrayList<arrayli> arraylist) {
        this.context = context;
        this.arraylist = arraylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // set the data in items
        holder.title.setText(arraylist.get(position).getitulo());
        holder.urgencia.setText(arraylist.get(position).geturgencia());
    }


    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView urgencia;



        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            urgencia = itemView.findViewById(R.id.urgencia);
        }
    }
}