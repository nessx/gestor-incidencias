package com.example.gestor_incidencias.clases;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_incidencias.CustomAdapter;
import com.example.gestor_incidencias.R;

import java.util.ArrayList;

public class listar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);
        ArrayList<arrayli> incidencias = new ArrayList<>();





        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(listar.this, titulo, urgencia);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

}
