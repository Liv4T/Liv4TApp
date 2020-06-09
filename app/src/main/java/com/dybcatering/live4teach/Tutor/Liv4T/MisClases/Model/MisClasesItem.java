package com.dybcatering.live4teach.Tutor.Liv4T.MisClases.Model;

public class MisClasesItem {
    public String Id;
    public String Semana;

    public MisClasesItem(String id, String semana) {
        Id = id;
        Semana = semana;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSemana() {
        return Semana;
    }

    public void setSemana(String semana) {
        Semana = semana;
    }
}
