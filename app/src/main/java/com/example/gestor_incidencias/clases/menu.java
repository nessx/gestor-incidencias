package com.example.gestor_incidencias.clases;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gestor_incidencias.R;
import com.example.gestor_incidencias.clases.agregar;
import com.example.gestor_incidencias.clases.listar;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;
import com.example.gestor_incidencias.iniciomenu;

import static com.example.gestor_incidencias.db.IncidenciaDBHelper.DATABASE_NAME;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class menu extends Fragment {

    //Create the instance of dbHelper for test things
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    private final int[] BTNMENU = {R.id.afegir, R.id.llistar, R.id.eliminar};

    public menu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //test
        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        //end
        // Inflate the layout for this fragment
        View menu = inflater.inflate(R.layout.fragment_menu, container, false);

        final Button btnAfegir = menu.findViewById(R.id.afegir);
        btnAfegir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment fragmentAddIncidencia = new agregar();
                menuTransaction.replace(R.id.fragmentID, fragmentAddIncidencia );

                menuTransaction.commit();
                dbHelper.close();
                db.close();
            }
        });

        final Button btnLlistar = menu.findViewById(R.id.llistar);
        btnLlistar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment fragmentList = new listar();
                menuTransaction.replace(R.id.fragmentID, fragmentList );


                menuTransaction.commit();

            }
        });

        final Button btnEliminar = menu.findViewById(R.id.eliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int numIncidencies = ((iniciomenu)getActivity()).arrayIncidencies.size();
                Toast toast = Toast.makeText(((iniciomenu)getActivity()).getApplicationContext(), String.valueOf(numIncidencies), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return menu;
    }
}