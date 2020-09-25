package com.example.gestor_incidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gestor_incidencias.clases.*;



public class iniciomenu extends AppCompatActivity {
    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_inicio);
        agregar = findViewById(R.id.agregar);


        //codigo

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (iniciomenu.this, agregar.class);
                startActivity(intent);
            }
        });

        // fragments pasar datos tutorals

    }
}