package com.example.gestor_incidencias.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class incidencia implements Parcelable {
    private String titulo;
    private String urgencia;

    public incidencia(String titulo, String urgencia) {
        this.titulo = titulo;
        this.urgencia = urgencia;
    }

    protected incidencia(Parcel in) {
        titulo = in.readString();
        urgencia = in.readString();
    }

    public static final Creator<incidencia> CREATOR = new Creator<incidencia>() {
        @Override
        public incidencia createFromParcel(Parcel in) {
            return new incidencia(in);
        }

        @Override
        public incidencia[] newArray(int size) {
            return new incidencia[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(urgencia);
    }
}
