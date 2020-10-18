package com.example.gestor_incidencias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestor_incidencias.clases.agregar;
import com.example.gestor_incidencias.clases.incidencia;
import com.example.gestor_incidencias.clases.listar;
import com.example.gestor_incidencias.clases.usuario;

import java.util.ArrayList;


public class iniciomenu extends AppCompatActivity {
    Button agregar,listar;
    TextView test;
    public ArrayList<incidencia> incidencias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuario user = new usuario();
        setContentView(R.layout.menu_inicio);
        agregar = findViewById(R.id.agregar);
        listar = findViewById(R.id.listar);
        test = findViewById(R.id.test);

        test.setText("Bienvenido/a "+user.getuser().toUpperCase());


        //codigo

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (iniciomenu.this, agregar.class);
                intent.putExtra("array_incidencias", incidencias);
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (iniciomenu.this, listar.class);
                intent.putExtra("array_incidencias", incidencias);
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });

        // fragments pasar datos tutorals

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode == RESULT_OK){
                incidencias = data.getParcelableArrayListExtra("array_incidencias");
            }
        }

    }
}