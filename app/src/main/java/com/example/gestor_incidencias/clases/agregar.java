package com.example.gestor_incidencias.clases;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gestor_incidencias.R;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;
import com.example.gestor_incidencias.iniciomenu;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class agregar extends Fragment {
    public Spinner prioridad;
    //Create the instance of dbHelper
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;
    ArrayList<incidencia> incidenciarr;

    public agregar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        // Inflate the layout for this fragment
        final View agregar = inflater.inflate(R.layout.fragment_agregar, container, false);
        final Button btnafegirIncidencia = agregar.findViewById(R.id.btnafegirIncidencia);

        //spinner
        prioridad = agregar.findViewById(R.id.urgencia);
        String[] prioridades = new String[]{"Alta", "Mediana", "Baja"};
        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, prioridades);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioridad.setPrompt("Tipo de urgencia");
        prioridad.setAdapter(adapter);
        //end

        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String prioritat = prioridad.getSelectedItem().toString();
                EditText txtIncidencia = agregar.findViewById(R.id.txtincidencia);
                String txtIncidenciaForm = txtIncidencia.getText().toString();

                incidencia incidencia = new incidencia(txtIncidenciaForm, prioritat);

                //INSERTANDO DATOS DENTRO DE LA BBDD
                dbHelper.insertIncidencia(db,incidencia);

            }
        });


        return agregar;
    }

}