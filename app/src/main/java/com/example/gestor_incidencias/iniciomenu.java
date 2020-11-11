package com.example.gestor_incidencias;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gestor_incidencias.clases.agregar;
import com.example.gestor_incidencias.clases.incidencia;
import com.example.gestor_incidencias.clases.usuario;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;

import java.util.ArrayList;
import java.util.List;


public class iniciomenu extends AppCompatActivity {
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    TextView userview;

    public ArrayList<incidencia> arrayIncidencies;

    protected Fragment[] menuFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_inicio);

        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        usuario user = new usuario();
        userview = findViewById(R.id.userview);

        userview.setText("Bienvenido/a "+user.getuser().toUpperCase());

        arrayIncidencies = new ArrayList<>();
        arrayIncidencies=dbHelper.listIncidencia();
        Bundle bundle = new Bundle();
        bundle.putSerializable("arrayIncidencies", arrayIncidencies);

    }

}