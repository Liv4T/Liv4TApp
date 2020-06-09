package com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos.Semana.CrearSemana.Model;

public class SemanaItem {

    private String driving_question;
    private String class_development;
    private String observation;
    private String weeek;

    public SemanaItem(String driving_question, String class_development, String observation, String weeek) {
        this.driving_question = driving_question;
        this.class_development = class_development;
        this.observation = observation;
        this.weeek = weeek;
    }

    public String getDriving_question() {
        return driving_question;
    }

    public void setDriving_question(String driving_question) {
        this.driving_question = driving_question;
    }

    public String getClass_development() {
        return class_development;
    }

    public void setClass_development(String class_development) {
        this.class_development = class_development;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getWeeek() {
        return weeek;
    }

    public void setWeeek(String weeek) {
        this.weeek = weeek;
    }
}
