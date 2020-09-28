package com.example.gestor_incidencias.clases;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gestor_incidencias.*;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class agregar extends AppCompatActivity {
    EditText titulo;
    TextView prueba;
    Button add;
    public ArrayList<arrayli> incidencias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar);

        add = findViewById(R.id.add);
        titulo = findViewById(R.id.titulo);
        prueba = findViewById(R.id.prueba);

        //test
        final String title = titulo.getText().toString().trim();

        //codigo
        Spinner dropdown = findViewById(R.id.urgencia);
        String[] Valoracion = new String[]{"Alta", "Mediana", "Baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Valoracion);
        dropdown.setAdapter(adapter);

        final String urgencia = dropdown.getSelectedItem().toString();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incidencias.add(new arrayli(title, urgencia));
            }
        });

        // fragments pasar datos tutorals

    }
}
