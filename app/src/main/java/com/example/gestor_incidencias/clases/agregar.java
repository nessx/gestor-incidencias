package com.example.gestor_incidencias.clases;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import com.example.gestor_incidencias.R;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;
import com.example.gestor_incidencias.iniciomenu;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
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
    //

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

        //date import
        final SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");



        //add hints
        btnafegirIncidencia.setText(R.string.in_add);


        //spinner
        prioridad = agregar.findViewById(R.id.urgencia);
        String[] prioridades = new String[]{getResources().getString(R.string.in_sel),getResources().getString(R.string.in_Alta), getResources().getString(R.string.in_Med), getResources().getString(R.string.in_Baja)};
        // Initializing an ArrayAdapter
        final ArrayAdapter adapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_spinner_dropdown_item,prioridades){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    //hacemos que el primer item no sea seleccionable
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView i = (TextView) view;
                if(position == 0){
                    i.setTextColor(Color.GRAY);
                }
                else {
                    i.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioridad.setPrompt(getResources().getString(R.string.in_type));
        prioridad.setAdapter(adapter);
        //end

        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String prioritat = prioridad.getSelectedItem().toString();
                EditText txtIncidencia = agregar.findViewById(R.id.txtincidencia);
                txtIncidencia.setHint(R.string.in_title);
                EditText txtDescripcio = agregar.findViewById(R.id.txtdescripcio);

                // to string
                String txtIncidenciaForm = txtIncidencia.getText().toString();
                String txtDescripcioForm = txtDescripcio.getText().toString();
                if (vacionulo(txtIncidenciaForm)){
                    alert();
                }else{
                    String currentdata = dat.format(new Date());
                    incidencia i = new incidencia(txtIncidenciaForm, prioritat, txtDescripcioForm, currentdata);
                    dbHelper.insertIncidencia(db,i);
                    Toast.makeText(getContext(),R.string.dialog_add_msg,Toast.LENGTH_SHORT).show();
                }

            }
        });


        return agregar;
    }


    public static boolean vacionulo(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
    void alert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(R.string.dialog_cln_tl);
        alertDialogBuilder
                .setMessage(R.string.dialog_del_txtvacio)
                .setCancelable(false)
                .setNegativeButton(R.string.dialog_aceptar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                }).create().show();
    }

}