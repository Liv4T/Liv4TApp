package com.dybcatering.live4teach.Tutor.Liv4T.MisClases.DetalleSemanaClases.Model;

public class ClaseItem {


    public String Nombre;
    public String Descripcion;
    public String Nombre_Documento;
    public String Documento;
    public String Url;
    public String Video;
    public String Id_Weekly;

    public ClaseItem(String nombre, String descripcion, String nombre_Documento, String documento, String url, String video, String id_Weekly) {
        Nombre = nombre;
        Descripcion = descripcion;
        Nombre_Documento = nombre_Documento;
        Documento = documento;
        Url = url;
        Video = video;
        Id_Weekly = id_Weekly;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNombre_Documento() {
        return Nombre_Documento;
    }

    public void setNombre_Documento(String nombre_Documento) {
        Nombre_Documento = nombre_Documento;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String documento) {
        Documento = documento;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public String getId_Weekly() {
        return Id_Weekly;
    }

    public void setId_Weekly(String id_Weekly) {
        Id_Weekly = id_Weekly;
    }
}
