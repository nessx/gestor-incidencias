package com.example.gestor_incidencias.clases;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gestor_incidencias.R;
import com.example.gestor_incidencias.iniciomenu;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class agregar extends Fragment {
    public Spinner prioridad;

    public agregar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View agregar = inflater.inflate(R.layout.fragment_agregar, container, false);
        final Button btnafegirIncidencia = agregar.findViewById(R.id.btnafegirIncidencia);

        //spinner
        prioridad = agregar.findViewById(R.id.urgencia);
        String[] Valoracion = new String[]{"Alta", "Mediana", "Baja"};
        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Valoracion);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioridad.setPrompt("Tipo de urgencia");
        prioridad.setAdapter(adapter);
        //end

        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String urgencia = prioridad.getSelectedItem().toString();
                EditText txtIncidencia = agregar.findViewById(R.id.txtincidencia);
                String txtIncidenciaForm = txtIncidencia.getText().toString();

                ((iniciomenu)getActivity()).arrayIncidencies.add(new incidencia(txtIncidenciaForm, urgencia));

            }
        });

        return agregar;
    }
}