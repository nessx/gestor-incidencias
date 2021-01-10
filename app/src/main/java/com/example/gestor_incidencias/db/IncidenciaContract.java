package com.example.gestor_incidencias.db;

import android.provider.BaseColumns;

public class IncidenciaContract {
    private IncidenciaContract(){}
    public static class IncidenciaEntry implements BaseColumns {
        public static final String TABLE_NAME ="incidencia";
        public static final String ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PRIORITY = "prioritat";
        public static final String COLUMN_NAME_DESCRIPCIO = "descripcio";
        public static final String COLUMN_NAME_DATE = "fecha";
    }

}