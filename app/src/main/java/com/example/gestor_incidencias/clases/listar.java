package com.example.gestor_incidencias.clases;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_incidencias.R;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;
import com.example.gestor_incidencias.iniciomenu;

/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class listar extends Fragment {
    //test
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;
    //end

    public listar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        // Inflate the layout for this fragment
        View listar = inflater.inflate(R.layout.fragment_listar, container, false);

        RecyclerView recyclerView = (RecyclerView)listar.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(listar.getContext()));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dbHelper.listIncidencia());

        recyclerView.setAdapter(adapter);


        return listar;
    }
}