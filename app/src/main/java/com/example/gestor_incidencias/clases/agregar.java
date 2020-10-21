package com.example.gestor_incidencias.clases;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestor_incidencias.*;

import java.io.Serializable;
import java.util.ArrayList;

public class agregar extends AppCompatActivity implements Serializable {
    EditText titulo;
    Button add;
    public Spinner prioridad;
    ArrayList<incidencia> incidencias;

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

                incidencias.add(title,urgencia);

                int counter = incidencias.size();
                if (counter>=1) {
                    alerta("Informacion", "usuario agregado correctemente");
                }
                else{
                    alerta("ERROR", "No se ha podido agregar la alerta");
                }
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("array_incidencias", incidencias);
        setResult(RESULT_OK, intent);
        finish();
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
