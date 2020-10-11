package com.example.gestor_incidencias.clases;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gestor_incidencias.*;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class agregar extends AppCompatActivity {
    EditText titulo;
    Button add;
    public Spinner prioridad;
    public ArrayList<arrayli> incidencias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar);
        add = findViewById(R.id.add);

        //lo que tendria que devolver
        titulo = findViewById(R.id.titulo);
        prioridad = findViewById(R.id.urgencia);

        //codigo
        String[] Valoracion = new String[]{"Alta", "Mediana", "Baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Valoracion);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioridad.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titulo.getText().toString().trim();
                String urgencia = prioridad.getSelectedItem().toString();


                incidencias.add(new arrayli(title, urgencia));
                alerta("Informacion", "usuario agregado correctemente");
            }
        });

        // fragments pasar datos tutorals
        Fragment f = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) incidencias);
        f.setArguments(bundle);
    }

    //alerta que recibe un titulo y un mensaje
    public void alerta (String titulos, String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(agregar.this);
        alertDialogBuilder.setTitle(titulos);
        alertDialogBuilder
                .setMessage(mensaje)
                .setCancelable(false)
                .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                }).create().show();
    }
}
