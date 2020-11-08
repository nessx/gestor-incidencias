package com.example.gestor_incidencias.clases;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class incidencia {
    protected String nom;
    protected String prioritat;

    public incidencia(String nom, String prioritat){
        this.nom = nom;
        this.prioritat = prioritat;
    }

    public String getNom(){
        return nom;
    }

    public String getPrioritat(){
        return prioritat;
    }

    public void setNom(String newNom){
        this.nom = newNom;
    }

    public void setPrioritat(String newPrioritat){
        this.prioritat = newPrioritat;
    }

}
