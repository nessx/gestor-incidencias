package com.example.gestor_incidencias.clases;

public class arrayli {
    private String titulo;
    private String urgencia;

    public arrayli(String titulo, String urgencia) {
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
