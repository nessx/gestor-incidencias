package com.example.gestor_incidencias.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class incidencia {
    private String titulo;
    private String urgencia;

    public incidencia(String titulo, String urgencia) {
        this.titulo = titulo;
        this.urgencia = urgencia;
    }

    public void setitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getitulo() {
        return titulo;
    }

    public void seturgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    public String geturgencia() {
        return urgencia;
    }

}
