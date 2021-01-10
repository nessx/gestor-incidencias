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
import androidx.recyclerview.widget.ItemTouchHelper;
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
        holder.etiquetaDescripcio.setText(array_incidencies.get(position).getDescripcio());
        holder.etiquetaData.setText(array_incidencies.get(position).getFecha());
    }
    

    //REMOVE ITEM
    public void removeItem(int position) {
        array_incidencies.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem(incidencia item, int position) {
        array_incidencies.add(position, item);
        notifyItemInserted(position);
    }
    //END

    @Override
    public int getItemCount() {
        return array_incidencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView etiquetaNom, etiquetaPrioritat, etiquetaDescripcio, etiquetaData;
        CardView layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaNom = itemView.findViewById(R.id.itemListIncidencia);
            etiquetaPrioritat = itemView.findViewById(R.id.itemListPrioritat);
            etiquetaDescripcio = itemView.findViewById(R.id.itemListDescripcio);
            etiquetaData = itemView.findViewById(R.id.itemListdata);
            layout = itemView.findViewById(R.id.layout);
        }
    }
    

}
