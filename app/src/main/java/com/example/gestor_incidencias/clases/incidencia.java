package com.example.gestor_incidencias.clases;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class incidencia {
    protected String nom;
    protected String prioritat;
    protected String descripcio;
    protected String fecha;

    public incidencia(String nom, String prioritat, String descripcio, String fecha){
        this.nom = nom;
        this.prioritat = prioritat;
        this.descripcio = descripcio;
        this.fecha = fecha;
    }

    public String getNom(){
        return nom;
    }

    public String getPrioritat(){
        return prioritat;
    }

    public String getDescripcio(){
        return descripcio;
    }

    public String getFecha(){
        return fecha;
    }

    public void setNom(String newNom){
        this.nom = newNom;
    }

    public void setPrioritat(String newPrioritat){
        this.prioritat = newPrioritat;
    }

}
