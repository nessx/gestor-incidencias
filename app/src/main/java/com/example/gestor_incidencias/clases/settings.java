package com.example.gestor_incidencias.clases;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gestor_incidencias.clases.spref_manager;

import com.example.gestor_incidencias.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class settings extends Fragment {
    public Spinner languaje;

    private spref_manager s_preferences;
    private static Locale myLocale;


    public settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View settings = inflater.inflate(R.layout.fragment_settings, container, false);
        final Button btnsave = settings.findViewById(R.id.btnsave);
        final Button reset = settings.findViewById(R.id.reset);

        //shared prefrerences
        s_preferences = new spref_manager(getContext());

        //spinner
        languaje = settings.findViewById(R.id.lang);
        String[] l = new String[]{"Seleciona un idioma.","es", "en"};
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

        btnsave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(),"El lenguaje elegido fue: "+languaje.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                //setLocale(languaje.getSelectedItem().toString());
                changeLocale(languaje.getSelectedItem().toString());
                System.exit(2);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s_preferences.reset();
                System.exit(2);
            }
        });


        // Inflate the layout for this fragment
        return settings;
    }

    //Change Locale
    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);//Set Selected Locale
        savelangDetails(lang);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());//Update the config
    }

    private void savelangDetails(String lang) {
        new spref_manager(getContext()).savelangDetails(lang);
    }

}