package com.example.gestor_incidencias.clases;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_incidencias.CustomAdapter;
import com.example.gestor_incidencias.R;

import java.util.ArrayList;

public class listar extends AppCompatActivity {

    //public ArrayList<incidencia> listaincidencias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);


        ArrayList<incidencia> incidencias = getIntent().getParcelableArrayListExtra("array_incidencias");

        /*
        Intent recogerDatos = getIntent();
        int contador = recogerDatos.getIntExtra("contador", 0);
        for (int i=0;i<incidencias.size();i++){

        }
         */



        //ArrayList<? extends incidencia> incidencias = getIntent().getParcelableArrayListExtra("array_incidencias");



        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(incidencias);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

}
