package com.example.gestor_incidencias.clases;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestor_incidencias.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class settings extends Fragment {
    public Spinner languaje;


    public settings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View settings = inflater.inflate(R.layout.fragment_settings, container, false);

        //spinner
        languaje = settings.findViewById(R.id.lang);
        String[] l = new String[]{"Seleccionar idioma","Es", "En"};
        // Initializing an ArrayAdapter
        final ArrayAdapter adapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_spinner_dropdown_item,l){
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
        languaje.setPrompt(getResources().getString(R.string.in_type));
        languaje.setAdapter(adapter);
        //end

        Toast.makeText(getContext(),"El lenguaje elegido fue: "+languaje.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        setLocale(languaje.getSelectedItem().toString());


        // Inflate the layout for this fragment
        return settings;
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }
}