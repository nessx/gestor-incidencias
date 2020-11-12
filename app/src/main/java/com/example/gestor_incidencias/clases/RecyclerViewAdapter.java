package com.example.gestor_incidencias.clases;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_incidencias.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<incidencia> array_incidencies;
    private listar context;

    public RecyclerViewAdapter(listar con, ArrayList<incidencia> arrI){
        array_incidencies = arrI;
        context = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.etiquetaNom.setText(array_incidencies.get(position).getNom());
        holder.etiquetaPrioritat.setText(array_incidencies.get(position).getPrioritat());
    }

    @Override
    public int getItemCount() {
        return array_incidencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView etiquetaNom, etiquetaPrioritat;
        CardView layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaNom = itemView.findViewById(R.id.itemListIncidencia);
            etiquetaPrioritat = itemView.findViewById(R.id.itemListPrioritat);
            layout = itemView.findViewById(R.id.layout);
        }
    }

}
