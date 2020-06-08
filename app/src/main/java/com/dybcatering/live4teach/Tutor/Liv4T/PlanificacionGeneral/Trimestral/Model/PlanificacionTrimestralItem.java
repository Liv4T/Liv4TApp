package com.dybcatering.live4teach.Tutor.Liv4T.PlanificacionGeneral.Trimestral.Model;

public class PlanificacionTrimestralItem {

    private String unit_name;
    private String content;

    public PlanificacionTrimestralItem(String unit_name, String content) {
        this.unit_name = unit_name;
        this.content = content;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
